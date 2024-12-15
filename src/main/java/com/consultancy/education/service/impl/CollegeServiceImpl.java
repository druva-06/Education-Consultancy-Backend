package com.consultancy.education.service.impl;

import com.consultancy.education.DTOs.requestDTOs.college.CollegeAndAddressRequestDto;
import com.consultancy.education.DTOs.requestDTOs.college.CollegeRequestDto;
import com.consultancy.education.DTOs.responseDTOs.college.CollegeResponseDto;
import com.consultancy.education.DTOs.responseDTOs.collegeCourse.CollegeCourseResponseDto;
import com.consultancy.education.exception.DatabaseException;
import com.consultancy.education.exception.NotFoundException;
import com.consultancy.education.exception.ValidationException;
import com.consultancy.education.helper.ExcelHelper;
import com.consultancy.education.model.Address;
import com.consultancy.education.model.College;
import com.consultancy.education.model.CollegeCourse;
import com.consultancy.education.repository.CollegeRepository;
import com.consultancy.education.service.CollegeService;
import com.consultancy.education.transformer.AddressTransformer;
import com.consultancy.education.transformer.CollegeCourseTransformer;
import com.consultancy.education.transformer.CollegeTransformer;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {

    private final CollegeRepository collegeRepository;

    public CollegeServiceImpl(CollegeRepository collegeRepository) {
        this.collegeRepository = collegeRepository;
    }

    @Override
    public String bulkCollegesUpload(MultipartFile file) {
        int collegeCount = 0;
        int updatedCount = 0;
        try{
            List<College> collegeList = ExcelHelper.convertCollegeExcelIntoList(file.getInputStream());
            collegeCount = collegeList.size();
            for (College college : collegeList) {
                College existingCollege =  collegeRepository.findByNameAndCampusAndCountry(college.getName(),  college.getCampus(), college.getCountry());
                if(existingCollege != null){
                    college.setId(existingCollege.getId());
                    college.setAddress(existingCollege.getAddress());
                    college.setCollegeCourses(existingCollege.getCollegeCourses());
                    college.setSeo(existingCollege.getSeo());
                    updatedCount++;
                }
                collegeRepository.save(college);
            }
        }
        catch (Exception e){
            throw new DatabaseException(e.getMessage());
        }
        return "Created College Count : " + (collegeCount - updatedCount) + " & Updated College Count : " + updatedCount;
    }

    @Override
    public CollegeResponseDto add(CollegeAndAddressRequestDto collegeAndAddressRequestDto) {
        try {
            College college = CollegeTransformer.toEntity(collegeAndAddressRequestDto.getCollege());
            Address address = AddressTransformer.toEntity(collegeAndAddressRequestDto.getAddress());
            college.setAddress(address);
            address.setCollege(college);
            collegeRepository.save(college);
            return new CollegeResponseDto(college.getId(), college.getName());
        }
        catch (Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public List<CollegeResponseDto> getColleges() {
        try{
            List<College> collegeList = collegeRepository.findAll();
            return CollegeTransformer.toResDTO(collegeList);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public List<CollegeResponseDto> getCollegesByCountries(List<String> countries) {
        try{
            List<College> collegeList = collegeRepository.findByAddress_CountryIn(countries);
            return CollegeTransformer.toResDTO(collegeList);
        }
        catch (Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public CollegeRequestDto getCollege(Long id) {
        if (collegeRepository.findById(id).isPresent()) {
            College college = collegeRepository.findById(id).get();
            return CollegeTransformer.toReqDTO(college);
        } else {
            throw new NotFoundException("College not found");
        }
    }

    @Override
    public Long getCollegeCount() {
        try{
            return collegeRepository.count();
        }
        catch (Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public CollegeResponseDto deleteCollege(Long id){
        if (collegeRepository.findById(id).isPresent()) {
            College college = collegeRepository.findById(id).get();
            collegeRepository.delete(college);
            return CollegeTransformer.toResDTO(college);
        }
        else {
            throw new NotFoundException("College not found");
        }
    }

    @Override
    public CollegeRequestDto updateCollege(Long id, CollegeRequestDto collegeRequestDto) {
        if(collegeRepository.findById(id).isPresent()){
            College college = collegeRepository.findById(id).get();
            CollegeTransformer.updateCollegeDetails(college, collegeRequestDto);
            return CollegeTransformer.toReqDTO(college);
        }
        else{
            throw new NotFoundException("College not found");
        }
    }

    @Override
    public List<CollegeResponseDto> getCollegeByName(String name) {
        List<College> colleges = collegeRepository.findByNameContainingIgnoreCase(name, PageRequest.of(0, 5));
        if (!colleges.isEmpty()) {
            return CollegeTransformer.toResDTO(colleges);
        }
        else{
            throw new NotFoundException("College not found");
        }
    }

    @Override
    public List<CollegeResponseDto> sortCollegeByName(String type) {
        type = type.toUpperCase();
        List<College> colleges;
        if(type.equals("ASC")){
            colleges = collegeRepository.findAllByOrderByNameAsc();
        }
        else if(type.equals("DESC")){
            colleges = collegeRepository.findAllByOrderByNameDesc();
        }
        else{
            List<String> errors = new ArrayList<>();
            errors.add("Invalid sort type");
            throw new ValidationException(errors);
        }
        return CollegeTransformer.toResDTO(colleges);
    }

    @Override
    public List<CollegeCourseResponseDto> getCollegeCourses(Long collegeId) {
        if(collegeRepository.findById(collegeId).isPresent()){
            College college = collegeRepository.findById(collegeId).get();
            List<CollegeCourse> collegeCourses = college.getCollegeCourses();
            return CollegeCourseTransformer.toResDto(collegeCourses);
        }
        throw new NotFoundException("College not found");
    }
}

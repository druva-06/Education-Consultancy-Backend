package com.consultancy.education.service.impl;

import com.consultancy.education.DTOs.requestDTOs.address.AddressRequestDto;
import com.consultancy.education.DTOs.responseDTOs.address.AddressResponseDto;
import com.consultancy.education.enums.EntityType;
import com.consultancy.education.exception.NotFoundException;
import com.consultancy.education.model.Address;
import com.consultancy.education.model.College;
import com.consultancy.education.model.Student;
import com.consultancy.education.repository.AddressRepository;
import com.consultancy.education.repository.CollegeRepository;
import com.consultancy.education.repository.StudentRepository;
import com.consultancy.education.service.AddressService;
import com.consultancy.education.transformer.AddressTransformer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final StudentRepository studentRepository;
    private final CollegeRepository collegeRepository;

    public AddressServiceImpl(AddressRepository addressRepository, StudentRepository studentRepository, CollegeRepository collegeRepository) {
        this.addressRepository = addressRepository;
        this.studentRepository = studentRepository;
        this.collegeRepository = collegeRepository;
    }

    @Override
    public AddressResponseDto addAddress(AddressRequestDto addressRequestDto, Long id) {
        Address address = AddressTransformer.toEntity(addressRequestDto);
        if(addressRequestDto.getEntityType() == EntityType.STUDENT){
            if(studentRepository.findById(id).isPresent()){
                Student student = studentRepository.findById(id).get();
                student.getAddresses().add(address);
                address.setStudent(student);
                addressRepository.save(address);
                return AddressTransformer.toResDTO(address, student.getId());
            }
            throw new NotFoundException("Student not found");
        }
        else if(addressRequestDto.getEntityType() == EntityType.COLLEGE){
            if(collegeRepository.findById(id).isPresent()){
                College college = collegeRepository.findById(id).get();
                college.setAddress(address);
                address.setCollege(college);
                addressRepository.save(address);
                return AddressTransformer.toResDTO(address, college.getId());
            }
            throw new NotFoundException("College not found");
        }
        else{
            throw new NotFoundException("Entity type not found");
        }
    }

    @Override
    public AddressResponseDto updateAddress(AddressRequestDto addressRequestDto, Long id) {
        if(addressRepository.findById(id).isPresent()){
            Address address = addressRepository.findById(id).get();
            AddressTransformer.updateAddressDetails(address, addressRequestDto);
            address = addressRepository.save(address);
            AddressResponseDto addressResponseDto;
            if(address.getEntityType() == EntityType.STUDENT){
                addressResponseDto = AddressTransformer.toResDTO(address, address.getStudent().getId());
            }
            else if(address.getEntityType() == EntityType.COLLEGE){
                addressResponseDto = AddressTransformer.toResDTO(address, address.getCollege().getId());
            }
            else {
                throw new NotFoundException("Entity type not found");
            }
            return addressResponseDto;
        }
        throw new NotFoundException("Address not found");
    }

    @Override
    public List<AddressResponseDto> getAddress(Long entityId, String entityType) {
        if(entityType.equals("STUDENT")){
            List<Address> addresses = addressRepository.findByStudent_Id(entityId);
            return AddressTransformer.toResDTO(addresses, addresses.get(0).getStudent().getId());
        }
        else if(entityType.equals("COLLEGE")){
            List<Address> addresses = addressRepository.findByCollege_Id(entityId);
            return AddressTransformer.toResDTO(addresses, addresses.get(0).getCollege().getId());
        }
        else {
            throw new NotFoundException("Entity type not found");
        }
    }
}

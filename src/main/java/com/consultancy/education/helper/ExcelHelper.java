package com.consultancy.education.helper;

import com.consultancy.education.enums.GraduationLevel;
import com.consultancy.education.exception.ExcelException;
import com.consultancy.education.model.College;
import com.consultancy.education.model.Course;
import com.consultancy.education.utils.BasicValidations;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static boolean checkExcelFormat(MultipartFile multipartFile){
        String contentType = multipartFile.getContentType();
        if (contentType == null) return false;
        return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<College> convertCollegeExcelIntoList(InputStream inputStream) throws Exception {
        List<College> collegeArrayList = new ArrayList<>();

        try{
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet collegeSheet = workbook.getSheet("colleges");
            Iterator<Row> colleges = collegeSheet.iterator();
            int row = 0;
            while(colleges.hasNext()){
                Row collegeRow = colleges.next();
                if(row == 0){
                    row++;
                    continue;
                }
                row++;
                College college = getCollege(collegeRow);
                if(college.getName() == null) break;
                collegeArrayList.add(college);
            }
        }
        catch (Exception e){
            throw new ExcelException(e.getMessage());
        }

        return collegeArrayList;
    }

    public static List<Course> convertCourseExcelIntoList(InputStream inputStream) throws Exception {
        List<Course> courseArrayList = new ArrayList<>();

        try{
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet courseSheet = workbook.getSheet("courses");
            Iterator<Row> courses = courseSheet.iterator();
            int row = 0;
            while(courses.hasNext()){
                Row courseRow = courses.next();
                if(row == 0){
                    row++;
                    continue;
                }
                row++;
                Course course = getCourse(courseRow);
                if(course.getName() == null) break;
                courseArrayList.add(course);
            }
        }
        catch (Exception e){
            throw new ExcelException(e.getMessage());
        }

        return courseArrayList;
    }

    private static Course getCourse(Row courseRow) {
        BasicValidations basicValidations = new BasicValidations();
        int col = 0;
        Course course = new Course();
        for (Cell cell : courseRow) {
            switch (col){
                case 0:
                    course.setName(basicValidations.validateString(cell));
                    break;
                case 1:
                    course.setDepartment(basicValidations.validateString(cell));
                    break;
                case 2:
                    course.setGraduationLevel(GraduationLevel.valueOf(basicValidations.validateString(cell)));
                    break;
                case 3:
                    course.setSpecialization(basicValidations.validateString(cell));
                    break;
                case 4:
                    course.setDescription(basicValidations.validateString(cell));
                    break;
                default:
                    break;
            }
            col++;
        }
        return course;
    }

    private static College getCollege(Row collegeRow) {
        BasicValidations basicValidations = new BasicValidations();
        int col = 0;
        College college = new College();
        for (Cell cell : collegeRow) {
            switch (col){
                case 0:
                    college.setName(basicValidations.validateString(cell));
                    break;
                case 1:
                    college.setCampus(basicValidations.validateString(cell));
                    break;
                case 2:
                    college.setWebsiteUrl(basicValidations.validateString(cell));
                    break;
                case 3:
                    college.setCollegeLogo(basicValidations.validateString(cell));
                    break;
                case 4:
                    college.setCountry(basicValidations.validateString(cell));
                    break;
                case 5:
                    college.setEstablishedYear(basicValidations.validateInteger(cell));
                    break;
                case 6:
                    college.setRanking(basicValidations.validateInteger(cell));
                    break;
                case 7:
                    college.setStudyLevel(basicValidations.validateString(cell));
                    break;
                case 8:
                    college.setIeltsMinScore(basicValidations.validateDouble(cell));
                    break;
                case 9:
                    college.setToeflMinScore(basicValidations.validateDouble(cell));
                    break;
                case 10:
                    college.setPteMinScore(basicValidations.validateDouble(cell));
                    break;
                case 11:
                    college.setGreMinScore(basicValidations.validateDouble(cell));
                    break;
                case 12:
                    college.setGmatMinScore(basicValidations.validateDouble(cell));
                    break;
                case 13:
                    college.setSatMinScore(basicValidations.validateDouble(cell));
                    break;
                case 14:
                    college.setCatMinScore(basicValidations.validateDouble(cell));
                    break;
                case 15:
                    college.setDetMinScore(basicValidations.validateDouble(cell));
                    break;
                case 16:
                    college.setMin10thScore(basicValidations.validateDouble(cell));
                    break;
                case 17:
                    college.setMinInterScore(basicValidations.validateDouble(cell));
                    break;
                case 18:
                    college.setMinGraduationScore(basicValidations.validateDouble(cell));
                    break;
                case 19:
                    college.setScholarshipEligible(basicValidations.validateString(cell));
                    break;
                case 20:
                    college.setScholarshipDetails(basicValidations.validateString(cell));
                    break;
                case 21:
                    college.setBacklogAcceptanceRange(basicValidations.validateInteger(cell));
                    break;
                case 22:
                    college.setRemarks(basicValidations.validateString(cell));
                    break;
                case 23:
                    college.setDescription(basicValidations.validateString(cell));
                    break;
                case 24:
                    college.setCampusGalleryVideoLink(basicValidations.validateString(cell));
                    break;
                case 25:
                    college.setEligibilityCriteria(basicValidations.validateString(cell));
                    break;
                default:
                    break;
            }
            col++;
        }
        return college;
    }
}

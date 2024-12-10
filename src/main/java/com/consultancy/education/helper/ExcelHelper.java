package com.consultancy.education.helper;

import com.consultancy.education.exception.ExcelException;
import com.consultancy.education.model.College;
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

    public static List<College> covertExcelIntoList(InputStream inputStream) throws Exception {
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
                collegeArrayList.add(college);
            }
        }
        catch (Exception e){
            throw new ExcelException(e.getMessage());
        }

        return collegeArrayList;
    }

    private static College getCollege(Row collegeRow) {
        int col = 0;
        College college = new College();
        for (Cell cell : collegeRow) {
            switch (col){
                case 0:
                    college.setName(cell.getStringCellValue());
                    break;
                case 1:
                    college.setCampus(cell.getStringCellValue());
                    break;
                case 2:
                    college.setWebsiteUrl(cell.getStringCellValue());
                    break;
                case 3:
                    college.setCollegeLogo(cell.getStringCellValue());
                    break;
                case 4:
                    college.setCountry(cell.getStringCellValue());
                    break;
                case 5:
                    college.setEstablishedYear((int) cell.getNumericCellValue());
                    break;
                case 6:
                    college.setRanking((int) cell.getNumericCellValue());
                    break;
                case 7:
                    college.setStudyLevel(cell.getStringCellValue());
                    break;
                case 8:
                    college.setIeltsMinScore(cell.getNumericCellValue());
                    break;
                case 9:
                    college.setToeflMinScore(cell.getNumericCellValue());
                    break;
                case 10:
                    college.setPteMinScore(cell.getNumericCellValue());
                    break;
                case 11:
                    college.setGreMinScore(cell.getNumericCellValue());
                    break;
                case 12:
                    college.setGmatMinScore(cell.getNumericCellValue());
                    break;
                case 13:
                    college.setMin10thScore(cell.getNumericCellValue());
                    break;
                case 14:
                    college.setMinInterScore(cell.getNumericCellValue());
                    break;
                case 15:
                    college.setMinGraduationScore(cell.getNumericCellValue());
                    break;
                case 16:
                    college.setScholarshipEligible(cell.getStringCellValue());
                    break;
                case 17:
                    college.setScholarshipDetails(cell.getStringCellValue());
                    break;
                case 18:
                    college.setBacklogAcceptanceRange((int) cell.getNumericCellValue());
                    break;
                case 19:
                    college.setRemarks(cell.getStringCellValue());
                    break;
                case 20:
                    college.setDescription(cell.getStringCellValue());
                    break;
                case 21:
                    college.setCampusGalleryVideoLink(cell.getStringCellValue());
                    break;
                case 22:
                    college.setEligibilityCriteria(cell.getStringCellValue());
                    break;
                default:
                    break;
            }
            col++;
        }
        return college;
    }
}

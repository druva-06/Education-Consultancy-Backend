package com.consultancy.education.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class BasicValidations {

    public Object cellValidation(Cell cell) {
        if(cell.getCellType() == CellType.STRING) {
            String value = cell.getStringCellValue();
            value = value.trim();
            if(value.isEmpty() || value.equals("NA")) {
                return null;
            }
            return value;
        }
        else if(cell.getCellType() == CellType.NUMERIC) {
            return (Double) cell.getNumericCellValue();
        }
        else if(cell.getCellType() == CellType.BLANK) {
            return null;
        }
        return null;
    }

    public String validateString(Cell cell){
        if(cell == null) return null;
        if(cell.getCellType() == CellType.STRING) {
            String value = cell.getStringCellValue().trim();
            if(value.isEmpty() || value.equals("NA")) {
                return null;
            }
            return value;
        }
        return null;
    }

    public Integer validateInteger(Cell cell){
        if(cell == null) return null;
        if(cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        }
        return null;
    }

    public Double validateDouble(Cell cell){
        if(cell == null) return null;
        if(cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        }
        return null;
    }
}

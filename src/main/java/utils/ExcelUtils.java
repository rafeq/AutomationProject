package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    private Workbook workbook;
    private Sheet sheet;

    // Constructor to load Excel file and initialize the sheet
    public ExcelUtils(String filePath, String sheetName) {
        try {
            // Load the workbook
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);

            // Load the sheet
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' does not exist in the workbook");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Excel file: " + e.getMessage(), e);
        }
    }

    // Get data from a specific cell
    public String getCellData(int rowNum, int colNum) {
        if (sheet == null) {
            throw new IllegalStateException("Sheet is not initialized.");
        }

        Row row = sheet.getRow(rowNum);
        if (row == null) {
            return ""; // Return empty if the row doesn't exist
        }

        Cell cell = row.getCell(colNum);
        if (cell == null) {
            return ""; // Return empty if the cell doesn't exist
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    // Get the number of rows in the sheet
    public int getRowCount() {
        if (sheet == null) {
            throw new IllegalStateException("Sheet is not initialized.");
        }
        return sheet.getLastRowNum() + 1;
    }

    // Get the number of columns in the first row
    public int getColumnCount() {
        if (sheet == null) {
            throw new IllegalStateException("Sheet is not initialized.");
        }
        Row firstRow = sheet.getRow(0);
        if (firstRow == null) {
            return 0; // No columns if the first row is null
        }
        return firstRow.getLastCellNum();
    }

    // Close the workbook to free resources
    public void closeWorkbook() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

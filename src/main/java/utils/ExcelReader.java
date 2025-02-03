package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Utility class to read data from an Excel file.
 * This class provides a method to read the data from a specified sheet of an Excel file
 * and store it in a 2D Object array.
 */
public class ExcelReader {

    /**
     * Reads data from an Excel file and returns it in a 2D Object array.
     * It reads all the rows and columns from the specified sheet in the Excel file.
     *
     * @param filePath   The file path of the Excel file.
     * @param sheetName  The name of the sheet from which to read the data.
     * @return           A 2D Object array containing the data from the sheet.
     * @throws IOException If there is an issue with reading the file or parsing the sheet.
     */
    public static Object[][] readExcelData(String filePath, String sheetName) throws IOException {
        // Opening the Excel file
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        // Check if the sheet exists
        if (sheet == null) {
            throw new RuntimeException("Sheet '" + sheetName + "' does not exist in the file: " + filePath);
        }

        // Getting the number of rows and columns in the sheet
        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();

        // Creating a 2D array to store the data
        Object[][] data = new Object[rowCount][colCount];

        // Loop through the rows and columns to extract data
        for (int i = 1; i <= rowCount; i++) { // Skip the header row
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                if (cell != null) {
                    // Switch based on the cell type to read the data correctly
                    switch (cell.getCellType()) {
                        case STRING:
                            data[i - 1][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            // Handling numeric cells
                            data[i - 1][j] = String.valueOf((int) cell.getNumericCellValue());
                            break;
                        default:
                            // If the cell type is not handled, set the value to an empty string
                            data[i - 1][j] = "";
                            break;
                    }
                } else {
                    // If the cell is null, set the value to an empty string
                    data[i - 1][j] = "";
                }
            }
        }

        // Closing the workbook and file input stream
        workbook.close();
        fis.close();

        // Return the data array
        return data;
    }
}

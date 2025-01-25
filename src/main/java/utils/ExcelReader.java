package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    public static Object[][] readExcelData(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            throw new RuntimeException("Sheet '" + sheetName + "' does not exist in the file: " + filePath);
        }

        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) { // Skip header row
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            data[i - 1][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            data[i - 1][j] = String.valueOf((int) cell.getNumericCellValue());
                            break;
                        default:
                            data[i - 1][j] = "";
                            break;
                    }
                } else {
                    data[i - 1][j] = "";
                }
            }
        }

        workbook.close();
        fis.close();
        return data;
    }
}

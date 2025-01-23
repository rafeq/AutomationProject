package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelReader {
    private Workbook workbook;

    public ExcelReader(String filePath) throws Exception {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
    }

    public String getCellData(String sheetName, int row, int col) {
        Sheet sheet = workbook.getSheet(sheetName);
        Row rowData = sheet.getRow(row);
        Cell cell = rowData.getCell(col);
        return cell.toString();
    }

    public void close() throws Exception {
        workbook.close();
    }
}


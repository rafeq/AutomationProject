package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriteRegistrationData {
    public static void main(String[] args) {
        // File path to save the Excel file
        String filePath = "/Users/rafeqfiad/IdeaProjects/AutomationProject/src/test/resources/RegisterData.xlsx";

        try (Workbook workbook = new XSSFWorkbook()) {
            // Create a new sheet
            Sheet sheet = workbook.createSheet("RegisterData");

            // Create header row
            String[] headers = {
                    "Name", "Email", "Password", "Date of Birth", "First name", "Last name",
                    "Address", "Country", "State", "City", "Zip Code", "Mobile Number"
            };

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            // Sample data
            String[][] sampleData = {
                    {"Test User", "testuser@example.com", "password123", "01/01/2000", "First name", "Last name",
                            "123 Test Street, Test Company", "United States", "California", "Los Angeles", "90001", "051111111111"},
                    {"Jane Doe", "janedoe@example.com", "password456", "15/05/1995", "First name", "Last name",
                            "456 Jane Street, P.O. Box 789", "Canada", "Ontario", "Toronto", "M5H 2N2", "9876543210"}
            };

            // Write sample data
            for (int i = 0; i < sampleData.length; i++) {
                Row dataRow = sheet.createRow(i + 1);
                for (int j = 0; j < sampleData[i].length; j++) {
                    dataRow.createCell(j).setCellValue(sampleData[i][j]);
                }
            }

            // Adjust column widths
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to the Excel file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("Registration data written to Excel file successfully!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

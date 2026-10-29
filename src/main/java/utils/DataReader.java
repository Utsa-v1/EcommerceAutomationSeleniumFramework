package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class DataReader {

    public String getTestStatus(String sheetName, String testCaseName) {

        try {

            FileInputStream file =
                    new FileInputStream("src/main/resources/testdata.xlsx");

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            Sheet sheet = workbook.getSheet(sheetName);

            for (Row row : sheet) {

                String testCase =
                        row.getCell(0).getStringCellValue();

                String status =
                        row.getCell(1).getStringCellValue();

                if (testCase.equalsIgnoreCase(testCaseName)) {

                    workbook.close();

                    return status;
                }
            }

            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "skip";
    }
}
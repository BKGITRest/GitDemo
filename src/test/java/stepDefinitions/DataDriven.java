package stepDefinitions;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriven {

    //Identifying Column By Scanning the Entire First Row


    public ArrayList<String> getData(String testCaseName) throws IOException {

        ArrayList<String> a = new ArrayList<String>();

        //FileInputtreamArgument
        FileInputStream stream = new FileInputStream("src/main/resources/TestData/EmployeeDetails_TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(stream);
        Integer countOfSheets = workbook.getNumberOfSheets();
        System.out.println("The CountOfSheets is :" + countOfSheets);

        for (int i = 0; i < countOfSheets; i++) {

            if (workbook.getSheetName(i).equalsIgnoreCase("Employee_Details")) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                System.out.println("The Sheet Name is :" + sheet.getSheetName());

                Iterator<Row> row = sheet.iterator();// Sheet is the collection of Rows
                Row firstRow = row.next();
                Iterator<Cell> coulmn = firstRow.cellIterator();   //Row is the collection of cells
                int k = 0;
                int column = 0;
                while (coulmn.hasNext()) {
                    Cell value = coulmn.next();
                    if (value.getStringCellValue().equalsIgnoreCase("Testcases")) {
                        //desired column
                        column = k;

                    }
                    k++;
                }
                System.out.println("The index of TestCases cell is :" + column);

                //once coulumn is identified then scan the entire testcase column to identify the purchase testcase row
                while (row.hasNext()) {

                    Row r = row.next();
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {

                            Cell c = cv.next();
                            if (c.getCellType() == CellType.STRING) {
                                a.add(c.getStringCellValue());
                            } else if (c.getCellType() == CellType.NUMERIC) {
                                a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }

                        }

                    }
                }

            }
        }
        return a;
    }


}

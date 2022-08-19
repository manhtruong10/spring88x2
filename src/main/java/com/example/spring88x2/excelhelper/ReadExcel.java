package com.example.spring88x2.excelhelper;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ReadExcel {
    public static void read() throws IOException {
        //Tạo mới luồng đầu vào dữ liệu từ pathname: fileInputStream
    FileInputStream ios = new FileInputStream(
            new File("C:\\Users\\NMT\\Documents\\ThinhTienTech\\users1.xls"));
    // Tạo mới đối tường workbook có dữ liệu từ luồng đầu vào file InputStream
    XSSFWorkbook workbook = new XSSFWorkbook(ios);

    // Lấy ra sheet đầu tiên từ workbook
    XSSFSheet sheet = workbook.getSheetAt(0);

    // Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
    Iterator<Row> rowIterator = sheet.iterator();

        //Sử dụng hasNext của đối tượng Interator để lặp lại tất cả các dòng của sheet
       while (rowIterator.hasNext()) {
           //Lấy từng phần tử tiếp theo row
        Row row = rowIterator.next();

        // Lấy Iterator cho tất cả các cell của dòng hiện tại.
        Iterator<Cell> cellIterator = row.cellIterator();

        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();

            // Lấy cellType của cell
            CellType cellType = cell.getCellType();

            switch (cellType) {
                case _NONE:
                    System.out.print("");
                    System.out.print("\t");
                    break;
                case BOOLEAN:
                    System.out.print(cell.getBooleanCellValue());
                    System.out.print("\t");
                    break;
                case BLANK:
                    System.out.print("");
                    System.out.print("\t");
                    break;
                case FORMULA:

                    // Công thức
                    System.out.print(cell.getCellFormula());
                    System.out.print("\t");
                    //Tính toán kết quả của công thức trong trang tính excel
                    FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

                    // In ra giá trị từ công thức
                    System.out.print(evaluator.evaluate(cell).getNumberValue());
                    break;
                case NUMERIC:
                    System.out.print(cell.getNumericCellValue());
                    System.out.print("\t");
                    break;
                case STRING:
                    System.out.print(cell.getStringCellValue());
                    System.out.print("\t");
                    break;
                case ERROR:
                    System.out.print("!");
                    System.out.print("\t");
                    break;
            }

        }
        System.out.println("");
        }
        ios.close();
        workbook.close();
    }
}


package com.example.spring88x2.excelhelper;

import com.example.spring88x2.entity.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.persistence.criteria.From;
import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER;

public class ExcelExporter {
    public static String formatName(String originalName){
        String fullName = originalName.replaceAll("[^[a-zA-Z\s]]+","");
        String newFullName = "";
        char[] chars = fullName.toCharArray();

        if(chars[0] >= 97 && chars[0] <= 122){
            chars[0] -= 32;
        }

        for (int i = 1; i<chars.length; i++){
            char c = chars[i];
            if(c == 32){
                i++;
                if(chars[i] >= 97 && chars[i] <= 122){
                    chars[i] -= 32;
                }
            }
        }
        newFullName = String.valueOf(chars);
        return  newFullName;
    }
    public static byte[] excelFileExporter(List<User> list) throws IOException, InvalidFormatException, InterruptedException {
        File file = new File(ExcelExporter.class.getResource("/templates/export_user.xls").getFile());

        // Tạo mới một XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        // Tạo mới sheet có sheet name là user
        XSSFSheet sheet = workbook.getSheet("Users");

        //Tạo mới sheet có vị trí 0
        //XSSFSheet sheet = workbook.createSheet(0);

        // Tạo mới một row (hàng)
        Row row;
        //Tạo mới cell (ô)
        Cell cell;
        //Tạo biến rowNum lưu thứ tự hàng
        int rowNum = 3;

        //Lặp toàn bộ list User lấy giá trị trong db ghi vào excel
        for (User u : list) {
            row = sheet.createRow(rowNum);

            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(rowNum-2);
            CellStyle style = workbook.createCellStyle();
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setAlignment(CENTER);
            //Định dạng font chữ
            Font font = workbook.createFont();
            font.setColor(Font.COLOR_RED);
            style.setFont(font);
            cell.setCellStyle(style);

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(u.getUsername());

            cell = row.createCell(2, CellType.STRING);
            String fullname = "";
            if(u.getLast_name()==null){
                fullname = u.getFirst_name();
            }else if(u.getFirst_name()==null){
                fullname = u.getLast_name();
            }else{
                fullname = u.getFirst_name() + " " + u.getLast_name();
            }
            cell.setCellValue(NameUtils.formatName(fullname));

            //Dinh dang ten
            //Nguyen Văn A
//            String newFullName = "";
//            char[] chars = fullname.toCharArray();
//
//            if(chars[0] >= 97 && chars[0] <= 122){
//                chars[0] -= 32;
//            }
//
//            for (int i = 1; i<chars.length; i++){
//                char c = chars[i];
//                if(c == 32){
//                    i++;
//                    if(chars[i] >= 97 && chars[i] <= 122){
//                        chars[i] -= 32;
//                    }
//                }
//            }
//            newFullName = String.valueOf(chars);



            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue(u.getAge());
            //set style
            CellStyle style1 = workbook.createCellStyle();
            style1.setVerticalAlignment(VerticalAlignment.CENTER);
            style1.setAlignment(CENTER);
            cell.setCellStyle(style1);


            rowNum++;
        }
        //Tạo mới luồng đầu vào ous cho file
        ByteArrayOutputStream  ous = new ByteArrayOutputStream();

        //Ghi dữ liệu từ workbook vào ous
        workbook.write(ous);
        //Đóng ous
        ous.close();
        workbook.close();
        return ous.toByteArray();
        w4343434434343
    }
}

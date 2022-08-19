package com.example.spring88x2.excelhelper;

import com.example.spring88x2.entity.User;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WordFileExporter {

    public static void wordFile(List<User> list) throws IOException {
        //Tạo mới một document
        XWPFDocument document = new XWPFDocument();

        //Tạo đối tượng paragraph trong file word
        XWPFParagraph paragraph = document.createParagraph();
        //Tạo đối tượng run để thêm text vào paragraph
        XWPFRun run = paragraph.createRun();
        run.setText("Bảng danh sách User");

        XWPFParagraph paragraph1 = document.createParagraph();
        run = paragraph1.createRun();
        run.setText("abc123");

        //Tạo table cho document
        XWPFTable table = document.createTable();

        //Tạo đối tượng wordExtractor để lấy text từ file word
        //XWPFWordExtractor wordExtractor = new XWPFWordExtractor(document);

        //Tạo bines numrow lưu thứ tự hàng
        int numrow = 0;
        //Tạo hàng cho table
        XWPFTableRow tableRow;
        //Tạo hàng thứ tự numrow
        tableRow = table.getRow(numrow);
        //Cập nhật giá trị 'First_name' cho ô 0 của tableRow
        tableRow.getCell(0).setText("First_name");
        //Tạo ô mới vị trí tiếp theo với giá trị 'Last_name'
        tableRow.addNewTableCell().setText("Last_name");
        //Tạo ô mới vị trí tiếp theo với giá trị 'username'
        tableRow.addNewTableCell().setText("username");

        //Lặp lại các user trong list
        for (User u : list) {
            //Tạo mới một hàng
            tableRow = table.createRow();
            //Cập nhật giá trị cho ô 0
            tableRow.getCell(0).setText(u.getFirst_name());
            //Cập nhật giá trị cho ô 1
            tableRow.getCell(1).setText(u.getLast_name());
            //Cập nhật giá trị cho ô 2
            tableRow.getCell(2).setText(u.getUsername());
        }



        //Tạo mới file có pathname (đường dẫn) tên 'users.docx'
        File file = new File("C:\\Users\\NMT\\Documents\\ThinhTienTech\\users.docx");
        //Kiểm tra thư mục hiện có và việc tạo thành công nếu chưa tồn tại
        file.getParentFile().mkdir();
        //Tạo mới luồng đầu ous vào cho file
        FileOutputStream ous = new FileOutputStream(file);
        //Ghi giá trị vào ous
        document.write(ous);
        ous.close();
        document.close();
        //In ra đường dẫn file
        System.out.println("create word: " + file.getAbsolutePath());
    }
}

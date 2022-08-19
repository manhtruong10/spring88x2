package com.example.spring88x2.excelhelper;

import com.example.spring88x2.entity.User;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Transactional(rollbackOn = Exception.class)
public class ReadExcelWriteUser {
    public static List<User> addUser() throws IOException {
        //Tạo đối tượng ios - luồng đầu vào dữ liệu
        FileInputStream ios = new FileInputStream(new File("C:\\Users\\NMT\\Documents\\ThinhTienTech\\adduser.xlsx"));
        //Tạo mới một worbook có dữ liệu lấy từ ios
        XSSFWorkbook workbook = new XSSFWorkbook(ios);
        //Tạo sheet lấy từ worbook(0)
        XSSFSheet sheet = workbook.getSheetAt(0);
        //Lấy ra iterator cho tất cả các row của sheet
        Iterator<Row> iterator = sheet.iterator();
        //Tạo mới fmt định dạng giá trị lưu trong ô excel
        DataFormatter fmt = new DataFormatter();
        //Lấy ra row đầu tiên
        Row firstRow = iterator.next();

        //Tạo danh sách User: list
        List<User> list = new ArrayList<User>();

        //Lặp các row còn lại
        while(iterator.hasNext()){
            Row row = iterator.next();
            User user = new User();
            //Cập nhật giá trị cho biến Age sử dụng fmt định dạng giá trị trong ô vị trí 0
            user.setAge(Integer.valueOf(fmt.formatCellValue(row.getCell(0))));
            //Cập nhật giá trị cho biến first_name được lấy từ ô 1
            user.setFirst_name(row.getCell(1).getStringCellValue());
            //Cập nhật giá trị cho biến last_name được lấy từ ô 2
            user.setLast_name(row.getCell(2).getStringCellValue());
            //Cập nhật giá trị cho biến Password được lấy từ ô 3
            user.setPassword(row.getCell(3).getStringCellValue());
            //Cập nhật giá trị cho biến username được lấy giá trị từ ô 4
            user.setUsername(row.getCell(4).getStringCellValue());

            //Thêm user vào list
            list.add(user);
        }

        //In ra toàn bộ user có trong list
        for (User u : list) {
            System.out.println(u.toString());
        }
        ios.close();
        //Đóng workbook
        workbook.close();

        return list;
    }
}

package com.example.spring88x2.excelhelper;

import com.example.spring88x2.entity.User;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadWordWriteUser {
    public static List<User> addUserWord() throws IOException {
        //Tạo mới luồng vào ios cho file 'TableUser'
        FileInputStream ios = new FileInputStream(new File("C:\\Users\\NMT\\Documents\\ThinhTienTech\\TableUser.docx"));
        //Tạo mới một XWPDFDocument
        XWPFDocument document = new XWPFDocument(ios);
        //Tạo mới danh sách list lưu Paragraphs
        List<XWPFParagraph> list = document.getParagraphs();
        //Tạo danh sách listUser
        List<User> listUser = new ArrayList<>();

        for (XWPFParagraph p:list
             ) {
//            System.out.println(p.getText());
            //Lấy paragraph lưu vào str
            String str = p.getText();
            User user = new User();
            //Tách str thàng mảng string với split
            String[] strs = str.split("\s+|\t");
            //Cập nhật giá trị cho user
            user.setUsername(strs[1]);
            user.setPassword(strs[2]);
            user.setFirst_name(strs[3]);
            user.setLast_name(strs[4]);
            user.setAge(Integer.valueOf(strs[5]));
            System.out.println(user.toString());
            listUser.add(user);
        }
//        int i = 1;
//        while(i < list.size()){
//            User user = new User();
//            i++;
//            XWPFParagraph p = list.get(i);
//            user.setUsername(p.getText());
//
//            i++;
//            p = list.get(i);
//            user.setPassword(p.getText());
//
//            i++;
//            p = list.get(i);
//            user.setFirst_name(p.getText());
//
//            i++;
//            p = list.get(i);
//            user.setLast_name(p.getText());
//
//            i++;
//            p = list.get(i);
//            user.setAge(Integer.valueOf(p.getText()));
//
//            listUser.add(user);
//            System.out.println(user.toString());
//            i++;
//        }

//        XWPFWordExtractor wordExtractor = new XWPFWordExtractor(document);
//
//        System.out.println(wordExtractor.getText());
        ios.close();
        document.close();
        return listUser;
    }
}

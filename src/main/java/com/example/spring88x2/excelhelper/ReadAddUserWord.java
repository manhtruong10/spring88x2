package com.example.spring88x2.excelhelper;

import com.example.spring88x2.entity.User;
import com.example.spring88x2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAddUserWord {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("C:\\Users\\NMT\\Documents\\ThinhTienTech\\ListUser.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        line = bufferedReader.readLine();
        List<User> list = new ArrayList<User>();
        while (line!=null){
            line = bufferedReader.readLine();
            User user = new User();
            user.setUsername(line);
            line = bufferedReader.readLine();
            user.setPassword(line);
            line = bufferedReader.readLine();
            user.setFirst_name(line);
            line = bufferedReader.readLine();
            user.setLast_name(line);
            line = bufferedReader.readLine();
            user.setAge(Integer.valueOf(line));
            list.add(user);
            System.out.println(user.toString());
            line = bufferedReader.readLine();
        }
    }
}

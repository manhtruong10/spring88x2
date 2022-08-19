package com.example.spring88x2.excelhelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NameUtil {
    public static String upercaseFirstChar(String str){
        if(str == null || "".equals(str.trim())) {
            return null;
        }

        //Trim spacing in prefix and suffix
        str = str.trim();

        //Check string's length = 1
        if(str.length() == 1) {
            return str.toUpperCase();
        }

        //Other case
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public static String formatName(String ...strs) {
        if(strs==null || strs.length==0) {
            return null;
        }

        List<String> newStrs = new ArrayList<>();
        //Remove null value & format
        for(int i=0; i<strs.length; ++i) {
            String newStr = upercaseFirstChar(strs[i]);
            if(newStr==null) {
                continue;
            }

            newStrs.add(newStr);
        }

        return newStrs.stream().collect(Collectors.joining(" "));
    }
    public static void main(String[] args) {
        System.out.println(formatName("Nguyễn", "Văn", "A"));
        System.out.println(formatName("NguyễN", "VăN", "a"));
        System.out.println(formatName("Nguyễn", "Văn", "AnH"));
        System.out.println(formatName(null, "a"));
        System.out.println(formatName("a", null));
        System.out.println(formatName("アイウエオ", null));
    }
}

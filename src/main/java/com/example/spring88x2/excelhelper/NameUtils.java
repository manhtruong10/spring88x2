package com.example.spring88x2.excelhelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NameUtils {
    public static Map<Character, Character> specialCharMap = new HashMap<Character, Character>()
    {
        {
            put('á', 'Á');
            put('ă', 'Ă');
            put('â', 'Â');
            put('à', 'À');
            put('ã', 'Ã');
            put('ạ', 'Ạ');
            put('ả', 'Ả');
            put('ầ', 'Ầ');
            put('ẩ', 'Ẩ');
            put('ậ', 'Ậ');
            put('đ', 'Đ');
            put('ê', 'Ê');
            put('ô', 'Ô');
        }
    };

    public static char uperCaseChar(char c){
        if(c == '\0'){
            return '\0';
        }
        if (c >= 97 && c <= 122) {
            c -= 32;
        }

       if(specialCharMap.containsKey(c)) {
           Character uperCaseC = specialCharMap.get(c);
           return uperCaseC;
       }

       return c;
    }

    public static char lowerCaseChar(char c){
        if(c == '\0'){
            return '\0';
        }

        if (c >= 65 && c <= 90) {
            c += 32;
        }

        for (Map.Entry entry : specialCharMap.entrySet()) {
            if(entry.getValue().equals(c)) {
                Object key = entry.getKey();
                if(!Objects.equals(null, key)) {
                    return (char)key;
                }
            }
        }

        return c;
    }
    public static String formatName(String originalName) {
        if (originalName == null || originalName.trim() == "") {
            return null;
        }
//      originalName = originalName.replaceAll("[^[a-zA-Z\s]]+","");
        String newFullName = "";
        char[] chars = originalName.toCharArray();
        chars[0]=uperCaseChar(chars[0]);

        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            if (c == 32) {
                i++;
                chars[i]=uperCaseChar(chars[i]);
            } else {
                chars[i] = lowerCaseChar(chars[i]);
            }
        }
        newFullName = String.valueOf(chars);
        return newFullName;
    }


    public static void main(String[] args) {
        System.out.println(formatName("NguyỄn Văn đÊ"));
//        System.out.println(formatName("NguyễN VăN a"));
//        System.out.println(formatName("Nguyễn Văn AnH"));
//        System.out.println(formatName("a"));
//        System.out.println(formatName("          "));
//        System.out.println(formatName("アイウエオ"));
    }
}
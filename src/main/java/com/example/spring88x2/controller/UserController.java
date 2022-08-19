package com.example.spring88x2.controller;

import com.example.spring88x2.excelhelper.*;
import com.example.spring88x2.service.dto.IncrementAgeDTO;
import com.example.spring88x2.service.dto.LogginDTO;
import com.example.spring88x2.entity.User;
import com.example.spring88x2.service.UserService;
import com.google.common.hash.Hashing;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("users")
    public List<User> userList(@RequestParam(name = "pageIndex") int pageIndex){
        int pageSize = 5;
        return service.getAllUser(pageIndex, pageSize);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity deleteUser(@PathVariable(name = "id") Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("user")
    public ResponseEntity addUser(@RequestBody User user){
        //Mã hóa password h256
        String passwordDamahoa = Hashing.sha256()
                .hashString(user.getPassword(), StandardCharsets.UTF_8)
                .toString();
        user.setPassword(passwordDamahoa);

        user.setIsDeleted(false);


        service.save(user);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("user/login")
    public ResponseEntity login(@RequestBody LogginDTO loginDTO){

        User user = service.findUserByUsernameAndPassword(loginDTO);

        if(user == null){
            return ResponseEntity.badRequest().body("user khong ton tai");
        }

        if(user.isIsDeleted() == true){
            return ResponseEntity.badRequest().body("ban khong co quyen truy cap");
        }

        return ResponseEntity.ok().body(user);
    }

    @PutMapping("user/{id}")
    public ResponseEntity editUser(@PathVariable(name = "id") Integer id,
                                   @RequestBody User user){
        service.save(user);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("user/age/increment")
    public ResponseEntity incrementAge(@RequestBody IncrementAgeDTO incrementAgeDTO){
        service.incrementAge(incrementAgeDTO);
        return ResponseEntity.ok().body(true);
    }

    @PostMapping("user/lastname/increment")
    public ResponseEntity incrementLastName(@RequestBody IncrementAgeDTO incrementAgeDTO){
        service.incrementLastName(incrementAgeDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("userss")
    public List<User> userss(@RequestParam(name = "pageIndex") int pageIndex,
                             @RequestParam(name = "keyword") String keyword){
        int pageSize = 5;
        return service.getAll(keyword, pageIndex, pageSize);
    }

    @GetMapping("user/{id}")
    public User getUser(@PathVariable(name = "id") Integer id){
        return service.findById(id);
    }

    //------------------------------------------------------------------------

    @GetMapping("user/excel")
    public ResponseEntity download() throws IOException, InvalidFormatException, InterruptedException {
        List<User> users = service.listAll();
        byte[] bytes = ExcelExporter.excelFileExporter(users);
//        ReadExcel.read();
        return ResponseEntity.ok().body(bytes);
    }
    @GetMapping("user/word")
    public void downloadWord() throws IOException {
        List<User> users = service.listAll();
        WordFileExporter.wordFile(users);
    }

    @GetMapping("user/addexcel")
    public void addExcel() throws IOException {
        List<User> list = ReadExcelWriteUser.addUser();
        for (User u:list) {

            String passwordDamahoa = Hashing.sha256()
                    .hashString(u.getPassword(), StandardCharsets.UTF_8)
                    .toString();
            u.setPassword(passwordDamahoa);

            u.setIsDeleted(false);

            service.save(u);
        }
    }

//    @GetMapping("user/readword")
//    public void readWord() throws IOException {
//        ReadWord.readWordUser();
//    }
    @GetMapping("user/addword")
    public ResponseEntity addWord() throws IOException {
        List<User> list = ReadWordWriteUser.addUserWord();
        for (User u:list) {
            String passwordDamahoa = Hashing.sha256()
                    .hashString(u.getPassword(), StandardCharsets.UTF_8)
                    .toString();
            u.setPassword(passwordDamahoa);

            u.setIsDeleted(false);

            service.save(u);
        }
        return ResponseEntity.ok().body("ok");
    }

}

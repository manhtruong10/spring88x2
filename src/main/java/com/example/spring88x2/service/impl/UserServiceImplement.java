package com.example.spring88x2.service.impl;

import com.example.spring88x2.service.UserService;
import com.example.spring88x2.service.dto.IncrementAgeDTO;
import com.example.spring88x2.service.dto.LogginDTO;
import com.example.spring88x2.repository.UserRepository;
import com.example.spring88x2.entity.User;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUser(int pageIndex, int pageSize){


        int fromIndex = pageSize*(pageIndex-1);
        return repository.findAll(fromIndex, pageSize, false);
    }

    @Override
    public List<User> getAll(String keyword, int pageIndex, int pageSize) {
        int fromIndex = pageSize*(pageIndex-1);
        return repository.findAllUser(false, keyword, fromIndex, pageSize);
    }

    @Override
    public List<User> listAll() {
        List<User> users = repository.findAll();
        return users;
    }


    public User findById(Integer id){
        Optional<User> opt =  repository.findById(id);
        if(opt.isEmpty()) return null;

        User user = opt.get();
        if(user.isIsDeleted() == true) {
            System.out.println("user was deleted");
            return null;
        }

        return  user;
    }

    public void save(User user){
        repository.save(user);
    }

    public void delete(Integer id){
        Optional<User> opt =  repository.findById(id);
        if(opt.isEmpty()) return;

        User user = opt.get();

        if(user == null) {
            System.out.println("user không tồn tại");
            return;
        }

        user.setIsDeleted(true);
        save(user);
    }

    public User findUserByUsernameAndPassword(LogginDTO loginDTO) {

        String passwordDamahoa = Hashing.sha256()
                .hashString(loginDTO.getPassword(), StandardCharsets.UTF_8)
                .toString();
        loginDTO.setPassword(passwordDamahoa);

        User user = repository.findUsersByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        return user;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void incrementAge(IncrementAgeDTO incrementAgeDTO) {
        //Lay tat ca user theo ids
        List<User> users = repository.findAllByIdIn(incrementAgeDTO.getUserIds());

        //Kiem tra tuoi < 0
        Boolean isContains = false;
        for (User u : users) {
            if(u.getAge()<0){
                isContains = true;
                break;
            }
        }

        //Neu khong ton tai tuoi <0 thif update tuoi
        if(!isContains){
            for (User u : users) {
                int newAge = u.getAge() + 1;
                u.setAge(newAge);
            }

            //Cap nhat tuoi cua tat ca user kia len 1 don vi
            repository.saveAll(users);
        }


//        //Neu gap user co tuoi < 0 thi rollback(throw ra loi) de baor toanf duwx lieu ban dau
//        users = repository.findAllByIdIn(incrementAgeDTO.getUserIds());
//        for (User u : users) {
//            int newAgeAfterUpdate = u.getAge();
//            if(newAgeAfterUpdate<=0){
//                throw new Exception();
//            }
//        }

    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void incrementLastName(IncrementAgeDTO incrementAgeDTO) {
        List<User> userList = repository.findAllByIdIn(incrementAgeDTO.getUserIds());

        for (User u: userList) {
            if(u.getAge()>0){
                String lastName = u.getLast_name() + "abc";
                u.setLast_name(lastName);
            }
        }
        repository.saveAll(userList);
    }
}

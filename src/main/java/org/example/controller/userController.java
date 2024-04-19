package org.example.controller;

import org.example.DAO.UserRepository;
import org.example.DAO.userJdbcDao;
import org.example.model.User;
import org.example.service.userServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpHeaders;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class userController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    userJdbcDao userJdbcDao;
    @Autowired
    userServiceImpl userService;
    @GetMapping("/name")
    public String getName(){
        return "Hello,World12312312";
    }

    @PostMapping("/create")
    public String create(@RequestBody User user){
        if(user==null){
            return "無資料無法新增";
        }
        //userRepository.save(user);
        userJdbcDao.createUser(user);
        return "新增成功";
    }
    @PostMapping("/createMutiUser")
    public String addMutiUser(@RequestBody List<User> users){
        for(User user:users){
            if(user==null){
                return "資料不能空白";
            }
        }
        userRepository.saveAll(users);

        return "新增成功";
    }

    @GetMapping("/findAll")
    public List<User> findAll(){

        //return userRepository.findAll();
        return userJdbcDao.getAllUsers();
    }

    @PostMapping("/update/{id}")
    public String updateUser(@RequestBody User user,@PathVariable int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userJdbcDao.updateUser(user,id);
//            User newuser = optionalUser.get();
//            newuser.setFirstName(user.getFirstName());
//            newuser.setLastName(user.getLastName());
//            newuser.setAge(user.getAge());
//            newuser.setAddress(user.getAddress());
//            newuser.setHeight(user.getHeight());
//            newuser.setWeight(user.getWeight());
//            userRepository.save(newuser);
            return  "修改成功";
        } else {
            return "無此用戶"; // 如果找不到對應的用戶，返回null
        }
    }


    @DeleteMapping("/delete/{id}")
    public  String delete(@PathVariable int id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            userJdbcDao.deleteUser(id);
            //userRepository.deleteById(id);
            return "刪除成功";
        }
        return "刪除失敗";
    }

    @GetMapping("downloadExcel")
    public byte[] downloadExcel(){
        byte[] excelByte=userService.excelDownload();
        String fileName="user.xlsx";
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment",fileName);
        return excelByte;
    }

}

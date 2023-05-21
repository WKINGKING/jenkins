package cn.itsource.controller;

import cn.itsource.entity.User;
import cn.itsource.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author wangkui
 * @date 2023-05-21 17:22
 * @description:
 * @version:
 */
@RestController
public class LoginController {
    @Autowired
    UserServiceImpl userService;
    @PostMapping("/userLogin")
    public String login(@RequestBody User requestUser){
        String username=requestUser.getName();
        String password=requestUser.getPwd();
        Map<String, Object> map = new HashMap<>();
        List<User> list=new ArrayList<>();
        list=userService.queryByNameAndPwd(username,password);
        if (list.size()>0){
            System.out.println("登录成功");
            return "登录成功";
        }else{
            System.out.println("登录失败");
            return "登录失败";
        }
    }
}

package cn.itsource.controller;

import cn.itsource.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author wangkui
 * @date 2023-05-21 17:22
 * @description:
 * @version:
 */
@RestController
public class LoginController {
    @CrossOrigin
    @PostMapping("/userLogin")
    public String login(@RequestBody User requestUser){
        String username=requestUser.getName();
        String password=requestUser.getPwd();
        if (Objects.equals(username,"test")&&Objects.equals(password,"123456")){
            System.out.println("登录成功");
            return "登录成功";
        }else{
            System.out.println("登录失败");
            return "登录失败";
        }
    }
}
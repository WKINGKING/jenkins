package cn.itsource.controller;

import cn.itsource.entity.Mail;
import cn.itsource.service.SendMailSevice;
import cn.itsource.service.UserServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>TestController</p>
 *
 * @author 波波老师(微信 ： javabobo0513)
 */
@RestController
@Slf4j
public class TestController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    private SendMailSevice sendMailSevice;

    @GetMapping("/test")
    public String test(){
        System.out.println("System.out.println-欢迎来到SpringBoot奇妙世界！");
        log.info("log.info-欢迎来到SpringBoot奇妙世界！");
        return userService.queryAll().toString();
    }

    @GetMapping("/test1")
    public void test1(){
        Mail mail=new Mail();
        mail.setSubject("this is a subject test");
        mail.setContent("this is contest test");
        mail.setToUsers("wkinging@126.com");
        String toUsers = mail.getToUsers().trim();

        toUsers = toUsers.replace(" ", "");
        toUsers = toUsers.replace("；", ";");
        String[] strings = toUsers.split(";");

        List<String> toUser = Arrays.asList(strings);
        toUser = toUser.stream().filter(u -> !StringUtils.isBlank(u)).map(u -> u.trim()).collect(Collectors.toList());
        toUser.forEach(u -> {
            int status = 1;
            try {
                sendMailSevice.sendMail(u, mail.getSubject(), mail.getContent());
            } catch (Exception e) {
                log.error("发送邮件失败", e);
                status = 0;
            }
        });

    }

}
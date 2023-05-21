package cn.itsource;


import cn.itsource.entity.User;
import cn.itsource.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangkui
 * @date 2023-05-17 11:31
 * @description:
 * @version:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAppTest {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void contextLoads() {
       /* User user=new User("王逵",18);
        redisTemplate.opsForValue().set("user",user);
        System.out.println(redisTemplate.opsForValue().get("user"));*/
    }

    @Test
    public void contextLoads1() {
        redisTemplate.opsForValue().set("name","yixin");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }

    @Autowired
    UserServiceImpl userService;

    @Test
    public void queryAll() {
        userService.queryAll().forEach(System.out::println);
    }

    @Test
    public void query() {
        userService.queryAll().forEach(System.out::println);

        userService.queryByName("test1").forEach(System.out::println);
        userService.queryByNameMap("test1").forEach(System.out::println);
    }

}

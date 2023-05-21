package cn.itsource.service;
import cn.itsource.entity.User;
import cn.itsource.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl {
    @Autowired
    UserMapper userMapper;
    //查询全部
    public List<User> queryAll() {
        return userMapper.selectList(null);
    }

    public User queryById(int id){
        return userMapper.selectById(id);
    }

    public List<User> queryByName(String name){
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.like("name",name);
        return userMapper.selectList(userQueryWrapper);
    }

    public List<User> queryByName2(String name) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", name);//参数为表中的列名，要查询的条件 相当于 WHERE name = name
        return userMapper.selectList(userQueryWrapper);
    }

    public List<User> queryByNameMap(String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        return userMapper.selectByMap(map);
    }
}


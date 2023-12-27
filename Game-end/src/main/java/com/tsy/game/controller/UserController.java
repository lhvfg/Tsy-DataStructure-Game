package com.tsy.game.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tsy.game.dao.UserDao;
import com.tsy.game.domain.Result;
import com.tsy.game.domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.text.ParseException;

@RestController
@Transactional
public class UserController {
    @Autowired
    UserDao userDao;
    @PostMapping("/register")
    public Result add(@RequestBody User user){
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        Result result = new Result();
        if (user.getUserName()!=null&&user.getPassword()!=null)
        {
            LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
            lqw.eq(User::getUserName,user.getUserName());
            System.out.println("条件设置成功");
            User user1 =userDao.selectOne(lqw);
            System.out.println("查询成功");
            if(user1==null)
            {
                user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
                userDao.insert(user);
                result.setStatus("registerSucceed");
            }
            else {
                result.setStatus("renameFail");
            }
        }
        else {
            result.setStatus("nullAgainst");
        }
        return result;
    }
    @PostMapping("/login")
    public Result login (@RequestBody User user, HttpSession httpSession) throws ParseException {
        Result result;
        String userName = user.getUserName();
        String password = user.getPassword();
        User loginUser = userDao.selectOne( new LambdaQueryWrapper<User>().eq(User::getUserName,userName));
        if(loginUser == null)
        {
            return new Result("UserNotExist");
        }
        Integer userId = loginUser.getId();
        if (loginUser.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
            result = new Result(userId,"loginSucceed",userName,loginUser.getSucceedNum(),loginUser.getFastTime());
        }
        else {
            result = new Result("PasswordWrong");
        }
        return result;
    };

    @PostMapping("/changeSuccessNum")
    public Result changeSuccessNum(@RequestBody User user){
        Result result = new Result();
        User updateUser = userDao.selectOne( new LambdaQueryWrapper<User>().eq(User::getId,user.getId()));
        System.out.println("改变次数"+user);
        updateUser.setSucceedNum(user.getSucceedNum());
        userDao.updateById(updateUser);
        result.setStatus("changeNumSuccess");
        return result;
    }

    @PostMapping("/changeFastTime")
    public Result changeFastTime(@RequestBody User user){
        Result result = new Result();
        System.out.println("改变时间"+user);
        User updateUser = userDao.selectOne( new LambdaQueryWrapper<User>().eq(User::getId,user.getId()));
        updateUser.setFastTime(user.getFastTime());
        userDao.updateById(updateUser);
        result.setStatus("changeTimeSuccess");
        return result;
    }


}

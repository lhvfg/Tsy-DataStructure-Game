package com.tsy.game.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tsy.game.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDao extends BaseMapper<User> {
    @Select("select * from user where id = #{id}")
    User findById(Integer id);
}

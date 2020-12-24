package com.jiacool.mapper;

import com.jiacool.domain.User;

import java.util.List;

public interface UserMapper {
//    执行保存操作 插入数据、、将java中的Date类型转化为数据库中的bigint毫秒型
    public void save(User user);

//    查询操作 将数据库中的bigint型转化为java中的Date型
    public User findById(int id);

//    查询所有记录
    public List<User> findAll();
}

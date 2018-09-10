package com.learn.dao;

import com.learn.entity.UserEntity;

import java.util.List;

public interface UserDao {
    int save(UserEntity u);

    /** 获取所有用户
     * @return
     */
    List<UserEntity> findAll();

    /** 通过Uid 获取用户
     * @param id
     * @return
     */
    List<UserEntity> getUserByUid(int id);

    int login(UserEntity user);
    boolean Register(UserEntity user);

    /** 通过Uid 删除用户
     * @param id
     * @return
     */
    boolean Delete(int id);
}

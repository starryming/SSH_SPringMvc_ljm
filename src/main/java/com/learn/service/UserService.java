package com.learn.service;

import com.learn.entity.UserEntity;

import java.util.List;

public interface UserService {
    void saveUsers(List<UserEntity> us);
    List<UserEntity> getAllUser();
    List<UserEntity> getUserByUid(int id);

    int login(UserEntity user);
    boolean Register(UserEntity user);
    boolean Delete(int id);
}

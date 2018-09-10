package com.learn.service.impl;

import com.learn.dao.UserDao;
import com.learn.entity.UserEntity;
import com.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public void saveUsers(List<UserEntity> users) {
        for(UserEntity u : users){
            userDao.save(u);
        }
    }

    public List<UserEntity> getAllUser() {
        return userDao.findAll();
    }

    @Override
    public List<UserEntity> getUserByUid(int id) {
        return userDao.getUserByUid(id);
    }

    public int login(UserEntity user) {
        return userDao.login(user);
    }

    public boolean Register(UserEntity user) {
        System.out.println("ServiceImpl:"+user.getUsername());
        return userDao.Register(user);
    }

    public boolean Delete(int id) {
        return userDao.Delete(id);
    }
}

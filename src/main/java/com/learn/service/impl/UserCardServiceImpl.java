package com.learn.service.impl;

import com.learn.dao.UserCardDao;
import com.learn.entity.UsercardEntity;
import com.learn.service.UserCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCardServiceImpl implements UserCardService {

    @Autowired
    UserCardDao userCardDao;

    @Override
    public boolean add(UsercardEntity usercardEntity) {
        return userCardDao.add(usercardEntity);
    }

    @Override
    public boolean update(UsercardEntity usercardEntity) {
        return userCardDao.update(usercardEntity);
    }

    @Override
    public boolean delete(int id) {
        return userCardDao.delete(id);
    }
}

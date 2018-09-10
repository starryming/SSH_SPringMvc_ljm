package com.learn.dao;

import com.learn.entity.UsercardEntity;

public interface UserCardDao {
    boolean add(UsercardEntity usercardEntity);

    boolean update(UsercardEntity usercardEntity);

    boolean delete(int id);
}

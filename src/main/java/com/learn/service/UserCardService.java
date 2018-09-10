package com.learn.service;

import com.learn.entity.UsercardEntity;

public interface UserCardService {
    boolean add(UsercardEntity usercardEntity);

    boolean update(UsercardEntity usercardEntity);

    boolean delete(int id);
}

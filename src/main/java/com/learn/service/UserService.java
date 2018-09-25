package com.learn.service;

import com.learn.entity.UserEntity;
import com.learn.utils.PageBeanUtil;

import java.util.List;

public interface UserService {
    void saveUsers(List<UserEntity> us);
    List<UserEntity> getAllUser();
    List<UserEntity> getUserByUid(int id);

    int login(UserEntity user);
    boolean Register(UserEntity user);
    boolean Delete(int id);

    /**
     * 分页查询
     * @param pageSize  每页显示多少记录
     * @param page 当前页
     * @return 封装了分页信息的bean
     */
    public PageBeanUtil<UserEntity> queryForPage(int pageSize, int page);
}

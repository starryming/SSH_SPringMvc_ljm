package com.learn.dao;

import com.learn.entity.UserEntity;

import java.util.List;

public interface UserDao {
    int save(UserEntity u);

    /**
     * @param userEntity
     * @return boolean
     */
    boolean update(UserEntity userEntity);

    /** 获取所有用户
     * @return
     */
    List<UserEntity> findAll();

    /** 通过Uid 获取用户
     * @param id
     * @return
     */
    List<UserEntity> getUserByUid(int id);

    UserEntity login(UserEntity user);
    boolean Register(UserEntity user);

    /** 通过Uid 删除用户
     * @param id
     * @return
     */
    boolean Delete(int id);


    /**
     * 分页查询
     * @param hql  查询条件
     * @param offset  开始记录
     * @param length  一次查询几条记录
     * @return 查询的记录集合
     */
    public List<UserEntity> queryForPage(final String hql,final int offset,final int length);

    /**
     * 查询所有的记录数
     * @param hql 查询条件
     * @return 总记录数
     */
    public int getAllRowCount(String hql);
}

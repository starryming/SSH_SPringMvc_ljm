package com.learn.dao;

import com.learn.entity.ImgiconEntity;

import java.util.List;

public interface ImgIconDao {
    /** 增加图片
     * @param imgiconEntity
     * @return
     */
    boolean add(ImgiconEntity imgiconEntity);
    List<ImgiconEntity> getAll();
}

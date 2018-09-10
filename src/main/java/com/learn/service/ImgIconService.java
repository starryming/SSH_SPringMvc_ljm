package com.learn.service;

import com.learn.entity.ImgiconEntity;

import java.util.List;

public interface ImgIconService {
    /** 增加图片
     * @param imgiconEntity
     * @return
     */
    boolean add(ImgiconEntity imgiconEntity);
    List<ImgiconEntity> getAll();
}

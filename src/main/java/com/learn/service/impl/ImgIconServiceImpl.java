package com.learn.service.impl;

import com.learn.dao.ImgIconDao;
import com.learn.entity.ImgiconEntity;
import com.learn.service.ImgIconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgIconServiceImpl implements ImgIconService {

    @Autowired
    ImgIconDao imgIconDao;
    /**
     * 增加图片
     *
     * @param imgiconEntity
     * @return
     */
    @Override
    public boolean add(ImgiconEntity imgiconEntity) {
        return imgIconDao.add(imgiconEntity);
    }

    @Override
    public List<ImgiconEntity> getAll() {
        return imgIconDao.getAll();
    }
}

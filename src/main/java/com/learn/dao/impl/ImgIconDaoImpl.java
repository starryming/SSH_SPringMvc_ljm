package com.learn.dao.impl;

import com.learn.dao.ImgIconDao;
import com.learn.entity.ImgiconEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImgIconDaoImpl implements ImgIconDao {

    @Autowired
    SessionFactory sessionFactory;

    /**
     * 增加图片
     *
     * @param imgiconEntity
     * @return
     */
    @Override
    public boolean add(ImgiconEntity imgiconEntity) {
        if((int)sessionFactory.getCurrentSession().save(imgiconEntity) !=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<ImgiconEntity> getAll() {
        return null;
    }
}

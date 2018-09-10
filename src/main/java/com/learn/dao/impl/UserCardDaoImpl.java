package com.learn.dao.impl;

import com.learn.dao.UserCardDao;
import com.learn.entity.UsercardEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserCardDaoImpl implements UserCardDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public boolean add(UsercardEntity usercardEntity) {
        if((int)sessionFactory.getCurrentSession().save(usercardEntity) != 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean update(UsercardEntity usercardEntity) {
        System.out.println("爱好："+usercardEntity.getHobby()+" sex:"+usercardEntity.getSex()+" personality:"+usercardEntity.getPersonalitySignature()+" id:"+usercardEntity.getUsercardId());
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = ("update UsercardEntity u set u.hobby = ?,u.personalitySignature = ?,u.sex = ? where u.usercardId = ?");
        Query query = session.createQuery(hql);
        query.setParameter(0, usercardEntity.getHobby());
        query.setParameter(1, usercardEntity.getPersonalitySignature());
        query.setParameter(2, usercardEntity.getSex());
        query.setParameter(3, usercardEntity.getUsercardId());
        System.out.println("looksql:"+query.toString());
        if(query.executeUpdate() != 0){
            session.getTransaction().commit();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        if (sessionFactory.getCurrentSession().createQuery("delete UsercardEntity u where u.usercardId="+id).executeUpdate() != 0){
            return true;
        }else {
            return false;
        }
    }
}

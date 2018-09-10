package com.learn.dao.impl;

import com.learn.dao.UserDao;
import com.learn.entity.UserEntity;
import com.vdurmont.emoji.EmojiParser;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public int save(UserEntity u) {
        return (Integer) sessionFactory.getCurrentSession().save(u);
    }

    public List<UserEntity> findAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserEntity.class);
        return criteria.list();
    }

    @Override
    public List<UserEntity> getUserByUid(int id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("uid",id));
        return criteria.list();
    }

    public int login(UserEntity user) {
        String str = user.getUsername();
        this.testEnjoy(str);
        user.setUsername(EmojiParser.parseToAliases(str));
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("username",user.getUsername()));
        criteria.add(Restrictions.eq("password",user.getPassword()));
        List userentity = criteria.list();
        if (userentity.size() != 0){
            UserEntity temp = (UserEntity) userentity.get(0);
            System.out.println("Uid获取:"+temp.getUid());
            return  temp.getUid();
        }else{
            return 0;
        }
    }

    public boolean Register(UserEntity user) {
        //支持enjoy表情用户名 测试相关方法
        Timestamp create_date = new Timestamp(System.currentTimeMillis());
        System.out.println(create_date);
        user.setCreatetime(create_date);
        String str = user.getUsername();
        this.testEnjoy(str);
        user.setUsername(EmojiParser.parseToAliases(str));
        if((Integer)sessionFactory.getCurrentSession().save(user) != 0){
            return true;
        }else{
            return false;
        }

    }

    public boolean Delete(int id) {
        if (sessionFactory.getCurrentSession().createQuery("delete UserEntity u where u.id =" + id).executeUpdate() != 0){
            return true;
        }else{
            return false;
        }
    }


    /** 测试Java-Enjoy方法
     * @param str
     */
    public void testEnjoy(String str){
        System.out.println("用户名："+ str);
        System.out.println("用户名Java-enjoy处理："+ EmojiParser.parseToAliases(str));
        System.out.println("去掉表情:"+EmojiParser.parseToAliases(str, EmojiParser.FitzpatrickAction.REMOVE));
        System.out.println("ignore方法:"+EmojiParser.parseToAliases(str, EmojiParser.FitzpatrickAction.IGNORE));
        System.out.println("用户名Java-enjoy处理在转回表情："+ EmojiParser.parseToUnicode(EmojiParser.parseToAliases(str)));
        System.out.println("转回表情Html："+ EmojiParser.parseToHtmlDecimal(EmojiParser.parseToAliases(str)));
    }
}

package com.learn.dao.impl;

import com.learn.dao.Support.MyDaoSupport;
import com.learn.dao.UserDao;
import com.learn.entity.UserEntity;
import com.learn.utils.MD5Util;
import com.vdurmont.emoji.EmojiParser;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.rmi.AccessException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class UserDaoImpl extends MyDaoSupport implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public int save(UserEntity u) {
        u.setUsername(EmojiParser.parseToAliases(u.getUsername()));
        u.setPassword(MD5Util.getMD5(u.getPassword()));
        return (Integer) sessionFactory.getCurrentSession().save(u);
    }

    @Override
    public boolean update(UserEntity u) {
        u.setUsername(EmojiParser.parseToAliases(u.getUsername()));
        u.setPassword(MD5Util.getMD5(u.getPassword()));
        String hql = "update UserEntity u set u.username = ?, u.password = ?, u.phonenum = ?, u.email = ? where u.uid = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, u.getUsername());
        query.setParameter(1, u.getPassword());
        query.setParameter(2, u.getPhonenum());
        query.setParameter(3, u.getEmail());
        query.setParameter(4,u.getUid());
        System.out.println(query.toString());
        if(query.executeUpdate() != 0){
            return true;
        }else{
            return false;
        }
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

    public UserEntity login(UserEntity user) {
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
            return  temp;
        }else{
            return null;
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

    /**
     * 分页查询
     *
     * @param hql    查询条件
     * @param offset 开始记录
     * @param length 一次查询几条记录
     * @return 查询的记录集合
     */
    @Override
    public List<UserEntity> queryForPage(String hql, int offset, int length) {
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(length);
        List<UserEntity> userEntityList = query.list();

        System.out.println(userEntityList.size());

        return userEntityList;
    }

    /**
     * 查询所有的记录数
     *
     * @param hql 查询条件
     * @return 总记录数
     */
    @Override
    public int getAllRowCount(String hql) {
        return this.getHibernateTemplate().find(hql).size();
    }


    /** 测试Java-Emoji方法
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

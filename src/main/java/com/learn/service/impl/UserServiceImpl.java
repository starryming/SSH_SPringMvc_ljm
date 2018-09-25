package com.learn.service.impl;

import com.learn.dao.UserDao;
import com.learn.entity.UserEntity;
import com.learn.service.UserService;
import com.learn.utils.PageBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
//@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public void saveUsers(List<UserEntity> users) {
        for(UserEntity u : users){
            userDao.save(u);
        }
    }

    public List<UserEntity> getAllUser() {
        return userDao.findAll();
    }

    @Override
    public List<UserEntity> getUserByUid(int id) {
        return userDao.getUserByUid(id);
    }

    public int login(UserEntity user) {
        return userDao.login(user);
    }

    public boolean Register(UserEntity user) {
        System.out.println("ServiceImpl:"+user.getUsername());
        return userDao.Register(user);
    }

    public boolean Delete(int id) {
        return userDao.Delete(id);
    }

    /**
     * 分页查询
     *
     * @param pageSize    每页显示多少记录
     * @param page 当前页
     * @return 封装了分页信息的bean
     */
    @Override
    public PageBeanUtil<UserEntity> queryForPage(int pageSize, int page) {
        final String hql = "from UserEntity user order by user.uid"; //查询语句
        int allRow = userDao.getAllRowCount(hql);  //总记录数
        int totalPage = PageBeanUtil.countTatalPage(pageSize, allRow); //总页数
        final int offset = PageBeanUtil.countOffset(pageSize, page); //当前页开始记录
        final int length = pageSize; // 每页记录数
        final int currentPage = PageBeanUtil.countCurrentPage(page); // 当前页

        List<UserEntity> list = userDao.queryForPage(hql, offset, length); //
        //把分页信息保存到Bean当中
        PageBeanUtil<UserEntity> pageBean  = new PageBeanUtil<UserEntity>();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setAllRow(allRow);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(list);
        pageBean.init();

        return pageBean;
    }
}

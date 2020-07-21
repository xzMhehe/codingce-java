package cn.com.codingce.service;

import cn.com.codingce.dao.UserDao;

public class UserServiceImpl implements UserService {

/*    private UserDao userDao = new UserDaoImpl();

    public void getUser() {
        userDao.getUser();
    }*/
    private UserDao userDao;

    public void getUser() {
        userDao.getUser();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    //利用Set进行动态实现值的注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}

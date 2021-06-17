package cn.com.codingce.service.impl;


import cn.com.codingce.dao.UsersDao;
import cn.com.codingce.dao.impl.UsersDaoImpl;
import cn.com.codingce.entity.Users;
import cn.com.codingce.service.UsersService;

import java.util.List;


/**
 * 业务逻辑层  逻辑性的增、删、改(增:查+增，   对dao层进行的组装)
 *
 * @author williamma
 */
public class UsersServiceImpl implements UsersService {

    UsersDao usersDao = new UsersDaoImpl();

    @Override
    public List<Users> queryAllUsers() {
        return usersDao.queryAllUsers();
    }

    @Override
    public Users queryUsersBySno(int sno) {
        return usersDao.queryUsersBySno(sno);
    }

    @Override
    public boolean updateUsersBySno(int sno, Users users) {
        if (usersDao.isExist(sno)) {
            return usersDao.updateUsersBySno(sno, users);
        }
        return false;
    }

    @Override
    public boolean deleteUsersBySno(int sno) {
        if (usersDao.isExist(sno))
            return usersDao.deleteUsersBySno(sno);
        return false;
    }

    @Override
    public boolean addUsers(Users users) {
        if (!usersDao.isExist(users.getSno())) {
            usersDao.addUsers(users);
            return true;
        }
        System.out.println("此人已存在!");
        return false;
    }

    @Override
    public List<Users> queryUsersByPage(int currentPage, int pageSize) {
        return usersDao.queryUsersByPage(currentPage, pageSize);
    }

    @Override
    public int getTotalCount() {
        return usersDao.getTotalCount();
    }

    @Override
    public List<Users> queryAllUsersByName(String username) {
        return usersDao.queryAllUsersByName(username);
    }
}

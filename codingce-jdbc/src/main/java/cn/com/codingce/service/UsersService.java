package cn.com.codingce.service;


import cn.com.codingce.entity.Users;

import java.util.List;


/**
 * @author org
 */
public interface UsersService {

    /**
     * 查询数据总条数
     *
     * @return
     */
    int getTotalCount();

    /**
     * 查询当前数据总条数
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Users> queryUsersByPage(int currentPage, int pageSize);

    /**
     * 查询全部学生
     *
     * @return
     */
    List<Users> queryAllUsers();

    /**
     * 根据学号查询一个人
     *
     * @param sno
     * @return
     */
    Users queryUsersBySno(int sno);

    /**
     * 修改学生
     *
     * @param sno
     * @param users
     * @return
     */
    boolean updateUsersBySno(int sno, Users users);

    boolean deleteUsersBySno(int sno);

    boolean addUsers(Users users);

    List<Users> queryAllUsersByName(String username);
}

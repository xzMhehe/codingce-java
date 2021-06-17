package cn.com.codingce.dao;


import cn.com.codingce.entity.Users;

import java.util.List;

/**
 *
 * @author williamma
 */
public interface UsersDao {

    /**
     * 修改
     *
     * @param sno
     * @param users
     * @return
     */
    boolean updateUsersBySno(int sno, Users users);

    /**
     * 查询总数据数
     *
     * @return
     */
    int getTotalCount();

    /**
     * 查询页    页码     每页显示的数(页面大小)
     *
     * @param currentPage
     * @param pagesSize
     * @return
     */
    List<Users> queryUsersByPage(int currentPage, int pagesSize);

    /**
     * 根据学号删除
     *
     * @param sno
     * @return
     */
    boolean deleteUsersBySno(int sno);

    /**
     * 添加
     *
     * @param users
     * @return
     */
    boolean addUsers(Users users);


    /**
     * 查询全部
     *
     * @return
     */
    List<Users> queryAllUsers();


    /**
     * 模糊查询
     *
     * @param username
     * @return
     */
    List<Users> queryAllUsersByName(String username);


    /**
     * 判断是否存在
     *
     * @param sno
     * @return
     */
    boolean isExist(int sno);


    /**
     * 根据学号
     *
     * @param sno
     * @return
     */
    Users queryUsersBySno(int sno);
}

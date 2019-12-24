package cn.com.codingce.jpa.authentication.core;

import cn.com.coding4fun.hibernate.entity.SysUser;
import cn.com.coding4fun.hibernate.entity.UserInfo;
import org.hibernate.query.internal.QueryImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@SuppressWarnings("unchecked")
@Repository
public class AuthorityRepository {

    @PersistenceContext
    private EntityManager entityManager;


    /**
     * 通过用户名查询用户信息
     *
     * @param loginName
     * @return
     */
    public Optional<UserInfo> findByLoginName(String loginName) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserInfo> criteriaQuery = builder.createQuery(UserInfo.class);
        Root<UserInfo> root = criteriaQuery.from(UserInfo.class);
        criteriaQuery.where(builder.equal(root.get("userTitle"), loginName));
        return entityManager
                .createQuery(criteriaQuery)
                .unwrap(QueryImpl.class)
                .uniqueResultOptional();
    }

    /**
     * 查询登录用户职员信息
     *
     * @param uid
     * @return
     */
    public Optional<SysUser> findEmployeeByUserInfo(String uid) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SysUser> criteriaQuery = builder.createQuery(SysUser.class);
        Root<SysUser> root = criteriaQuery.from(SysUser.class);
        criteriaQuery.where(builder.equal(root.get("userPid"), uid));
        return entityManager
                .createQuery(criteriaQuery)
                .unwrap(QueryImpl.class)
                .uniqueResultOptional();
    }
}

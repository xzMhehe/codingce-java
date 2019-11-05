package cn.com.codingce.jpa.web.repository;

import cn.com.coding4fun.hibernate.entity.GroupDetailsUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Date 2019/10/24 10:16
 */
public interface GroupDetailsUserRepository extends JpaRepository<GroupDetailsUser, String> {

    List<GroupDetailsUser> findAllByGroupDetailsPid(String groupPid);

    long countByGroupDetailsPid(String groupDetailsPid);

}

package cn.com.codingce.jpa.web.repository;

import cn.com.coding4fun.hibernate.entity.GroupDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

/**
 * @Date 2019/10/23 13:42
 */
public interface GroupDetailsRepository extends JpaRepository<GroupDetails, String>, JpaSpecificationExecutor {

    long countByGroupNameAndOrgPid(String name, String orgPid);

    /**
     * 查询创建人创建每个分组的人数集合
     * @param usrPid 创建人pid
     * @return
     */
    @Query("SELECT\n" +
            "model.pid,\n" +
            "COUNT(guser.groupDetailsPid)\n" +
            "FROM\n" +
            "GroupDetails model\n" +
            "LEFT JOIN GroupDetailsUser guser ON model.pid = guser.groupDetailsPid \n" +
            "WHERE\n" +
            "model.userPid = :usrPid\n" +
            "GROUP BY\n" +
            "model.pid")
    Stream<Object[]> userNum(String usrPid);

}

package cn.com.codingce.jpa.repository;

import cn.com.codingce.jpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/22 13:04
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * 根据 name 来查找 Person
     *
     * @param name 用户姓名
     * @return
     */
    @Query("select p from Person p where p.name = :name")
    Optional<Person> findByNameCustomeQuery(@Param("name") String name);


    /**
     * 根据 id 更新Person name
     *
     * @param id 用户id
     * @return
     */
    @Query("select p.name from Person p where p.id = :id")
    String findPersonNameById(@Param("id") Long id);

}

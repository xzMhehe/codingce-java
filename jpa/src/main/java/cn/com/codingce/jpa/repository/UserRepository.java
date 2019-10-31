package cn.com.codingce.jpa.repository;

import cn.com.codingce.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jiangjun
 * @Date 2019/10/24 20:18
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}

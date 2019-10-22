package cn.com.codingce.jpa;

import cn.com.codingce.jpa.entity.Person;
import cn.com.codingce.jpa.repository.PersonRepository;
import cn.com.codingce.jpa.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class JpaApplicationTests {

    @Autowired
    private PersonService service;

    @Test
    void save() {
        Person person = new Person("SnailClimb", 23);
        service.save(person);
    }

}

package cn.com.codingce.jpa.service;

import cn.com.codingce.jpa.entity.Person;
import cn.com.codingce.jpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Author: Jiangjun
 * @Date: 2019/10/22 13:07
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    /**
     * 添加
     *
     * @param person
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(Person person) {
        repository.save(person);
    }

    /**
     * 查找用户
     *
     * @param id 用户id
     * @return
     */
    public Person findById(Long id) {
        Person person = repository.findById(id)
                .orElseThrow(RuntimeException::new);
        return person;
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    /**
     * 删除用户
     *
     * @param person
     */
    public void delete(Person person) {
        repository.delete(person);
    }



}

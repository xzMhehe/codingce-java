package cn.com.codingce.springcloud.service;

import cn.com.codingce.springcloud.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务降级
 * @author xzMa
 */
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory {

    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService(){

            @Override
            public Dept queryById(Long id) {
                Dept dept = new Dept();
                dept.setDeptno(id);
                dept.setDname("id" + "=>" + "没有对应的信息, 客户端提供了降级信息, 这个服务现在已关闭");
                dept.setDb_source("没有数据源");
                return dept;
            }

            @Override
            public List<Dept> queryAll() {
                Dept dept = new Dept();
                dept.setDeptno((long) 10);
                dept.setDname("id" + "=>" + "没有对应的信息, 客户端提供了降级信息, 这个服务现在已关闭");
                dept.setDb_source("没有数据源");
                List<Dept> deptList = new ArrayList<>();
                deptList.add(dept);
                return deptList;
            }

            @Override
            public boolean addDept(Dept dept) {
                return false;
            }
        };
    }
}

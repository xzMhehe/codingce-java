package cn.com.codingce.dao;

import cn.com.codingce.pojo.Department;
import cn.com.codingce.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    @Autowired
    private DepartmentDao departmentDao;

    // 模拟数据
    private static Map<Integer, Employee> employeeDaoMap = null;

    static {
        employeeDaoMap = new HashMap<>();    //创建一个部门表
        employeeDaoMap.put(1001, new Employee(1001, "张三", "2460798168@qq.com", 1, new Department(101, "教学部")));
        employeeDaoMap.put(1002, new Employee(1002, "李四", "F460798168@qq.com", 1, new Department(102, "后勤部")));
        employeeDaoMap.put(1003, new Employee(1003, "王五", "E460798168@qq.com", 1, new Department(103, "市场部")));
        employeeDaoMap.put(1004, new Employee(1004, "赵六", "D460798168@qq.com", 1, new Department(104, "教研部")));
        employeeDaoMap.put(1005, new Employee(1005, "孙七", "C460798168@qq.com", 1, new Department(105, "运营部")));
    }

    //主键自增
    private static Integer ininId = 1006;

    //增加一个员工B
    public void save(Employee employee) {
        if (employee == null) {
            ininId++;
        }
        employee.setId(ininId);
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employeeDaoMap.put(employee.getId(), employee);
    }

    //查询一个员工
    public Employee getEmployeeById(Integer id) {
        return employeeDaoMap.get(id);
    }

    //查询全部员工
    public Collection<Employee> getEmplees() {
        return employeeDaoMap.values();
    }

    public void deleEmp(Integer id) {
        employeeDaoMap.remove(id);
    }

}
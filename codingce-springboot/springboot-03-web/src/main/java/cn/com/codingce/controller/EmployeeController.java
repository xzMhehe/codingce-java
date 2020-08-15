package cn.com.codingce.controller;

import cn.com.codingce.dao.DepartmentDao;
import cn.com.codingce.dao.EmployeeDao;
import cn.com.codingce.pojo.Department;
import cn.com.codingce.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> emplees = employeeDao.getEmplees();
        model.addAttribute("emps", emplees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model) {
        //查出所有部门信息
        Collection<Department> departments = departmentDao.getDepartments();

        model.addAttribute("departments", departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addPage(Employee employee) {
        System.out.println(employee);
        employeeDao.save(employee);
        System.out.println(employee);
        return "redirect:/emps";
    }

}

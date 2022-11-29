package cn.com.codingce.controller;

import cn.com.codingce.dao.DepartmentDao;
import cn.com.codingce.dao.EmployeeDao;
import cn.com.codingce.pojo.Department;
import cn.com.codingce.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
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


    //跳转员工修改的页面
    @GetMapping("/emp/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.getEmployeeById(id);
        //查出所有部门信息
        Collection<Department> departments = departmentDao.getDepartments();

        model.addAttribute("departments", departments);
        model.addAttribute("employee", employee);
        return "emp/update";
    }

    @RequestMapping(value = "/updateEmp", method = RequestMethod.POST)
    public String updateEmp(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }


    @GetMapping("/deleEmp/{id}")
    public String deleEmp(@PathVariable("id") Integer id) {
        employeeDao.deleEmp(id);
        return "redirect:/emps";
    }


    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redircet:/index.html";
    }




}

package cn.com.codingce.controller;

import cn.com.codingce.dao.EmployeeDao;
import cn.com.codingce.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> emplees = employeeDao.getEmplees();
        model.addAttribute("emps", emplees);

        return "emp/list";
    }

}

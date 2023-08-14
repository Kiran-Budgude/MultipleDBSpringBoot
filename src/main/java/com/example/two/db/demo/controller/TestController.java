package com.example.two.db.demo.controller;


import com.example.two.db.demo.entity.db1.Employee;
import com.example.two.db.demo.entity.db1.Salary;
import com.example.two.db.demo.entity.db2.Manager;
import com.example.two.db.demo.mysql.repository.db1.EmployeeRepository;
import com.example.two.db.demo.mysql.repository.db1.SalaryRepository;
import com.example.two.db.demo.mysql.repository.db2.ManagerRepository;
import com.example.two.db.demo.service.db1.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1.0/list")
public class TestController {

    @Autowired
    @Qualifier("employeeRepository")
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    @Qualifier("managerRepository")
    ManagerRepository managerRepository;

    @Autowired
    SalaryRepository salaryRepository;



    @Transactional("primaryTransactionManager")
    @RequestMapping(value = "/saveAll", method = RequestMethod.POST)
    public String saveAllEmployees(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return "Employee saved in db";
    }

    @Transactional("primaryTransactionManager")
    @RequestMapping(value = "getAllEmployee",method = RequestMethod.GET)
    public List<Employee> getAllEmployee(){
       return employeeService.getAllEmployeesWithDepartment();
    }

    @RequestMapping(value = "/salary",method = RequestMethod.POST)
    public String fetchSalary(@RequestBody Salary salary){
        salaryRepository.save(salary);
        return "Salary credited";
    }

    @Transactional("secondaryTransactionManager")
    @RequestMapping(value = "/saveAllM", method = RequestMethod.POST)
    public List<Manager> saveAllManager(@RequestBody List<Manager> managers) {
        managerRepository.saveAll(managers);
        return managers;
    }
    @RequestMapping(value = "/fetchDepartment", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> fetchDepartment(){
        List<Employee> employees= employeeService.fetchDepartment();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
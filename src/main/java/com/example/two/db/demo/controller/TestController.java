package com.example.two.db.demo.controller;


import com.example.two.db.demo.entity.db1.Employee;
import com.example.two.db.demo.entity.db1.Salary;
import com.example.two.db.demo.entity.db2.Manager;
import com.example.two.db.demo.mysql.repository.db1.EmployeeRepository;
import com.example.two.db.demo.mysql.repository.db1.SalaryRepository;
import com.example.two.db.demo.mysql.repository.db2.ManagerRepository;
import com.example.two.db.demo.service.EmployeeService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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


    @GetMapping(value = "/getEmployeeFile/{type}")
    public ResponseEntity<?> getCSVFile(@PathVariable(value = "type") String type) throws IOException {
        if (type == null || type.equalsIgnoreCase("")) {
            throw new RuntimeException("Incorrect Type");
        }

        if (type.equalsIgnoreCase("JSON")) {
            List<Employee> employees = employeeService.fetchDepartment();
            return ResponseEntity.ok(employees);
        }

        if (type.equalsIgnoreCase("CSV")) {
            String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".csv";
            String filename = "EmployeeReport" + fileSuffix;
            InputStreamResource file = new InputStreamResource(employeeService.getEmployeeCSVFile());
            byte[] bytes = IOUtils.toByteArray(file.getInputStream());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
            headers.setContentType(MediaType.parseMediaType("application/csv"));

            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        }

        // Handle unsupported types
        return ResponseEntity.badRequest().body("Unsupported type: " + type);
    }
}
package com.example.two.db.demo.service;

import com.example.two.db.demo.entity.db1.Employee;
import com.example.two.db.demo.entity.db2.Department;
import com.example.two.db.demo.mysql.repository.db1.EmployeeRepository;
import com.opencsv.CSVWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional("primaryTransactionManager")
    public List<Employee> getAllEmployeesWithDepartment() {
        return employeeRepository.findAllWithDepartmentName();
    }

    public List<Employee> fetchDepartment() {

        List<Employee> employees = jdbcTemplate.query("SELECT * FROM sbmdbs.Employee e", new BeanPropertyRowMapper<>(Employee.class));

        for (Employee employee : employees) {
            Long depId = employee.getDepartmentId();
            Department department = findByDeptId(depId);
            if (department != null) {
                employee.setDepartmentId(department.getId());
                employee.setDepartmentName(department.getName());
            }
        }
        return employees;
    }

    public Department findByDeptId(Long deptId) { // Corrected method name
        String query = "SELECT * FROM sbmdb.Department d WHERE d.id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{deptId}, new BeanPropertyRowMapper<>(Department.class));

        } catch (EmptyResultDataAccessException ex) {
            // Handle the case where no Department is found with the provided ID
            return null;

        }
    }

    public ByteArrayInputStream getEmployeeCSVFile() throws IOException {

        List<Employee> employeesList = fetchDepartment();

        try (final ByteArrayOutputStream stream = new ByteArrayOutputStream();
             final CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(stream), CSVFormat.EXCEL)){
            for (final Employee employee:employeesList){
                List<Serializable> data = new ArrayList<>();
                data.add(employee.getId());
                data.add(employee.getName() != null ? employee.getName() : "");
                data.add(employee.getDepartmentId() != null ? employee.getDepartmentId().toString() : "");
                data.add(employee.getDepartmentName() != null ? employee.getDepartmentName() : "");
                data.add(employee.getSalary());
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(stream.toByteArray());

        }catch (final IOException e) {
            throw new RuntimeException("Csv writing error: " + e.getMessage());
        }
    }



}


package com.example.two.db.demo.service.db1;

import com.example.two.db.demo.entity.db1.Employee;
import com.example.two.db.demo.entity.db2.Department;
import com.example.two.db.demo.mysql.repository.db1.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}


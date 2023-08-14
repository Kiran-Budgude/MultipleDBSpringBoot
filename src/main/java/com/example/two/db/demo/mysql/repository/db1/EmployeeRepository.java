package com.example.two.db.demo.mysql.repository.db1;
import com.example.two.db.demo.entity.db1.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("employeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT e.*, d.name as department_name FROM sbmdbs.Employee e JOIN sbmdb.Department d ON e.department_id = d.id", nativeQuery = true)
    List<Employee> findAllWithDepartmentName();



}

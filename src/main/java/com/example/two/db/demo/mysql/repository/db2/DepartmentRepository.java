package com.example.two.db.demo.mysql.repository.db2;

import com.example.two.db.demo.entity.db2.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}

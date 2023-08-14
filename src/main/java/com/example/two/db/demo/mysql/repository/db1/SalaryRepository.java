package com.example.two.db.demo.mysql.repository.db1;

import com.example.two.db.demo.entity.db1.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Long> {
}

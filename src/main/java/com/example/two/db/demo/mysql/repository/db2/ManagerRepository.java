package com.example.two.db.demo.mysql.repository.db2;


import com.example.two.db.demo.entity.db2.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("managerRepository")
public interface ManagerRepository extends JpaRepository<Manager, Long> {

}

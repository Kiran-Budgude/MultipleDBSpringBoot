package com.example.two.db.demo.mysql.repository.db2;

import com.example.two.db.demo.entity.db2.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}

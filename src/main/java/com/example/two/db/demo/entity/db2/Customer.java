package com.example.two.db.demo.entity.db2;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer extends GenericNotification {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "deviceKey")
    private String deviceKey;

    @Column(name = "mobile")
    private String mobile;




}

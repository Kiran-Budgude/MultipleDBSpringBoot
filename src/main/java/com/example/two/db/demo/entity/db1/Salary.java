package com.example.two.db.demo.entity.db1;

import javax.persistence.*;

@Entity
@Table(name = "salary")
public class Salary {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    private String amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Salary(int id, String amount) {
        this.id = id;
        this.amount = amount;
    }
}

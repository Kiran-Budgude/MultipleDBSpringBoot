package com.example.two.db.demo.entity.db2;

import java.util.List;

public class ManagerData {
    private String managerName;
    private List<Salary> salaries;

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }

    public static class EmployeeDto {
        private String name;
        private int salary;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }
    }

    public static class Salary {
        private EmployeeDto employee;

        public EmployeeDto getEmployee() {
            return employee;
        }

        public void setEmployee(EmployeeDto employee) {
            this.employee = employee;
        }
    }
}







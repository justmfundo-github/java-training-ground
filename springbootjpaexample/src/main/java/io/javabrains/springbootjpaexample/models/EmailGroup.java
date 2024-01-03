package io.javabrains.springbootjpaexample.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity(name="email_group_data")
public class EmailGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    // The default fetch type for ManyToMany relationships is LAZY. You don't want to fetch too much data and
    // slow down your system.
    // In this case an email group could have very many employees so we leave the default of lazy
    @ManyToMany(mappedBy = "emailGroupList")
    private List<Employee> employeeList = new ArrayList<Employee>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EmailGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void addEmailGroupMember(Employee employee){
        this.employeeList.add(employee);
    }
}

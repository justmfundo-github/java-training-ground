package io.javabrains.springbootjpaexample.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity(name="PAY_STUB_DATA")
public class PayStub {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date payPeriodBegin;
    private Date payPeriodEnd;
    private float salary;
    // This says when a PayStub is removed then the employee should be removed. You don't want that!!!!
    //@ManyToOne(cascade = CascadeType.REMOVE)

    @ManyToOne
    @JoinColumn(name = "paystub_for") // use @JoinColumn when giving names to a foreign key variable
    private Employee employee;

    public Date getPayPeriodBegin() {
        return payPeriodBegin;
    }

    @Column(name="start")
    public void setPayPeriodBegin(Date payPeriodBegin) {
        this.payPeriodBegin = payPeriodBegin;
    }

    @Column(name="end")
    public Date getPayPeriodEnd() {
        return payPeriodEnd;
    }

    public void setPayPeriodEnd(Date payPeriodEnd) {
        this.payPeriodEnd = payPeriodEnd;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "PayStub{" +
                "id=" + id +
                ", payPeriodBegin=" + payPeriodBegin +
                ", payPeriodEnd=" + payPeriodEnd +
                ", salary=" + salary +
                ", employee=" + employee +
                '}';
    }
}

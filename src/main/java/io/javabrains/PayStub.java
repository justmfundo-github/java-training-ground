package io.javabrains;

import javax.persistence.*;
import java.util.Date;

@Entity(name="PAY_STUB_DATA")
public class PayStub {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date payPeriodBegin;
    private Date payPeriodEnd;
    private float salary;
    @ManyToOne
    private Employee employee;

    public Date getPayPeriodBegin() {
        return payPeriodBegin;
    }

    public void setPayPeriodBegin(Date payPeriodBegin) {
        this.payPeriodBegin = payPeriodBegin;
    }

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

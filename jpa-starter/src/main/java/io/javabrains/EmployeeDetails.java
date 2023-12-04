package io.javabrains;

import java.util.List;

public class EmployeeDetails {
    private Employee employee;
    private AccessCard accessCard;

    private List<PayStub> payStubList;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public AccessCard getAccessCard() {
        return accessCard;
    }

    public void setAccessCard(AccessCard accessCard) {
        this.accessCard = accessCard;
    }

    public List<PayStub> getPayStubList() {
        return payStubList;
    }

    public void setPayStubList(List<PayStub> payStubList) {
        this.payStubList = payStubList;
    }
}

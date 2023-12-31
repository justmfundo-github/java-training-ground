package io.javabrains.springbootjpaexample.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="EMPLOYEE_DATA")
//Note that named queries in older versions of java may not work
@NamedQuery(query="select e from Employee e where e.age > :age order by e.name", name="emp name asc")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name="employee_name", length = 100)
    private String name;

    private int age;

    @Temporal(TemporalType.DATE)
    private Date dob;


    @Column(unique = true, length = 10)
    private String ssn;

    @OneToOne(fetch = FetchType.LAZY)
    private AccessCard card;

    // This says, when an employee is removed then the PayStub must be removed as well
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private List<PayStub> payStubList = new ArrayList<>();

    // The default fetch type for ManyToMany relationships is LAZY. You don't want to fetch too much data and
    // slow down your system.
    // In this case however we've made the fetch type eager. If you fetch employee then email group will be
    // fetched also because one can imagine that an employee isn't part of too many email groups
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="email_group_subscriptions",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "subscription_email_id")
    )// customising the column names in the join table
    private List<EmailGroup> emailGroupList = new ArrayList<EmailGroup>();

    public AccessCard getCard() {
        return card;
    }

    public void setCard(AccessCard card) {
        this.card = card;
    }

    public String getDebugInfo() {
        return debugInfo;
    }

    public void setDebugInfo(String debugInfo) {
        this.debugInfo = debugInfo;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    private EmployeeType type;

    public List<PayStub> getPayStubList() {
        return payStubList;
    }

    public void setPayStubList(List<PayStub> payStubList) {
        this.payStubList = payStubList;
    }

    public void addPayStub(PayStub payStub){
        this.payStubList.add(payStub);
    }

    public void addEmailSubscription(EmailGroup emailGroup){
        this.emailGroupList.add(emailGroup);
    }

    // Attempting to save an enum results in the ordinal being stored in the database
    // I.e. if the value of the enum is at position 1, then the number 1 is saved as default.
    // To save the value of the enum, specify the enumType
    @Enumerated(EnumType.STRING)
    public EmployeeType getType() {
        return type;
    }

    // Any value that we don't want saved to the database must be marked transient
    @Transient
    private String debugInfo;
    public void setType(EmployeeType type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

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

    public List<EmailGroup> getEmailGroupList() {
        return emailGroupList;
    }

    public void setEmailGroupList(List<EmailGroup> emailGroupList) {
        this.emailGroupList = emailGroupList;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dob=" + dob +
                ", ssn='" + ssn + '\'' +
                ", card=" + card +
                ", type=" + type +
                '}';
    }
}

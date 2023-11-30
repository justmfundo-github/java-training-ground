package io.javabrains;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="EMPLOYEE_DATA")
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

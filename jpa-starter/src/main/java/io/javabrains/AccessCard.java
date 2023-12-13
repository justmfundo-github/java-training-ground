package io.javabrains;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ACCESS_CARD_DATA")
public class AccessCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date issuedDate;
    private Boolean isActive;
    private String firmwareVersion;
    @OneToOne(mappedBy = "card")
    private Employee owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "AccessCard{" +
                "id=" + id +
                ", issuedDate=" + issuedDate +
                ", isActive=" + isActive +
                ", firmwareVersion='" + firmwareVersion + '\'' +
                '}';
    }
}

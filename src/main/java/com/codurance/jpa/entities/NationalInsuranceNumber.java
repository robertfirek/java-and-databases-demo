package com.codurance.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Table(name = "national_insurance_number", schema = "jdbc")
public class NationalInsuranceNumber {
    @Id
    @Column(name = "_identifier")
    private String nationalInsuranceNumber;

    @Column(name = "_creation_date")
    private ZonedDateTime creationDate;

    public String getNationalInsuranceNumber() {
        return nationalInsuranceNumber;
    }

    public void setNationalInsuranceNumber(String nationalInsuranceNumber) {
        this.nationalInsuranceNumber = nationalInsuranceNumber;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "NationalInsuranceNumber{" +
                "nationalInsuranceNumber='" + nationalInsuranceNumber + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}

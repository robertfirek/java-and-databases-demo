package com.codurance.jpa.entities;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "employee", schema = "jdbc")
public class Employee {
    @Id
    @Column(name = "_identifier")
    private String identifier;

    @Column(name = "_first_name")
    private String firstName;

    @Column(name = "_surname")
    private String surname;

    @Column(name = "_date_of_birth")
    private ZonedDateTime dateOfBirth;

    @OneToOne
    @JoinColumn(name = "_nationa_insurance__identifier")
    private NationalInsuranceNumber nationalInsuranceNumber;

    @ManyToOne
    @JoinColumn(name = "_address_identifier")
    private Address address;

    @ManyToMany
    @JoinTable(
            name = "employee_salary",
            schema = "jdbc",
            joinColumns = @JoinColumn(name = "_employee_identifier", referencedColumnName = "_identifier"),
            inverseJoinColumns = @JoinColumn(name = "_company_identifier", referencedColumnName = "_identifier")
    )
    private List<Company> employers;


    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public ZonedDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(ZonedDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public NationalInsuranceNumber getNationalInsuranceNumber() {
        return nationalInsuranceNumber;
    }

    public void setNationalInsuranceNumber(NationalInsuranceNumber nationalInsuranceNumber) {
        this.nationalInsuranceNumber = nationalInsuranceNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Company> getEmployers() {
        return employers;
    }

    public void setEmployers(List<Company> employers) {
        this.employers = employers;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "identifier='" + identifier + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", nationalInsuranceNumber=" + nationalInsuranceNumber +
                ", address=" + address +
                '}';
    }
}
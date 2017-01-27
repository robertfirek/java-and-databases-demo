package com.codurance.jpa.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "address", schema = "jdbc")
public class Address {
    @Id
    @Column(name = "_identifier")
    private String identifier;

    @Column(name = "_post_code")
    private String postCode;

    @Column(name = "_post_town")
    private String postTown;

    @OneToMany(mappedBy = "address")
    private List<Employee> employees;

    @OneToMany(mappedBy = "address")
    private List<Company> companies;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostTown() {
        return postTown;
    }

    public void setPostTown(String postTown) {
        this.postTown = postTown;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "Address{" +
                "identifier='" + identifier + '\'' +
                ", postCode='" + postCode + '\'' +
                ", postTown='" + postTown + '\'' +
                '}';
    }
}

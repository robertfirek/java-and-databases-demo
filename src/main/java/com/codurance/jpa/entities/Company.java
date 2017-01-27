package com.codurance.jpa.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company", schema = "jdbc")
public class Company {
    @Id
    @Column(name = "_identifier")
    private String identifier;

    @Column(name = "_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "_address_identifier")
    private Address address;

    @ManyToMany(mappedBy = "employers")
    private List<Employee> employees;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Company{" +
                "identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

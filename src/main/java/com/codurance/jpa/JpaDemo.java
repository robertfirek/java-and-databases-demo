package com.codurance.jpa;

import com.codurance.jpa.entities.Address;
import com.codurance.jpa.entities.Company;
import com.codurance.jpa.entities.Employee;
import com.codurance.jpa.entities.NationalInsuranceNumber;

import javax.persistence.*;
import java.util.List;

public class JpaDemo {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = factory.createEntityManager();


        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        simpleFindAndOneToMany(entityManager);
        persistMergeAndRemove(entityManager);
        manyToMany(entityManager);

        nativeQuery(entityManager);
        jpqlQueries(entityManager);

        transaction.commit();
        entityManager.close();
        factory.close();
    }

    private static void jpqlQueries(EntityManager entityManager) {
        Query query = entityManager.createQuery("SELECT address FROM Address address");
        List<Address> resultList1 = query.getResultList();
        resultList1.forEach(resultAddress -> System.out.println(resultAddress));

        query = entityManager.createQuery("SELECT address.postCode FROM Address address");
        List<String> resultList2 = query.getResultList();
        resultList2.forEach(resultAddressPostCode -> System.out.println(resultAddressPostCode));

        query = entityManager.createQuery("UPDATE Address address SET postTown = 'London' WHERE address.identifier = :id");
        query.setParameter("id", "23 New Way Rd");
        query.executeUpdate();
    }

    private static void nativeQuery(EntityManager entityManager) {
        Query nativeQuery = entityManager.createNativeQuery("SELECT _identifier, _post_code, _post_town FROM jdbc.address WHERE _identifier = :id");
        nativeQuery.setParameter("id", "23 New Way Rd");
        List<Object[]> resultList = nativeQuery.getResultList();
        resultList.forEach(row -> {
            System.out.println("" + row[0] + "," + row[1] + "," + row[2]);
        });
    }

    private static void manyToMany(EntityManager entityManager) {
        Employee employee = entityManager.find(Employee.class, "RF");
        System.out.println("----------------------------------");
        System.out.println(employee);
        System.out.println(employee.getEmployers());
        System.out.println("----------------------------------");
    }

    private static void persistMergeAndRemove(EntityManager entityManager) {
        Address addressToAdd = new Address();
        addressToAdd.setIdentifier("x");
        addressToAdd.setPostCode("Post Code");
        addressToAdd.setPostTown("Post town");
//      entityManager.persist(addressToAdd);
        Address tracedEntity = entityManager.merge(addressToAdd);
        tracedEntity.setPostTown("ANothe post town");
        entityManager.remove(tracedEntity);
    }

    private static void simpleFindAndOneToMany(EntityManager entityManager) {
        Address address = entityManager.find(Address.class, "3 Henry Darlot Dr");
        System.out.println(address);
        System.out.println(address.getCompanies());
        System.out.println(address.getEmployees());

        NationalInsuranceNumber nationalInsuranceNumber = entityManager.find(NationalInsuranceNumber.class, "AA 12 34 56 B");
        System.out.println(nationalInsuranceNumber);


        Company company = entityManager.find(Company.class, "Tesco Ltd.");
        System.out.println("----------------------------------");
        System.out.println(company);
        System.out.println(company.getEmployees());
        System.out.println("----------------------------------");
    }
}

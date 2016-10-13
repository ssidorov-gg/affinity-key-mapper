package org.apache.ignite.examples.datagrid.affinity.mapper;

public class Person {
    private String personId;
    private String companyId;

    private String firstName;
    private String lastName;

    public Person(String personId, String companyId) {
        this.personId = personId;
        this.companyId = companyId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

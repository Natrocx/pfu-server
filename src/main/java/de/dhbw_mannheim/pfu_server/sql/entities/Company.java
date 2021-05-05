package de.dhbw_mannheim.pfu_server.sql.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;


@Entity(name="company") // This tells Hibernate to make a table out of this class
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false,name="ID_Company",nullable = false,columnDefinition = "int")
    private Integer ID_Company;

    @Column(updatable = true,name="Company_Name",nullable = false,columnDefinition = "mediumtext")
    private String Company_Name;

    public Integer getID_Company() {
        return ID_Company;
    }

    public void setID_Company(Integer ID_Company) {
        this.ID_Company = ID_Company;
    }

    public String getCompany_Name() {
        return Company_Name;
    }

    public void setCompany_Name(String company_Name) {
        Company_Name = company_Name;
    }
}
package de.dhbw_mannheim.pfu_server.sql.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;


@Entity // This tells Hibernate to make a table out of this class
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = true,name="ID_Company",nullable = false,columnDefinition = "int")
    private Integer ID_Company;

    @Column(updatable = true,name="Company_Name",nullable = false,columnDefinition = "varchar(255)")
    private String Company_Name;

    public Integer getId() {
        return ID_Company;
    }

    public void setId(Integer id) {
        this.ID_Company = id;
    }

    public String getName() {
        return Company_Name;
    }

    public void setName(String name) {
        this.Company_Name = name;
    }
}
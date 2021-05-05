package de.dhbw_mannheim.pfu_server.sql.relations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="student_is-employed-by_company")
public class Student_isEmployedBy_Company {

    @Id
    @Column(updatable = true,name="ID_User",nullable = false, columnDefinition = "int")
    private Integer ID_User;

    @Id
    @Column(updatable = true,name="ID_Company",nullable = false, columnDefinition = "int")
    private Integer ID_Company;

    @Column(updatable = true,name="Location",nullable = true, columnDefinition = "mediumtext")
    private String Location;


    public Integer getID_User() {
        return ID_User;
    }

    public void setID_User(Integer ID_User) {
        this.ID_User = ID_User;
    }

    public Integer getID_Company() {
        return ID_Company;
    }

    public void setID_Company(Integer ID_Company) {
        this.ID_Company = ID_Company;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}

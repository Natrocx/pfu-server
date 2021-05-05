package de.dhbw_mannheim.pfu_server.sql.relations;

import de.dhbw_mannheim.pfu_server.sql.entities.Company;
import de.dhbw_mannheim.pfu_server.sql.entities.User;

import javax.persistence.*;

@Entity(name="student_is-employed-by_company")
public class Student_isEmployedBy_Company {

    @Id
    @Column(updatable = true,name="ID_User",nullable = false, columnDefinition = "int")
    private Integer ID_User;

    @ManyToOne
    @JoinColumn(name="ID_User")
    @MapsId
    private User user;

    @Id
    @Column(updatable = true,name="ID_Company",nullable = false, columnDefinition = "int")
    private Integer ID_Company;

    @ManyToOne
    @JoinColumn(name="ID_Company")
    @MapsId
    private Company company;

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

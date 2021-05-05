package de.dhbw_mannheim.pfu_server.sql.entities;

import javax.persistence.*;

@Entity(name="dualehochschule") // This tells Hibernate to make a table out of this class
public class Dualehochschule {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(updatable = true,name="ID_Hochschule",nullable = false,columnDefinition = "int")
    private Integer ID_Hochschule;

    @Column(updatable = true,name="Location",nullable = false,columnDefinition = "mediumtext")
    private String Location;

    @Column(updatable = true,name="Name",nullable = false,columnDefinition = "mediumtext")
    private String Name;

    @Column(updatable = true,name="mail_format_dozenten",nullable = false,columnDefinition = "mediumtext")
    private String mail_format_dozenten;

    @Column(updatable = true,name="mail_format_students",nullable = false,columnDefinition = "mediumtext")
    private String mail_format_students;


    public Integer getID_Hochschule() {
        return ID_Hochschule;
    }

    public void setID_Hochschule(Integer ID_Hochschule) {
        this.ID_Hochschule = ID_Hochschule;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMail_format_dozenten() {
        return mail_format_dozenten;
    }

    public void setMail_format_dozenten(String mail_format_dozenten) {
        this.mail_format_dozenten = mail_format_dozenten;
    }

    public String getMail_format_students() {
        return mail_format_students;
    }

    public void setMail_format_students(String mail_format_students) {
        this.mail_format_students = mail_format_students;
    }
}

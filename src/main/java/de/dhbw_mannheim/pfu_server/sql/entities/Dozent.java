package de.dhbw_mannheim.pfu_server.sql.entities;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Dozent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = true,name="ID_User",nullable = false,columnDefinition = "int")
    private Integer ID_User;

    @Column(updatable = true,name="Biography",nullable = false,columnDefinition = "longtext")
    private String Biography;

    @Column(updatable = true,name="Special_Role",nullable = false,columnDefinition = "mediumtext")
    private String Special_Role;

    public Integer getId() {
        return ID_User;
    }

    public void setId(Integer id) {
        this.ID_User = id;
    }

    public String getBiography() {
        return Biography;
    }

    public void setBiography(String text) {
        this.Biography = text;
    }

    public String getSpecial_Role() {
        return Special_Role;
    }

    public void setSpecial_Role(String role) {
        this.Special_Role = role;
    }
}

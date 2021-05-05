package de.dhbw_mannheim.pfu_server.sql.entities;

import javax.persistence.*;

@Entity(name="dozent") // This tells Hibernate to make a table out of this class
public class Dozent {
    @Id
    @Column(updatable = true,name="ID_User",nullable = false,columnDefinition = "int")
    private Integer ID_User;

    @Column(updatable = true,name="Biography",nullable = true,columnDefinition = "longtext")
    private String Biography;

    @Column(updatable = true,name="Special_Role",nullable = true,columnDefinition = "mediumtext")
    private String Special_Role;


    public Integer getID_User() {
        return ID_User;
    }

    public void setID_User(Integer ID_User) {
        this.ID_User = ID_User;
    }

    public String getBiography() {
        return Biography;
    }

    public void setBiography(String biography) {
        Biography = biography;
    }

    public String getSpecial_Role() {
        return Special_Role;
    }

    public void setSpecial_Role(String special_Role) {
        Special_Role = special_Role;
    }
}

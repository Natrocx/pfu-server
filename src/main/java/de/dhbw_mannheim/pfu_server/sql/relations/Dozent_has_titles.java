package de.dhbw_mannheim.pfu_server.sql.relations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="dozent_has_titles") // This tells Hibernate to make a table out of this class
public class Dozent_has_titles {
    @Id
    @Column(updatable = true,name="ID_User",nullable = false,columnDefinition = "int")
    private Integer ID_User;

    @Id
    @Column(updatable = true,name="Title",nullable = false,columnDefinition = "varchar(45)")
    private String Title;

    public Integer getID_User() {
        return ID_User;
    }

    public void setID_User(Integer ID_User) {
        this.ID_User = ID_User;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
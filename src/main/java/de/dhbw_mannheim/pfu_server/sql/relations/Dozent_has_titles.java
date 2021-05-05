package de.dhbw_mannheim.pfu_server.sql.relations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Dozent_has_titles {
    @Id
    @Column(updatable = true,name="ID_User",nullable = false,columnDefinition = "int")
    private Integer ID_User;

    @Column(updatable = true,name="Title",nullable = false,columnDefinition = "varchar(45)")
    private String Title;


    public Integer getId() {
        return ID_User;
    }

    public void setId(Integer id) {
        this.ID_User = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String text) {
        this.Title = text;
    }
}
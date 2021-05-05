package de.dhbw_mannheim.pfu_server.sql.relations;

import de.dhbw_mannheim.pfu_server.sql.entities.Dozent;

import javax.persistence.*;

@Entity(name="dozent_has_titles") // This tells Hibernate to make a table out of this class
public class Dozent_has_titles {
    @Id
    @Column(updatable = false,name="ID_User",nullable = false,columnDefinition = "int")
    private Integer ID_User;

    @ManyToOne
    @JoinColumn(name="ID_User")
    @MapsId
    private Dozent dozent;

    @Id
    @Column(updatable = false,name="Title",nullable = false,columnDefinition = "varchar(45)")
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
package de.dhbw_mannheim.pfu_server.sql.entities;

import javax.persistence.*;

@Entity(name="student") // This tells Hibernate to make a table out of this class
public class Student {

    @Id
    @OneToOne(mappedBy="user",cascade = CascadeType.ALL)
    private Integer ID_User;

    @Column(updatable = true,name="Biography",nullable = false, columnDefinition = "longtext")
    private String biography;

    @Column(updatable = true,name="Course_Name",nullable = false, columnDefinition = "varchar(45)")
    private String Course_Name;

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getCourse_Name() {
        return Course_Name;
    }

    public void setCourse_Name(String course_Name) {
        Course_Name = course_Name;
    }
}

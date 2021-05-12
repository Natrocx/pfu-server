package de.dhbw_mannheim.pfu_server.sql.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity(name="student") // This tells Hibernate to make a table out of this class
public class Student {

    @Id
    @Column(updatable = false,name="ID_User",nullable = true, columnDefinition = "int")
    private Integer ID_User;

    @OneToOne
    @JoinColumn(name="ID_User")
    @MapsId
    private User user;

    @Column(updatable = true,name="Biography",nullable = true, columnDefinition = "longtext")
    private String biography;

    @Column(updatable = true,name="Course_Name",nullable = false, columnDefinition = "varchar(45)")
    private String Course_Name;

    @ManyToOne
    @JoinColumn(name="Course_Name")
    @MapsId
    private Course course;



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

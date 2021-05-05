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

    @Column(updatable = true,name="ID_Studiengang",nullable = false, columnDefinition = "int")
    private Integer ID_Studiengang;

    @ManyToOne
    @JoinColumn(name="ID_Studiengang")
    @MapsId
    private Studiengang studiengang;

    @Column(updatable = false,name="ID_DualeHochschule",nullable = false, columnDefinition = "int")
    private Integer ID_DualeHochschule;

    @OneToOne
    @JoinColumn(name="ID_DualeHochschule")
    @MapsId
    private DualeHochschule dualehochschule;

    @Column(updatable = false, name="Date_Start",nullable = false, columnDefinition = "date")
    private Date date_start;

    @Column(updatable = true,name="Date_Graduation",nullable = false, columnDefinition = "date")
    private Date date_graduation;

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

    public Integer getID_Studiengang() {
        return ID_Studiengang;
    }

    public void setID_Studiengang(Integer ID_Studiengang) {
        this.ID_Studiengang = ID_Studiengang;
    }

    public Integer getID_DualeHochschule() {
        return ID_DualeHochschule;
    }

    public void setID_DualeHochschule(Integer ID_DualeHochschule) {
        this.ID_DualeHochschule = ID_DualeHochschule;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_graduation() {
        return date_graduation;
    }

    public void setDate_graduation(Date date_graduation) {
        this.date_graduation = date_graduation;
    }
}

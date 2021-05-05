package de.dhbw_mannheim.pfu_server.sql.entities;

import javax.persistence.*;

@Entity(name="course") // This tells Hibernate to make a table out of this class
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = true,name="Course_Name",nullable = false,columnDefinition = "varchar(45)")
    private String Course_Name;

    @Column(updatable = true,name="ID_Studiengang",nullable = false,columnDefinition = "int")
    private Integer ID_Studiengang;


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
}

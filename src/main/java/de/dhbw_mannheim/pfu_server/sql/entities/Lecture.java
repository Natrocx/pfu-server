package de.dhbw_mannheim.pfu_server.sql.entities;

import javax.persistence.*;

@Entity(name="lecture") // This tells Hibernate to make a table out of this class
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = true,name="ID_Lecture",nullable = false,columnDefinition = "int")
    private Integer ID_Lecture;

    @Column(updatable = true,name="Lecture_Name",nullable = false,columnDefinition = "mediumtext")
    private String Lecture_Name;


    public Integer getID_Lecture() {
        return ID_Lecture;
    }

    public void setID_Lecture(Integer ID_Lecture) {
        this.ID_Lecture = ID_Lecture;
    }

    public String getLecture_Name() {
        return Lecture_Name;
    }

    public void setLecture_Name(String lecture_Name) {
        Lecture_Name = lecture_Name;
    }
}

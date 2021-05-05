package de.dhbw_mannheim.pfu_server.sql.relations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Dozent_reads_lecture_for_course {
    @Id
    @Column(updatable = true,name="ID_User",nullable = false,columnDefinition = "int")
    private Integer ID_User;

    @Id
    @Column(updatable = true,name="ID_Lecture",nullable = false,columnDefinition = "int")
    private Integer ID_Lecture;

    @Column(updatable = true,name="Course_name",nullable = false,columnDefinition = "varchar(45)")
    private String Course_name;


    public Integer getId() {
        return ID_User;
    }

    public void setId(Integer id) {
        this.ID_User = id;
    }

    public Integer getID_Lecture() {
        return ID_Lecture;
    }

    public void setID_Lecture(Integer id) {
        this.ID_Lecture = id;
    }

    public String getCourse_name() {
        return Course_name;
    }

    public void setCourse_name(String name) {
        this.Course_name = name;
    }
}

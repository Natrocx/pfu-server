package de.dhbw_mannheim.pfu_server.sql.relations;

import de.dhbw_mannheim.pfu_server.sql.entities.Course;
import de.dhbw_mannheim.pfu_server.sql.entities.Dozent;
import de.dhbw_mannheim.pfu_server.sql.entities.Lecture;
import de.dhbw_mannheim.pfu_server.sql.entities.User;

import javax.persistence.*;

@Entity(name="dozent_reads_lecture_for_course") // This tells Hibernate to make a table out of this class
public class Dozent_reads_Lecture_for_Course {
    @Id
    @Column(updatable = false,name="ID_User",nullable = false,columnDefinition = "int")
    private Integer ID_User;

    @ManyToOne
    @JoinColumn(name="ID_User")
    @MapsId
    private Dozent dozent;

    @Id
    @Column(updatable = false,name="ID_Lecture",nullable = false,columnDefinition = "int")
    private Integer ID_Lecture;

    @ManyToOne
    @JoinColumn(name="ID_Lecture")
    @MapsId
    private Lecture lecture;

    @Id
    @Column(updatable = false,name="Course_Name",nullable = false,columnDefinition = "varchar(45)")
    private String Course_name;

    @ManyToOne
    @JoinColumn(name="Course_Name")
    @MapsId
    private Course course;

    public Integer getID_User() {
        return ID_User;
    }

    public void setID_User(Integer ID_User) {
        this.ID_User = ID_User;
    }

    public Integer getID_Lecture() {
        return ID_Lecture;
    }

    public void setID_Lecture(Integer ID_Lecture) {
        this.ID_Lecture = ID_Lecture;
    }

    public String getCourse_name() {
        return Course_name;
    }

    public void setCourse_name(String course_name) {
        Course_name = course_name;
    }
}

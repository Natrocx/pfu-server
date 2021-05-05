package de.dhbw_mannheim.pfu_server.sql.relations;

import de.dhbw_mannheim.pfu_server.sql.entities.Lecture;
import de.dhbw_mannheim.pfu_server.sql.entities.Student;
import de.dhbw_mannheim.pfu_server.sql.entities.User;

import javax.persistence.*;
import java.util.List;

@Entity(name="student_attends_lecture")
public class Student_attends_Lecture {

    @Id
    @Column(updatable = false,name="ID_User",nullable = false, columnDefinition = "int")
    private Integer ID_User;

    @ManyToMany
    @JoinColumn(name="ID_User")
    @MapsId
    private List<Student> student;

    @Id
    @Column(updatable = false,name="ID_Lecture",nullable = false, columnDefinition = "int")
    private Integer ID_Lecture;

    @ManyToMany
    @JoinColumn(name="ID_Lecture")
    @MapsId
    private List<Lecture> lecture;

    @Column(updatable = true,name="Proficiency",nullable = false, columnDefinition = "int")
    private Integer Proficiency = 3;

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

    public Integer getProficiency() {
        return Proficiency;
    }

    public void setProficiency(Integer proficiency) {
        Proficiency = proficiency;
    }
}

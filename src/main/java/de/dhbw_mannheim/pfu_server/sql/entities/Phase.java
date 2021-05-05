package de.dhbw_mannheim.pfu_server.sql.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity(name="phase") // This tells Hibernate to make a table out of this class
public class Phase {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer ID_Phase;

    @Column(updatable = true,name="Date_Start",nullable = false, columnDefinition = "date")
    private Date date_start;

    @Column(updatable = true,name="Date_End",nullable = false, columnDefinition = "date")
    private Date date_end;

    @Column(updatable = true,name="Type",nullable = false, columnDefinition = "varchar(45)")
    private String type;

    @Column(updatable = true,name="Course_Name",nullable = false, columnDefinition = "varchar(45)")
    private String Course_Name;

    public Integer getID_Phase() {
        return ID_Phase;
    }

    public void setID_Phase(Integer ID_Phase) {
        this.ID_Phase = ID_Phase;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCourse_Name() {
        return Course_Name;
    }

    public void setCourse_Name(String course_Name) {
        Course_Name = course_Name;
    }
}

package de.dhbw_mannheim.pfu_server.sql.relations;

import javax.persistence.Column;
import java.sql.Date;

public class Student_takes_Studiengang {

    @Column(updatable = true,name="ID_User",nullable = false, columnDefinition = "int")
    private
    Integer ID_User;

    @Column(updatable = true,name="ID_Studiengang",nullable = false, columnDefinition = "int")
    private
    Integer ID_Studiengang;

    @Column(updatable = true,name="ID_DualeHochschule",nullable = false, columnDefinition = "int")
    private
    Integer ID_DualeHochschule;

    @Column(updatable = true,name="Date_Start",nullable = false, columnDefinition = "date")
    private Date date_start;

    @Column(updatable = true,name="Date_Graduation",nullable = false, columnDefinition = "date")
    private Date date_graduation;

    public Integer getID_User() {
        return ID_User;
    }

    public void setID_User(Integer ID_User) {
        this.ID_User = ID_User;
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

package de.dhbw_mannheim.pfu_server.sql.entities;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Studiengang {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer ID_Studiengang;

    @Column(nullable = false, name="Studiengang_Name",columnDefinition = "varchar(255)")
    private String Studiengang_Name;

    @Column(nullable = false,name="Number_of_Semesters",columnDefinition = "int")
    private Integer Number_of_Semesters;


    public Integer getID_Studiengang() {
        return ID_Studiengang;
    }

    public void setID_Studiengang(Integer ID_Studiengang) {
        this.ID_Studiengang = ID_Studiengang;
    }

    public String getStudiengang_Name() {
        return Studiengang_Name;
    }

    public void setStudiengang_Name(String studiengang_Name) {
        Studiengang_Name = studiengang_Name;
    }

    public Integer getNumber_of_Semesters() {
        return Number_of_Semesters;
    }

    public void setNumber_of_Semesters(Integer number_of_Semesters) {
        Number_of_Semesters = number_of_Semesters;
    }
}

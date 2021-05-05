package de.dhbw_mannheim.pfu_server.sql.entities;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = true,name="ID_Studiengang",nullable = false,columnDefinition = "int")
    private Integer ID_Studiengang;

    @Column(updatable = true,name="Course_Name",nullable = false,columnDefinition = "varchar(255)")
    private String Course_Name;

    public Integer getId() {
        return ID_Studiengang;
    }

    public void setId(Integer id) {
        this.ID_Studiengang = id;
    }

    public String getName() {
        return Course_Name;
    }

    public void setName(String name) {
        this.Course_Name = name;
    }
}

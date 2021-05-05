package de.dhbw_mannheim.pfu_server.sql.entities;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Student {

    @Id
    @OneToOne(mappedBy="user",cascade = CascadeType.ALL)
    private Integer ID_User;

    @Column(updatable = true,name="first_name",nullable = false,columnDefinition = "varchar(255)")
    private String first_name;

    @Column(updatable = true,name="last_name",nullable = false,columnDefinition = "varchar(255)")
    private String last_name;

    @Column(updatable = true,name="e-mail",nullable = false, columnDefinition = "varchar(255)")
    private String email;

    @Column(updatable = true, nullable = false,columnDefinition = "varchar(1000)")
    private String password_encrypted;




}

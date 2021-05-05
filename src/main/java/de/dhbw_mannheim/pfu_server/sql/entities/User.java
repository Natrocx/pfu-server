package de.dhbw_mannheim.pfu_server.sql.entities;

import javax.persistence.*;

@Entity(name="user") // This tells Hibernate to make a table out of this class
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @OneToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name="ID_User")
    private Integer ID_User;

    @Column(updatable = true,name="first_name",nullable = false,columnDefinition = "varchar(255)")
    private String first_name;

    @Column(updatable = true,name="last_name",nullable = false,columnDefinition = "varchar(255)")
    private String last_name;

    @Column(updatable = true,name="e-mail",nullable = false, columnDefinition = "varchar(255)")
    private String email;

    @Column(updatable = true, name="password_encrypted", nullable = false,columnDefinition = "varchar(1000)")
    private String password_encrypted;


    public Integer getID_User() {
        return ID_User;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_encrypted() {
        return password_encrypted;
    }

    public void setPassword_encrypted(String password_encrypted) {
        this.password_encrypted = password_encrypted;
    }

}

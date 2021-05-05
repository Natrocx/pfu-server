package de.dhbw_mannheim.pfu_server.sql.relations;

import javax.persistence.Column;

public class Student_isEmployedBy_Company {

    @Column(updatable = true,name="ID_User",nullable = false, columnDefinition = "int")
    private Integer ID_User;

    @Column(updatable = true,name="ID_Company",nullable = false, columnDefinition = "int")
    private Integer ID_Company;

    @Column(updatable = true,name="Location",nullable = false, columnDefinition = "mediumtext")
    private String Location;


}

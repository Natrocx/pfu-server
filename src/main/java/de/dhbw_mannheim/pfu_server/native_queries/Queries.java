package de.dhbw_mannheim.pfu_server.native_queries;

import java.util.List;

public class Queries {
    //TODO Nutzerinfos
    //
    public boolean createUser(String first_name, String last_name, String eMail, String password){
        QueryManager qm = new QueryManager();
        String query = "INSERT INTO user (first_name, last_name, `e-mail`, password_encrypted)" +
                " VALUES (" + first_name + ", " + last_name + ", " + eMail + ", " + password + ")";

        return qm.sqlStatement(query);
    }

    public boolean createStudent(String ID_User, String Biography, String Course_Name){
        QueryManager qm = new QueryManager();

        String query = "INSERT INTO student (ID_User, Biography, Course_Name)" +
                " VALUES (" + ID_User + ", " + Biography + ", " + Course_Name  + ")";

        return qm.sqlStatement(query);
    }
}

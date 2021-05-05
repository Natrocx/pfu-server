package de.dhbw_mannheim.pfu_server.sql.relations;

import javax.persistence.Column;
import java.sql.Timestamp;

public class User_sends_Message_to_User {

    @Column(updatable = true,name="ID_User1",nullable = false, columnDefinition = "int")
    private Integer ID_User1;

    @Column(updatable = true,name="ID_User2",nullable = false, columnDefinition = "int")
    private Integer ID_User2;

    @Column(updatable = true,name="ID_Message",nullable = false, columnDefinition = "int")
    private Integer ID_Message;

    @Column(updatable = true,name="Timestamp",nullable = false, columnDefinition = "timestamp")
    private Timestamp Timestamp;

    public Integer getID_User1() {
        return ID_User1;
    }

    public void setID_User1(Integer ID_User1) {
        this.ID_User1 = ID_User1;
    }

    public Integer getID_User2() {
        return ID_User2;
    }

    public void setID_User2(Integer ID_User2) {
        this.ID_User2 = ID_User2;
    }

    public Integer getID_Message() {
        return ID_Message;
    }

    public void setID_Message(Integer ID_Message) {
        this.ID_Message = ID_Message;
    }

    public java.sql.Timestamp getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(java.sql.Timestamp timestamp) {
        Timestamp = timestamp;
    }
}

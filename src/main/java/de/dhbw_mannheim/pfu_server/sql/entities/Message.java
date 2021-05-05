package de.dhbw_mannheim.pfu_server.sql.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Message {
    @Id
    @Column(updatable = true,name="ID_Message",nullable = false,columnDefinition = "int")
    private Integer ID_Message;

    @Id
    @Column(updatable = true,name="ID_MessageContent",nullable = false,columnDefinition = "int")
    private Integer ID_MessageContent;

    public Integer getID_Message() {
        return ID_Message;
    }

    public void setID_Message(Integer ID_Message) {
        this.ID_Message = ID_Message;
    }

    public Integer getID_MessageContent() {
        return ID_MessageContent;
    }

    public void setID_MessageContent(Integer ID_MessageContent) {
        this.ID_MessageContent = ID_MessageContent;
    }
}

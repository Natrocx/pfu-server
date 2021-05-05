package de.dhbw_mannheim.pfu_server.sql.entities;

import javax.persistence.*;

@Entity(name="messagecontent")
public class MessageContent {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(updatable = true,name="ID_MessageContent",nullable = false,columnDefinition = "int")
    private Integer ID_MessageContent;

    @Column(updatable = true,name="ContentType",nullable = false,columnDefinition = "varchar(255)")
    private String ContentType;

    @Column(updatable = true,name="Content",nullable = false,columnDefinition = "longtext")
    private String Content;


    public Integer getID_MessageContent() {
        return ID_MessageContent;
    }

    public void setID_MessageContent(Integer ID_MessageContent) {
        this.ID_MessageContent = ID_MessageContent;
    }

    public String getContentType() {
        return ContentType;
    }

    public void setContentType(String contentType) {
        ContentType = contentType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}

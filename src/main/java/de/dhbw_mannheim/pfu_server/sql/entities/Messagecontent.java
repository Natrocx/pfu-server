package de.dhbw_mannheim.pfu_server.sql.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Messagecontent {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(updatable = true,name="ID_MessageContent",nullable = false,columnDefinition = "int")
    private Integer ID_MessageContent;

    @Column(updatable = true,name="first_name",nullable = false,columnDefinition = "varchar(255)")
    private String ContentType;

    @Column(updatable = true,name="first_name",nullable = false,columnDefinition = "longtext")
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

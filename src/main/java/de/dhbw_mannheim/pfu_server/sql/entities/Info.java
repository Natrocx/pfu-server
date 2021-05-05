package de.dhbw_mannheim.pfu_server.sql.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Info {
    @Id
    @Column(updatable = true,name="ID_Info",nullable = false,columnDefinition = "int")
    private Integer ID_Info;

    @Column(updatable = true,name="Display_Name",nullable = false,columnDefinition = "mediumtext")
    private String Display_Name;

    @Column(updatable = true,name="Kurzinfo",nullable = false,columnDefinition = "mediumtext")
    private String Kurzinfo;

    @Column(updatable = true,name="Priority",nullable = false,columnDefinition = "int")
    private Integer Priority;

    @Column(updatable = true,name="Textinfo",nullable = false,columnDefinition = "longtext")
    private String Textinfo;

    @Column(updatable = true,name="Weblink",nullable = false,columnDefinition = "longtext")
    private String Weblink;

    public Integer getID_Info() {
        return ID_Info;
    }

    public void setID_Info(Integer ID_Info) {
        this.ID_Info = ID_Info;
    }

    public String getDisplay_Name() {
        return Display_Name;
    }

    public void setDisplay_Name(String display_Name) {
        Display_Name = display_Name;
    }

    public String getKurzinfo() {
        return Kurzinfo;
    }

    public void setKurzinfo(String kurzinfo) {
        Kurzinfo = kurzinfo;
    }

    public Integer getPriority() {
        return Priority;
    }

    public void setPriority(Integer priority) {
        Priority = priority;
    }

    public String getTextinfo() {
        return Textinfo;
    }

    public void setTextinfo(String textinfo) {
        Textinfo = textinfo;
    }

    public String getWeblink() {
        return Weblink;
    }

    public void setWeblink(String weblink) {
        Weblink = weblink;
    }
}

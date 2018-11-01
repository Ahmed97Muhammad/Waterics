package com.example.bilalsalman.waterics;

public class cardViewClass {

    private String id;
    private String supervisor;
    private String ack;
    private String date;
    private String res;
    private String type;
    private String image;

    public cardViewClass(String id, String supervisor, String ack, String date, String res, String type, String image) {

        this.id = id;
        this.supervisor = supervisor;
        this.ack = ack;
        this.date = date;
        this.res = res;
        this.type = type;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getAck() {
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

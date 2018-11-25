package com.example.bilalsalman.waterics;

public class admin_complaintcard {

    String date;
    String complaintID;
    String add;
    boolean ack;
    String type;

    String msg;
    String eta;

    String uid;

    public admin_complaintcard(String date, String complaintID, String add, String type, String msg, String eta, String uid) {
        this.date = date;
        this.complaintID = complaintID;
        this.add = add;
        this.ack = false;
        this.type = type;
        this.msg = msg;
        this.eta = eta;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }



    public admin_complaintcard(String date, String complaintID, String add, String type, String msg, String eta)
    {
        this.date = date;
        this.complaintID=complaintID;
        this.ack=false;
        this.add = add;
        this.type = type;
        this.msg = msg;
        this.eta = eta;
    }

    public admin_complaintcard()
    {

    }

    public String getComplaintID()
    {
        return complaintID;
    }

    public void acknowledge()
    {
        ack=true;
    }

    public boolean isAck()
    {
        return ack;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }
}

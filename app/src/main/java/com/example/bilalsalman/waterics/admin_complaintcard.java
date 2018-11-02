package com.example.bilalsalman.waterics;

public class admin_complaintcard {

    String date;
    String complaintID;
    String add;
    boolean ack;
    String type;


    String supername;
    String superphone;
    String eta;

    public admin_complaintcard(String s, String t, String u, String v, String w, String x, String y)
    {
        date = s;
        complaintID=t;
        ack=false;
        add = u;
        type = v;
        supername = w;
        superphone = x;
        eta = y;
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

    public String getSupername() {
        return supername;
    }

    public void setSupername(String supername) {
        this.supername = supername;
    }

    public String getSuperphone() {
        return superphone;
    }

    public void setSuperphone(String superphone) {
        this.superphone = superphone;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }
}

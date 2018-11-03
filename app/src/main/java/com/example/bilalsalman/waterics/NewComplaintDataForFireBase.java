package com.example.bilalsalman.waterics;

public class NewComplaintDataForFireBase {

    String date;
    String type;
    String id;
    String uname;
    String address;
    boolean ack;
    String pnumber;
    String msg;


    public NewComplaintDataForFireBase(String date, String type, String id, String uname, String address, boolean ack, String pnumber, String msg) {
        this.date = date;
        this.type = type;
        this.id = id;
        this.uname = uname;
        this.address = address;
        this.ack = ack;
        this.pnumber = pnumber;
        this.msg = msg;
    }

    public NewComplaintDataForFireBase()
    {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAck() {
        return ack;
    }

    public void setAck(boolean ack) {
        this.ack = ack;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

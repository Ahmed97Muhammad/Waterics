package com.example.bilalsalman.waterics;

public class UserDataForFireBase {

    String address,num,bill,fname,id;

    public UserDataForFireBase(String address, String num, String bill, String fname, String id) {
        this.address = address;
        this.num = num;
        this.bill = bill;
        this.fname = fname;
        this.id = id;
    }
    public UserDataForFireBase()
    {

    }

    @Override
    public String toString() {
        return "UserDataForFireBase{" +
                "address='" + address + '\'' +
                ", num='" + num + '\'' +
                ", bill='" + bill + '\'' +
                ", fname='" + fname + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

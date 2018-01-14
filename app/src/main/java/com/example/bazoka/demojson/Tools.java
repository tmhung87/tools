package com.example.bazoka.demojson;

import java.io.Serializable;

/**
 * Created by bazoka on 30/12/2017.
 */

public class Tools implements Serializable {
    int id;
    String ten,prn,sn,anh1,anh2,anh3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tools(String ten, String prn, String sn, String anh1, String anh2, String anh3) {
        this.ten = ten;
        this.prn = prn;
        this.sn = sn;
        this.anh1 = anh1;
        this.anh2 = anh2;
        this.anh3 = anh3;
    }

    public Tools() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getPrn() {
        return prn;
    }

    public void setPrn(String prn) {
        this.prn = prn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getAnh1() {
        return anh1;
    }

    public void setAnh1(String anh1) {
        this.anh1 = anh1;
    }

    public String getAnh2() {
        return anh2;
    }

    public void setAnh2(String anh2) {
        this.anh2 = anh2;
    }

    public String getAnh3() {
        return anh3;
    }

    public void setAnh3(String anh3) {
        this.anh3 = anh3;
    }

    public Tools(int id, String ten, String prn, String sn, String anh1, String anh2, String anh3) {
        this.id = id;
        this.ten = ten;
        this.prn = prn;
        this.sn = sn;
        this.anh1 = anh1;
        this.anh2 = anh2;
        this.anh3 = anh3;
    }
}

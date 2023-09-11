package com.example.defencedrive.admin.ui.home;

public class CenterDataHolder {
    String UID,Cname,Caddress,Week,HalfM,Month;

    public CenterDataHolder(String Uid , String cname, String caddress, String week, String halfM, String month) {
        UID = Uid;
        Cname = cname;
        Caddress = caddress;
        Week = week;
        HalfM = halfM;
        Month = month;
    }

    public CenterDataHolder() {
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getCaddress() {
        return Caddress;
    }

    public void setCaddress(String caddress) {
        Caddress = caddress;
    }

    public String getWeek() {
        return Week;
    }

    public void setWeek(String week) {
        Week = week;
    }

    public String getHalfM() {
        return HalfM;
    }

    public void setHalfM(String halfM) {
        HalfM = halfM;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }
}

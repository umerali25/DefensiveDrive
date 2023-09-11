package com.example.defencedrive.AccountCreation.Holder;

import android.icu.text.StringPrepParseException;

public class DataHolder {

    String Name;

    String Email;
    String UserId;



    boolean IsAdmin;

    public DataHolder(String name, String email,String userId,boolean isAdmin) {
        Name = name;
        Email = email;
        UserId=userId;
        IsAdmin = isAdmin;
    }

    public DataHolder() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
    public boolean isAdmin() {
        return IsAdmin;
    }

    public void setAdmin(boolean admin) {
        IsAdmin = admin;
    }


}


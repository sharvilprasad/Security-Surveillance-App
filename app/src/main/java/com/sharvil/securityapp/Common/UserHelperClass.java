package com.sharvil.securityapp.Common;

public class UserHelperClass {

    String Name, Email, Phoneno, Buildingname, Flatno, Uid;

    public UserHelperClass(){
    }
    public UserHelperClass(String name, String email, String phoneno, String buildingname, String flatno, String uid) {
        Name = name;
        Email = email;
        Phoneno = phoneno;
        Buildingname = buildingname;
        Flatno = flatno;
        Uid = uid;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public UserHelperClass(String uid) {
        Uid = uid;
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

    public String getPhoneno() {
        return Phoneno;
    }

    public void setPhoneno(String phoneno) {
        Phoneno = phoneno;
    }

    public String getBuildingname() {
        return Buildingname;
    }

    public void setBuildingname(String buildingname) {
        Buildingname = buildingname;
    }

    public String getFlatno() {
        return Flatno;
    }

    public void setFlatno(String flatno) {
        Flatno = flatno;
    }
}
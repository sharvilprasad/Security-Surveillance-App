package com.sharvil.securityapp.Security;

public class RequestFormHelperClass {

    String Name,Phoneno,Reason,Form,Flatno,Buildingname, CurrentDate;

    public RequestFormHelperClass(){
    }

    public RequestFormHelperClass(String name, String phoneno , String reason, String form, String flatno, String buildingname, String currentDate){
        Name = name;
        Phoneno = phoneno;
        Reason = reason;
        Form = form;
        Flatno = flatno;
        Buildingname = buildingname;
        CurrentDate = currentDate;
    }

    public String getCurrentDate() {
        return CurrentDate;
    }

    public void setCurrentDate(String currentDate) {
        CurrentDate = currentDate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneno() {
        return Phoneno;
    }

    public void setPhoneno(String phoneno) {
        Phoneno = phoneno;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getForm() {
        return Form;
    }

    public void setForm(String form) {
        Form = form;
    }

    public String getFlatno() {
        return Flatno;
    }

    public void setFlatno(String flatno) {
        Flatno = flatno;
    }

    public String getBuildingname() {
        return Buildingname;
    }

    public void setBuildingname(String buildingname) {
        Buildingname = buildingname;
    }
}

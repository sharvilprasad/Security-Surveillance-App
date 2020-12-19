package com.sharvil.securityapp.HelperClasses.HomeAdapter;

public class RequestHelperClass {

    int image;
    String name,dateandtime,reason;

    public RequestHelperClass(int image, String name, String dateandtime, String reason) {
        this.image = image;
        this.name = name;
        this.dateandtime = dateandtime;
        this.reason = reason;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDateandtime() {
        return dateandtime;
    }

    public String getReason() {
        return reason;
    }
}

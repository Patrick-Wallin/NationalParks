/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwmobiledeveloper.nationalparksdatabase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Patrick Wallin
 */
public class StandardHour {
    @SerializedName("wednesday")
    @Expose
    private String wednesday = "";
    @SerializedName("monday")
    @Expose
    private String monday = "";
    @SerializedName("thursday")
    @Expose
    private String thursday = "";
    @SerializedName("sunday")
    @Expose
    private String sunday = "";
    @SerializedName("tuesday")
    @Expose
    private String tuesday = "";
    @SerializedName("friday")
    @Expose
    private String friday = "";
    @SerializedName("saturday")
    @Expose
    private String saturday = "";

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }
    
    @Override
    public String toString() {
        return String.join(",", new String[] { 
            this.getSunday(),
            this.getMonday(),
            this.getTuesday(),
            this.getWednesday(),
            this.getThursday(),
            this.getFriday()
        });
    }

    public StandardHour() {}
}

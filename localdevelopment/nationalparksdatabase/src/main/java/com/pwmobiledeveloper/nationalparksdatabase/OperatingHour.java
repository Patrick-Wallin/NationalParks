/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwmobiledeveloper.nationalparksdatabase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrick Wallin
 */
public class OperatingHour {
    @SerializedName("exceptions")
    @Expose
    private List<Exception> exceptions = null;
    @SerializedName("description")
    @Expose
    private String description = "";
    @SerializedName("standardHours")
    @Expose
    private StandardHour standardHours = null;
    @SerializedName("name")
    @Expose
    private String name = "";

    public List<Exception> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<Exception> exceptions) {
        this.exceptions = exceptions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StandardHour getStandardHours() {
        return standardHours;
    }

    public void setStandardHours(StandardHour standardHours) {
        this.standardHours = standardHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    

    public String getOperatingHourQuery(int parkId, int operatingHourId) {
        operatingHourId++;
        
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO operating_hour(id,park_id,"
                + "description,name,standard_hours) VALUES (");
        //sb.append("(SELECT id FROM (SELECT COALESCE(MAX(id),0)+1 AS id FROM operatinghour) AS temp)");
        //sb.append("(SELECT id FROM (SELECT id AS id FROM park WHERE park_id = '" + parkId + "') AS temp)");
        sb.append(String.valueOf(operatingHourId).trim());
        sb.append(",");
        sb.append(String.valueOf(parkId).trim());       
        sb.append(",'");
        sb.append(this.getDescription().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getName().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getStandardHours().toString().replaceAll("'","''").trim());
        sb.append("')");
        
        return sb.toString();
    }

    public ArrayList<String> getOperatingHourExceptionQuery(int parkId, int operatingHourId, int exceptionId) {
        ArrayList<String> listOfQueries = new ArrayList<>();
        
        List<Exception> exceptions = this.getExceptions();
        if(!exceptions.isEmpty() && exceptions.size() > 0) {
            for(int i = 0; i < exceptions.size(); i++) {
                listOfQueries.add(exceptions.get(i).getExceptionQuery(operatingHourId, exceptionId));
                exceptionId++;
            }
        }

        return listOfQueries;
    }

    public OperatingHour() {}
}

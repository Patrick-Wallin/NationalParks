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
public class Exception {
    @SerializedName("exceptionHours")
    @Expose
    private ExceptionHour exceptionHours = null;
    @SerializedName("startDate")
    @Expose
    private String startDate = "";
    @SerializedName("name")
    @Expose
    private String name = "";
    @SerializedName("endDate")
    @Expose
    private String endDate = "";

    public ExceptionHour getExceptionHours() {
        return exceptionHours;
    }

    public void setExceptionHours(ExceptionHour exceptionHours) {
        this.exceptionHours = exceptionHours;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getExceptionQuery(int operatingHourId, int exceptionId) {
        exceptionId++;
        
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO exceptions(id,operating_hour_id,"
            + "name,start_date,end_date,exception_hours) VALUES (");
        //sb.append("(SELECT id FROM (SELECT COALESCE(MAX(id),0) AS id FROM operating_hour) AS temp2)");
        sb.append(String.valueOf(exceptionId));
        sb.append(",");
        sb.append(String.valueOf(operatingHourId));
        sb.append(",'");
        sb.append(this.getName().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getStartDate().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getEndDate().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getExceptionHours().toString().replaceAll("'","''").trim());
        sb.append("')");

        return sb.toString();
    }
    
    public Exception() {}
}

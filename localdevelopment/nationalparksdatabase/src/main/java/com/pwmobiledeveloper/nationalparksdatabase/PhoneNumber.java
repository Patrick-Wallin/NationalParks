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
public class PhoneNumber {
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber = "";
    @SerializedName("description")
    @Expose
    private String description = "";
    @SerializedName("extension")
    @Expose
    private String extension = "";
    @SerializedName("type")
    @Expose
    private String type = "";

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }    
    
    public String getPhoneNumberQuery(int parkId,int phoneNumberId) {
        phoneNumberId++;
        
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO contact_phone_number(id,park_id,"
                + "phonenumber,description,extension,type) VALUES (");
        //sb.append("(SELECT id FROM (SELECT COALESCE(MAX(id),0)+1 AS id FROM contact_phone_number) AS temp)");
        //sb.append("(SELECT id FROM (SELECT id AS id FROM park WHERE park_id = '" + parkId + "') AS temp)");
        sb.append(String.valueOf(phoneNumberId).trim());
        sb.append(",");
        sb.append(String.valueOf(parkId).trim());       
        sb.append(",'");
        sb.append(this.getPhoneNumber().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getDescription().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getExtension().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getType().replaceAll("'","''").trim());
        sb.append("')");

        return sb.toString();
    }

    public PhoneNumber() {}
    
    
}

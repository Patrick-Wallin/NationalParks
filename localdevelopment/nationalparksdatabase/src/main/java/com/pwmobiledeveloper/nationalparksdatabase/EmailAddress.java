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
public class EmailAddress {
    @SerializedName("description")
    @Expose
    private String description = "";
    @SerializedName("emailAddress")
    @Expose
    private String emailAddress = "";

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }    

    public String getEmailAddressQuery(int parkId, int contactEmailAddressId) {
        contactEmailAddressId++;
        
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO contact_email_address(id,park_id,"
                + "emailaddress,description) VALUES (");
        //sb.append("(SELECT id FROM (SELECT COALESCE(MAX(id),0)+1 AS id FROM contact_email_address) AS temp)");
        //sb.append("(SELECT id FROM (SELECT id AS id FROM park WHERE park_id = '" + parkId + "') AS temp)");
        sb.append(String.valueOf(contactEmailAddressId).trim());
        sb.append(",");
        sb.append(String.valueOf(parkId).trim());       
        sb.append(",'");
        sb.append(this.getEmailAddress().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getDescription().replaceAll("'","''").trim());
        sb.append("')");

        return sb.toString();
    }

    public EmailAddress() {}
}

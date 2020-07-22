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
public class Address {
    @SerializedName("postalCode")
    @Expose
    private String postalCode = "";
    @SerializedName("city")
    @Expose
    private String city = "";
    @SerializedName("stateCode")
    @Expose
    private String stateCode = "";
    @SerializedName("line1")
    @Expose
    private String line1 = "";
    @SerializedName("type")
    @Expose
    private String type = "";
    @SerializedName("line3")
    @Expose
    private String line3 = "";
    @SerializedName("line2")
    @Expose
    private String line2 = "";

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }    
    
    public String getAddressQuery(int parkId, int addressId) {
        addressId++;
        
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO address(id,park_id,"
                + "postalcode,city,statecode,line1,type,line3,line2) VALUES (");
        //sb.append("(SELECT id FROM (SELECT COALESCE(MAX(id),0)+1 AS id FROM address) AS temp)");
        //sb.append("(SELECT id FROM (SELECT id AS id FROM park WHERE park_id = '" + parkId + "') AS temp)");
        sb.append(String.valueOf(addressId).trim());
        sb.append(",");
        sb.append(String.valueOf(parkId).trim());       
        sb.append(",'");
        sb.append(this.getPostalCode().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getCity().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getStateCode().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getLine1().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getType().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getLine3().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getLine2().replaceAll("'","''").trim());        
        sb.append("')");

        return sb.toString();
    }
    

    public Address() {}
    
    
}

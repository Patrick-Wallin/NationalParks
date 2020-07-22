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
public class EntranceFee {
    @SerializedName("cost")
    @Expose
    private String cost = "";
    @SerializedName("description")
    @Expose
    private String description = "";
    @SerializedName("title")
    @Expose
    private String title = "";

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getEntranceFeeQuery(int parkId, int entranceFeeId) {
        entranceFeeId++;
        
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO entrancefee(id,park_id,"
                + "cost,description,title) VALUES (");
        //sb.append("(SELECT id FROM (SELECT COALESCE(MAX(id),0)+1 AS id FROM entrancefee) AS temp)");
        //sb.append("(SELECT id FROM (SELECT id AS id FROM park WHERE park_id = '" + parkId + "') AS temp)");
        sb.append(String.valueOf(entranceFeeId).trim());
        sb.append(",");
        sb.append(String.valueOf(parkId).trim());       
        sb.append(",'");
        sb.append(this.getCost().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getDescription().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getTitle().replaceAll("'","''").trim());
        sb.append("')");

        return sb.toString();
    }

    public EntranceFee() {}
}

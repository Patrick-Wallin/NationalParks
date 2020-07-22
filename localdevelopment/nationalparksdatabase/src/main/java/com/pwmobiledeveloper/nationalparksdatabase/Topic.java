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
public class Topic {
    @SerializedName("id")
    @Expose
    private String id = "";
    @SerializedName("name")
    @Expose
    private String name = "";
    
    public Topic() {}
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getTopicQuery(int parkId, int topicId) {
        topicId++;
        
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO topic(id,park_id,"
                + "topic_id,name) VALUES (");
        //sb.append("(SELECT id FROM (SELECT COALESCE(MAX(id),0)+1 AS id FROM topic) AS temp)");
        //sb.append("(SELECT id FROM (SELECT id AS id FROM park WHERE park_id = '" + parkId + "') AS temp)");
        sb.append(String.valueOf(topicId).trim());
        sb.append(",");
        sb.append(String.valueOf(parkId).trim());       
        sb.append(",'");
        sb.append(this.getId().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getName().replaceAll("'","''").trim());
        sb.append("')");

        return sb.toString();
    }

}

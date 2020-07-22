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
public class Image {
    @SerializedName("credit")
    @Expose
    private String credit = "";
    @SerializedName("altText")
    @Expose
    private String altText = "";
    @SerializedName("title")
    @Expose
    private String title = "";
    @SerializedName("caption")
    @Expose
    private String caption = "";
    @SerializedName("url")
    @Expose
    private String url = "";

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }    
    
    public String getImageQuery(int parkId, int imageId) {
        imageId++;
        
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO image(id,park_id,"
                + "credit,alttext,title,caption,url) VALUES (");
        //sb.append("(SELECT id FROM (SELECT COALESCE(MAX(id),0)+1 AS id FROM image) AS temp)");
        //sb.append("(SELECT id FROM (SELECT id AS id FROM park WHERE park_id = '" + parkId + "') AS temp)");
        sb.append(String.valueOf(imageId).trim());
        sb.append(",");
        sb.append(String.valueOf(parkId).trim());       
        sb.append(",'");
        sb.append(this.getCredit().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getAltText().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getTitle().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getCaption().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getUrl().replaceAll("'","''").trim());
        sb.append("')");

        return sb.toString();
    }
    

    public Image() {}
}

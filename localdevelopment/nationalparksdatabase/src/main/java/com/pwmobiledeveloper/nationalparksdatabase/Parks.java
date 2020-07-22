/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwmobiledeveloper.nationalparksdatabase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 *
 * @author Patrick Wallin
 */
public class Parks {
    @SerializedName("total")
    @Expose
    private String total = "";
    @SerializedName("limit")
    @Expose
    private String limit = "";
    @SerializedName("start")
    @Expose
    private String start = "";    
    @SerializedName("data")
    @Expose
    private List<Data> data = null;

    public Parks() {}

    public String getTotal() { return this.total; }
    public void setTotal(String total) { this.total = total; }
    public String getLimit() { return this.limit; }
    public void setLimit(String limit) { this.limit = limit; }
    public String getStart() { return this.start; }
    public void setStart(String start) { this.start = start; }
    public List<Data> getData() { return this.data; }
    public void setData(List<Data> data) { this.data = data; }
    
    
    
    
}
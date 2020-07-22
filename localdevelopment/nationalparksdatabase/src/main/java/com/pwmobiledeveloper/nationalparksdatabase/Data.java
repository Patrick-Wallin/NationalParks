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
public class Data {
    @SerializedName("contacts")
    @Expose
    private Contact contacts = null;
    @SerializedName("states")
    @Expose
    private String states = "";
    @SerializedName("longitude")
    @Expose
    private String longitude = "";
    @SerializedName("activities")
    @Expose
    private List<Activity> activities = null;
    @SerializedName("entranceFees")
    @Expose
    private List<EntranceFee> entranceFees = null;
    @SerializedName("directionsInfo")
    @Expose
    private String directionsInfo = "";
    @SerializedName("entrancePasses")
    @Expose
    private List<EntrancePass> entrancePasses = null;
    @SerializedName("directionsUrl")
    @Expose
    private String directionsUrl = "";
    @SerializedName("url")
    @Expose
    private String url = "";
    @SerializedName("weatherInfo")
    @Expose
    private String weatherInfo = "";
    @SerializedName("name")
    @Expose
    private String name = "";
    @SerializedName("operatingHours")
    @Expose
    private List<OperatingHour> operatingHours = null;
    @SerializedName("topics")
    @Expose
    private List<Topic> topics = null;
    @SerializedName("latLong")
    @Expose
    private String latLong = "";
    @SerializedName("description")
    @Expose
    private String description = "";
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("designation")
    @Expose
    private String designation = "";
    @SerializedName("parkCode")
    @Expose
    private String parkCode = "";
    @SerializedName("addresses")
    @Expose
    private List<Address> addresses = null;
    @SerializedName("id")
    @Expose
    private String id = "";
    @SerializedName("fullName")
    @Expose
    private String fullName = "";
    @SerializedName("latitude")
    @Expose
    private String latitude = "";

    public Contact getContacts() {
        return contacts;
    }

    public void setContacts(Contact contacts) {
        this.contacts = contacts;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<EntranceFee> getEntranceFees() {
        return entranceFees;
    }

    public void setEntranceFees(List<EntranceFee> entranceFees) {
        this.entranceFees = entranceFees;
    }

    public String getDirectionsInfo() {
        return directionsInfo;
    }

    public void setDirectionsInfo(String directionsInfo) {
        this.directionsInfo = directionsInfo;
    }

    public List<EntrancePass> getEntrancePasses() {
        return entrancePasses;
    }

    public void setEntrancePasses(List<EntrancePass> entrancePasses) {
        this.entrancePasses = entrancePasses;
    }

    public String getDirectionsUrl() {
        return directionsUrl;
    }

    public void setDirectionsUrl(String directionsUrl) {
        this.directionsUrl = directionsUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(String weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OperatingHour> getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(List<OperatingHour> operatingHours) {
        this.operatingHours = operatingHours;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public String getLatLong() {
        return latLong;
    }

    public void setLatLong(String latLong) {
        this.latLong = latLong;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getParkCode() {
        return parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    
    public String getParkRecordQuery(int id) {
        id++;
        
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO park(id,park_id,description,"
                + "designation,directionsinfo,directionsurl,"
                + "fullname,latlong,longitude,name,parkcode,"
                + "states,url,weatherinfo) VALUES (");
        sb.append(String.valueOf(id).trim());
        sb.append(",'");
        sb.append(this.getId().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getDescription().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getDesignation().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getDirectionsInfo().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getDirectionsUrl().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getFullName().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getLatLong().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getLongitude().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getName().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getParkCode().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getStates().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getUrl().replaceAll("'","''").trim());
        sb.append("','");
        sb.append(this.getWeatherInfo().replaceAll("'","''").trim());
        sb.append("')");
        
        return sb.toString();
    }
    
    public ArrayList<String> getContactPhoneNumbers(int parkId, int contactPhoneNumberId) {
        ArrayList<String> phoneNumberLists = new ArrayList<>();
        
        if(this.getContacts() != null) {
            List<PhoneNumber> phoneNumbers = this.getContacts().getPhoneNumbers();
            if(!phoneNumbers.isEmpty()) {
                for(int i = 0; i < phoneNumbers.size(); i++) {
                    phoneNumberLists.add(phoneNumbers.get(i).getPhoneNumberQuery(parkId,contactPhoneNumberId));
                    contactPhoneNumberId++;
                }
            }
        }
        
        return phoneNumberLists;
    }

    public ArrayList<String> getContactEmailAddresses(int parkId, int contactEmailAddressId) {
        ArrayList<String> emailAddressesLists = new ArrayList<String>();
        
        if(this.getContacts() != null) {
            List<EmailAddress> emailAddresses = this.getContacts().getEmailAddresses();
            if(!emailAddresses.isEmpty()) {
                for(int i = 0; i < emailAddresses.size(); i++) {
                    emailAddressesLists.add(emailAddresses.get(i).getEmailAddressQuery(parkId,contactEmailAddressId));
                    contactEmailAddressId++;
                }
            }
        }
        return emailAddressesLists;
    }

    public ArrayList<String> getParkEntranceFees(int parkId, int entranceFeeId) {
        ArrayList<String> entranceFeeLists = new ArrayList<String>();
        
        List<EntranceFee> listFees = this.getEntranceFees();
        
        if(listFees != null && !listFees.isEmpty()) {
            for(int i = 0; i < listFees.size(); i++) {
                entranceFeeLists.add(listFees.get(i).getEntranceFeeQuery(parkId,entranceFeeId));
                entranceFeeId++;
            }
        }
        return entranceFeeLists;
    }

    public ArrayList<String> getParkEntrancePasses(int parkId, int entrancePassId) {
        ArrayList<String> entrancePassLists = new ArrayList<String>();
        
        List<EntrancePass> listPasses = this.getEntrancePasses();
        
        if(listPasses != null && !listPasses.isEmpty()) {
            for(int i = 0; i < listPasses.size(); i++) {
                entrancePassLists.add(listPasses.get(i).getEntrancePassQuery(parkId,entrancePassId));
                entrancePassId++;
            }
        }
        return entrancePassLists;
    }

    public ArrayList<String> getParkActivities(int parkId, int activityId) {
        ArrayList<String> activityLists = new ArrayList<String>();
        
        List<Activity> listActivity = this.getActivities();
        
        if(listActivity != null && !listActivity.isEmpty()) {
            for(int i = 0; i < listActivity.size(); i++) {
                activityLists.add(listActivity.get(i).getActivityQuery(parkId,activityId));
                activityId++;
            }
        }
        return activityLists;
    }

    public ArrayList<String> getParkTopics(int parkId, int topicId) {
        ArrayList<String> topicLists = new ArrayList<String>();
        
        List<Topic> listTopic = this.getTopics();
        
        if(listTopic != null && !listTopic.isEmpty()) {
            for(int i = 0; i < listTopic.size(); i++) {
                topicLists.add(listTopic.get(i).getTopicQuery(parkId, topicId));
                topicId++;
            }
        }
        return topicLists;
    }

    public ArrayList<String> getParkImages(int parkId, int imageId) {
        ArrayList<String> imageLists = new ArrayList<String>();
        
        List<Image> listImage = this.getImages();
        
        if(listImage != null && !listImage.isEmpty()) {
            for(int i = 0; i < listImage.size(); i++) {
                imageLists.add(listImage.get(i).getImageQuery(parkId,imageId));
                imageId++;
            }
        }
        return imageLists;
    }

    public ArrayList<String> getParkAddresses(int parkId, int addressId) {
        ArrayList<String> addressLists = new ArrayList<String>();
        
        List<Address> listAddress = this.getAddresses();
        
        if(listAddress != null && !listAddress.isEmpty()) {
            for(int i = 0; i < listAddress.size(); i++) {
                addressLists.add(listAddress.get(i).getAddressQuery(parkId,addressId));
                addressId++;
            }
        }
        return addressLists;
    }

    public ArrayList<String> getParkOperatingHours(int parkId, int operatingHourId) {
        ArrayList<String> operatingHourLists = new ArrayList<String>();
        
        List<OperatingHour> listOperatingHours = this.getOperatingHours();
        
        if(listOperatingHours != null && !listOperatingHours.isEmpty()) {
            for(int i = 0; i < listOperatingHours.size(); i++) {
                operatingHourLists.add(listOperatingHours.get(i).getOperatingHourQuery(parkId,operatingHourId));
                operatingHourId++;
            }
        }
        
        return operatingHourLists;
    }

    public ArrayList<String> getParkException(int parkId, int operatingHourId,int exceptionId) {
        ArrayList<String> exceptionLists = new ArrayList<String>();
        
        List<OperatingHour> listOperatingHours = this.getOperatingHours();
        
        if(listOperatingHours != null && !listOperatingHours.isEmpty()) {
            for(int i = 0; i < listOperatingHours.size(); i++) {
                List<String> listQueries = listOperatingHours.get(i).getOperatingHourExceptionQuery(parkId,operatingHourId,exceptionId);
                exceptionId++;
                for(int x = 0; x < listQueries.size(); x++) {
                    exceptionLists.add(listQueries.get(x));
                }
            }
        }
        return exceptionLists;
    }
    
    public Data() {}
    
    
}


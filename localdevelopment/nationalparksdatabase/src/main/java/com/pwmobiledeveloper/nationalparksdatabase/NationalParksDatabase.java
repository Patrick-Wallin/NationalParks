/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwmobiledeveloper.nationalparksdatabase;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick Wallin
 */
public class NationalParksDatabase {
    private static String National_Parks_Service_API = "";
    private static String National_Parks_URL = "https://developer.nps.gov/api/v1/";
    private static String DATABASE_PATH = "C:/projects/nationalparks/database/";
    private static String JSON_PATH = "C:/projects/nationalparks/json/";
    private static String JSON_FILE_NAME = "national_parks";
    
    //private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    
    private static final String DB_URL = "jdbc:mysql://localhost/";

    private static final String USERNAME_DATABASE = "root";
    private static final String PASSWORD_DATABASE = "";
    private static final String DATABASE_NAME = "nationalpark";

    private static HashMap<String,Integer> listTableWithCount = null;
        
    public static void main(String[] args) {
        /*
        deleteAllJSONFiles();
        
        String jsonvalue = downloadParks(1, 0);
        int total = getTotalRecordsBasedOnJSONFile(jsonvalue);
        int numberOfJSONFiles = 0;
        
        if(total > 0) {
            int limit = 0;
            int start = 0;
            while(total > 0) {
                numberOfJSONFiles++;
                limit = (total > 100 ? 100 : total);
                jsonvalue = downloadParks(limit, start);
                start = start+limit;
                total = total-limit;
                System.out.println("Start: " + String.valueOf(start).trim() + " Limit: " + String.valueOf(limit).trim() + " Total: " + String.valueOf(total).trim());
                saveJSONDataToFile(jsonvalue,JSON_PATH + JSON_FILE_NAME + String.valueOf(numberOfJSONFiles).trim() + ".json");
            }
        }
        */
        int numberOfJSONFiles = 5;
        if(numberOfJSONFiles > 0) {
            HashMap<String,ArrayList<String>> listOfTableNames = getListOfTablesBasedOnDatabase();
            listTableWithCount = new HashMap<String,Integer>();
            for (Map.Entry pair : listOfTableNames.entrySet()) {
                listTableWithCount.put(pair.getKey().toString(), 0);
                System.out.println("name:" + pair.getKey().toString());
            }            
            
            deleteAllRecordsInAllTables(listOfTableNames);
            
            
            
            Gson gson = new Gson();
            for(int i = 1; i <= numberOfJSONFiles; i++) {
                try (Reader reader = new FileReader(JSON_PATH + JSON_FILE_NAME + String.valueOf(i).trim() + ".json")) {
                    Parks parks = gson.fromJson(reader, Parks.class);
                    List<Data> data = parks.getData();
                    insertAllDataIntoDatabase(data);
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }        
        }
    }
    
    public static void createNewDatabase(String fileName) {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + DATABASE_PATH + fileName;
            
            try (Connection conn = DriverManager.getConnection(url)) {
                if (conn != null) {
                    DatabaseMetaData meta = conn.getMetaData();
                    System.out.println("The driver name is " + meta.getDriverName());
                    System.out.println("A new database has been created.");
                }
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NationalParksDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void createNewDatabaseInMySQL() {
        Connection conn = null;
        Statement stmt = null;
        try{
           Class.forName("com.mysql.jdbc.Driver");

           System.out.println("Connecting to database...");
           conn = DriverManager.getConnection(DB_URL, USERNAME_DATABASE, PASSWORD_DATABASE);

           System.out.println("Creating database...");
           stmt = conn.createStatement();

           String deleteSQL = "DROP DATABASE " + DATABASE_NAME;
           stmt.executeUpdate(deleteSQL);
           System.out.println("Database deleted successfully...");
           String sql = "CREATE DATABASE " + DATABASE_NAME;
           stmt.executeUpdate(sql);
           System.out.println("Database created successfully...");
        }catch(SQLException se){
           se.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NationalParksDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           try{
              if(stmt!=null)
                 stmt.close();
           }catch(SQLException se2){
           }
           
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException se){
              se.printStackTrace();
           }//end finally try
        }//end try        
    }

    public static void createNewTableInMySQL() {
        Connection conn = null;
        Statement stmt = null;
        try{
           Class.forName("com.mysql.jdbc.Driver");

           System.out.println("Connecting to a selected database...");
           conn = DriverManager.getConnection(DB_URL+DATABASE_NAME, USERNAME_DATABASE, PASSWORD_DATABASE);

           System.out.println("Creating table tables...");
           stmt = conn.createStatement();

           String sql = "CREATE TABLE REGISTRATION " +
                   "(id INTEGER not NULL, " +
                   " first VARCHAR(255), " + 
                   " last VARCHAR(255), " + 
                   " age INTEGER, " + 
                   " PRIMARY KEY ( id ))"; 
           stmt.executeUpdate(sql);
           System.out.println("Table created successfully...");
        }catch(SQLException se){
           se.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NationalParksDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           try{
              if(stmt!=null)
                 stmt.close();
           }catch(SQLException se2){
           }
           
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException se){
              se.printStackTrace();
           }//end finally try
        }//end try        
    }
    
    private static String downloadParks(int p_iLimit, int p_iStart) {
        StringBuilder sbResultInJSON = new StringBuilder();
        
        URL urlNationalPark;
        HttpURLConnection con = null;
        
        String fullURL = National_Parks_URL+"parks?api_key="+National_Parks_Service_API+"&sort=parkCode";
        
        fullURL += "&limit=" + String.valueOf(p_iLimit).trim() + "&start=" + String.valueOf(p_iStart).trim();
        
        try {
            urlNationalPark = new URL (fullURL);
            con = (HttpURLConnection)urlNationalPark.openConnection();
    
            con.setRequestMethod("GET");
            
            con.connect();
            int status = con.getResponseCode();
            if(status == 200 || status == 201) {                
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    sbResultInJSON.append(line);                    
                }
                br.close();
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(NationalParksDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NationalParksDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                con.disconnect();
         }
      }
      return sbResultInJSON.toString();
    }
    
    private static boolean saveJSONDataToFile(String jsonData, String p_sFileName) {
        boolean bSuccess = true;
        
        File file = new File(p_sFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(jsonData);
        } catch (IOException ex) {
            bSuccess = false;
            Logger.getLogger(NationalParksDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bSuccess;
    }
    
    private static int getTotalRecordsBasedOnJSONFile(String p_sJson) {
        int result = 0;
        
        Gson gson = new Gson();
                
        Parks parks = gson.fromJson(p_sJson, Parks.class);
        
        result = Integer.parseInt(parks.getTotal());
        
        return result;
    }
    
    public static void deleteAllJSONFiles() {
        File dir = new File(JSON_PATH);
        for(File file: dir.listFiles()) 
            if (!file.isDirectory()) 
                file.delete();
    }

    public static HashMap<String,ArrayList<String>> getListOfTablesBasedOnDatabase() {
        HashMap<String,ArrayList<String>> tables = new HashMap<String,ArrayList<String>>();
         
        Connection conn = null;

        try{
           Class.forName(JDBC_DRIVER);

           System.out.println("Connecting to a selected database...");
           conn = DriverManager.getConnection(DB_URL+DATABASE_NAME, USERNAME_DATABASE, PASSWORD_DATABASE);
           DatabaseMetaData md = conn.getMetaData();
           ResultSet rs = md.getTables(DATABASE_NAME, "dbo", "%", new String[]{"TABLE"});
           while(rs.next()) {
               ArrayList<String> fk = new ArrayList<String>();
               
               
               String catalog = rs.getString("TABLE_CAT");
               String schema = rs.getString("TABLE_SCHEM");
                String tableName = rs.getString("TABLE_NAME");
                System.out.println("Table: " + tableName);
                try (ResultSet foreignKeys = md.getExportedKeys(catalog, schema, tableName)) {
                    while (foreignKeys.next()) {
                        fk.add(foreignKeys.getString("FKTABLE_NAME"));
                        //System.out.println("Foreign key: " + primaryKeys.getString("FKTABLE_NAME"));
                    }
                }
               tables.put(rs.getString("TABLE_NAME"), fk);
               
           }
           System.out.println("Gathering list of tables' names successfully...");
        }catch(SQLException se){
           se.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NationalParksDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException se){
              se.printStackTrace();
           }//end finally try
        }//end try 
        
        return tables;
    }
    
    public static void createDataFromJsonIntoDatabase(int numberOfJsonFiles) {
        
        if(numberOfJsonFiles > 0) {
            
        }
    }
    
    public static void deleteAllRecordsInAllTables(HashMap<String,ArrayList<String>> listOfTableNames) {
        Connection conn = null;
        Statement stmt = null;
        
        if(!listOfTableNames.isEmpty()) {
            try{
               Class.forName(JDBC_DRIVER);

               System.out.println("Connecting to a selected database...");
               conn = DriverManager.getConnection(DB_URL+DATABASE_NAME, USERNAME_DATABASE, PASSWORD_DATABASE);

                System.out.println("Deleting tables...");
                stmt = conn.createStatement();
               
                Iterator it = listOfTableNames.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    ArrayList<String> t = (ArrayList<String>) pair.getValue();
                    if(t.size() > 0) {
                        for(int i = 0; i < t.size(); i++) {
                    //System.out.println(pair.getKey() + " = " + pair.getValue());
                            String sql = "DELETE FROM " + t.get(i).trim();
                            System.out.println(t.get(i).trim());
                            stmt.executeUpdate(sql);                   
                        }
                        String sql = "DELETE FROM " + pair.getKey().toString().trim();
                        System.out.println(pair.getKey().toString().trim());
                        stmt.executeUpdate(sql);                   
                        
                    }else {
                        try {
                            String sql = "DELETE FROM " + pair.getKey().toString().trim();
                            System.out.println(pair.getKey().toString().trim());
                            stmt.executeUpdate(sql);                                           
                        }catch(SQLException se){
                            System.out.println("try to delete it!");
                        }
                    }
                    it.remove(); // avoids a ConcurrentModificationException
                }
                /*
                for(String name : listOfTableNames) {
                    String sql = "DELETE FROM " + name.trim();
                    stmt.executeUpdate(sql);                   
                }
                */
               System.out.println("Deleting all tables successfully...");
            }catch(SQLException se){
               se.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NationalParksDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
               try{
                  if(stmt!=null)
                     stmt.close();
               }catch(SQLException se2){
               }

               try{
                  if(conn!=null)
                     conn.close();
               }catch(SQLException se){
                  se.printStackTrace();
               }//end finally try
            }//end try        
        }        
    }
    
    public static void insertAllDataIntoDatabase(List<Data> data) {
        if(!data.isEmpty()) {
            Connection conn = null;
            Statement stmt = null;
                
            
            try {
                Class.forName(JDBC_DRIVER);
                
                System.out.println("Connecting to a selected database...");
                conn = DriverManager.getConnection(DB_URL+DATABASE_NAME, USERNAME_DATABASE, PASSWORD_DATABASE);
                conn.setAutoCommit(false);

                System.out.println("inserting tables...");
                stmt = conn.createStatement();

                int parkId = 0;
                int contactPhoneNumberId = 0;
                int contactEmailAddressId = 0;
                int entranceFeeId = 0;
                int entrancePassId = 0;
                int activityId = 0;
                int topicId = 0;
                int addressId = 0;
                int imageId = 0;
                int operatingHourId = 0;
                int exceptionsId = 0;
                int totalloop = 0;
                
                if(listTableWithCount.containsKey("park")) {
                    parkId = listTableWithCount.get("park");
                }
                if(listTableWithCount.containsKey("contact_phone_number")) {
                    contactPhoneNumberId = listTableWithCount.get("contact_phone_number");
                }
                if(listTableWithCount.containsKey("contact_email_address")) {
                    contactEmailAddressId = listTableWithCount.get("contact_email_address");
                }
                if(listTableWithCount.containsKey("entrancefee")) {
                    entranceFeeId = listTableWithCount.get("entrancefee");
                }
                if(listTableWithCount.containsKey("entrancepass")) {
                    entrancePassId = listTableWithCount.get("entrancepass");
                }
                if(listTableWithCount.containsKey("activity")) {
                    activityId = listTableWithCount.get("activity");
                }
                if(listTableWithCount.containsKey("topic")) {
                    topicId = listTableWithCount.get("topic");
                }
                if(listTableWithCount.containsKey("address")) {
                    addressId = listTableWithCount.get("address");
                }
                if(listTableWithCount.containsKey("image")) {
                    imageId = listTableWithCount.get("image");
                }
                if(listTableWithCount.containsKey("operating_hour")) {
                    operatingHourId = listTableWithCount.get("operating_hour");
                }
                if(listTableWithCount.containsKey("exceptions")) {
                    exceptionsId = listTableWithCount.get("exceptions");
                }
                
                for(int i = 0; i < data.size(); i++) {
                    if(!data.get(i).getId().isEmpty()) {
                        System.out.println("Adding batch...");
                        // Create query for park table
                        stmt.addBatch(data.get(i).getParkRecordQuery(parkId)); parkId++;
                        // Create query for Contact Phone Numbers table
                        totalloop += 1;
                        
                        ArrayList<String> contactPhoneNumbers = data.get(i).getContactPhoneNumbers(parkId,contactPhoneNumberId);
                        for(int x = 0; x < contactPhoneNumbers.size(); x++) {
                            stmt.addBatch(contactPhoneNumbers.get(x));
                        }
                        contactPhoneNumberId += contactPhoneNumbers.size();
                        totalloop += contactPhoneNumbers.size();
                        
                        // Create query for Contact Email Address table
                        ArrayList<String> contactEmailAddresses = data.get(i).getContactEmailAddresses(parkId,contactEmailAddressId);
                        for(int x = 0; x < contactEmailAddresses.size(); x++) {
                            stmt.addBatch(contactEmailAddresses.get(x)); 
                        }
                        contactEmailAddressId += contactEmailAddresses.size();
                        totalloop += contactEmailAddresses.size();
                        // Create query for Entrance Fee table
                        ArrayList<String> entranceFees = data.get(i).getParkEntranceFees(parkId,entranceFeeId);
                        for(int x = 0; x < entranceFees.size(); x++) {
                            stmt.addBatch(entranceFees.get(x)); 
                            
                        }
                        entranceFeeId += entranceFees.size();
                        totalloop += entranceFees.size();
                        // Create query for Entrance Pass table
                        ArrayList<String> entrancePasses = data.get(i).getParkEntrancePasses(parkId,entrancePassId);
                        for(int x = 0; x < entrancePasses.size(); x++) {
                            stmt.addBatch(entrancePasses.get(x));
                        }
                        entrancePassId += entrancePasses.size();
                        totalloop += entrancePasses.size();
                        // Create query for Activity table
                        ArrayList<String> activities = data.get(i).getParkActivities(parkId,activityId);
                        for(int x = 0; x < activities.size(); x++) {
                            stmt.addBatch(activities.get(x));
                        }
                        activityId += activities.size();
                        totalloop += activities.size();
                        // Create query for topic table
                        ArrayList<String> topics = data.get(i).getParkTopics(parkId,topicId);
                        for(int x = 0; x < topics.size(); x++) {
                            stmt.addBatch(topics.get(x));
                        }
                        topicId += topics.size();
                        totalloop += topics.size();
                        // Create query for image table
                        ArrayList<String> images = data.get(i).getParkImages(parkId,imageId);
                        for(int x = 0; x < images.size(); x++) {
                            stmt.addBatch(images.get(x));
                        }
                        imageId += images.size();
                        totalloop += images.size();
                        // Create query for address table
                        ArrayList<String> addresses = data.get(i).getParkAddresses(parkId,addressId);
                        for(int x = 0; x < addresses.size(); x++) {
                            stmt.addBatch(addresses.get(x)); 
                        }
                        addressId += addresses.size();
                        totalloop += addresses.size();
                        // Create query for operating hour table
                        ArrayList<String> operatingHours = data.get(i).getParkOperatingHours(parkId,operatingHourId);
                        for(int x = 0; x < operatingHours.size(); x++) {
                            stmt.addBatch(operatingHours.get(x)); 
                        }
                        operatingHourId += operatingHours.size();
                        totalloop += operatingHours.size();
                        
                        // Need to work on exceptions a little more complicated due to subarray within OperatingHours which is also array.
                        /*
                        // Create query for operating hour table
                        ArrayList<String> exceptions = data.get(i).getParkException(parkId,operatingHourId,exceptionsId);
                        for(int x = 0; x < exceptions.size(); x++) {
                            stmt.addBatch(exceptions.get(x)); 
                        }
                        exceptionsId += exceptions.size();
                        totalloop += exceptions.size();
                       */
                    }
                    
                    if(totalloop >= 1000) {
                        System.out.println("Executing batch...");
                        stmt.executeBatch();
                        totalloop = 0;
                    }
                    

                }

                if(listTableWithCount.containsKey("park")) {
                    listTableWithCount.put("park", parkId);
                }
                if(listTableWithCount.containsKey("contact_phone_number")) {
                    listTableWithCount.put("contact_phone_number",contactPhoneNumberId);
                }
                if(listTableWithCount.containsKey("contact_email_address")) {
                    listTableWithCount.put("contact_email_address",contactEmailAddressId);
                }
                if(listTableWithCount.containsKey("entrancefee")) {
                    listTableWithCount.put("entrancefee",entranceFeeId);
                }
                if(listTableWithCount.containsKey("entrancepass")) {
                    listTableWithCount.put("entrancepass",entrancePassId);
                }
                if(listTableWithCount.containsKey("activity")) {
                    listTableWithCount.put("activity",activityId);
                }
                if(listTableWithCount.containsKey("topic")) {
                    listTableWithCount.put("topic",topicId);
                }
                if(listTableWithCount.containsKey("address")) {
                    listTableWithCount.put("address",addressId);
                }
                if(listTableWithCount.containsKey("image")) {
                    listTableWithCount.put("image",imageId);
                }
                if(listTableWithCount.containsKey("operating_hour")) {
                    listTableWithCount.put("operating_hour",operatingHourId);
                }
                if(listTableWithCount.containsKey("exceptions")) {
                    listTableWithCount.put("exceptions",exceptionsId);
                }
                
                if(totalloop > 0) {
                    System.out.println("Executing batch...");
                    stmt.executeBatch();
                }
                
                conn.setAutoCommit(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NationalParksDatabase.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(NationalParksDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
               try{
                  if(stmt!=null)
                     stmt.close();
               }catch(SQLException se2){
               }

               try{
                  if(conn!=null)
                     conn.close();
               }catch(SQLException se){
                  se.printStackTrace();
               }//end finally try
            }//end try      

        }
    }    
}

package com.locationsimulator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

import com.locationsimulator.model.ErrorModel;

@Service
public class LocationSimulatorService {
    @Autowired
    private ConnectVendor connectVendor;
    
    @Autowired
    private ErrorModel errorModel;
    private static final Logger LOGGER = Logger.getLogger(LocationSimulatorService.class.getName());
    public String getlocationResults(String origin, String destination) {
        String response = connectVendor.locationResults(origin, destination);
        try {
            JSONObject result = new JSONObject(response);
            JSONArray jsonArray = new JSONArray();
            jsonArray = (JSONArray) result.get("routes");
            JSONObject obj = null;
            for(int count = 0; count < jsonArray.length(); count++)
            {
                if(count == 0) {
                    obj = jsonArray.getJSONObject(count);
                }
            }
            if(obj != null) {
                jsonArray = (JSONArray) obj.get("legs");
                System.out.println(jsonArray.get(0));
                LOGGER.log(Level.INFO, "The JSONARRAY is {0}",jsonArray.get(0));
                obj = null;
                for(int count = 0; count < jsonArray.length(); count++)
                {
                    if(count == 0) {
                        obj = jsonArray.getJSONObject(count);
                    }
                }
                if(obj != null) {
                    return createListLocationResponse((JSONArray) obj.get("steps"));
                }
            }   
        } catch(Exception e) {
            errorModel.setException(e);
            return errorModel.toString();
        }
        errorModel.setInfo("Something went wrong. Please try again with valid Inputs");
        return errorModel.toString();
    }
    
    public String createListLocationResponse(JSONArray jsonArray){
        ArrayList<String> list = new ArrayList<>();
        for(int count = 0; count < jsonArray.length(); count++)
        {
            JSONObject endLocation = (JSONObject) jsonArray.getJSONObject(count).get("end_location");
            JSONObject startLocation = (JSONObject) jsonArray.getJSONObject(count).get("start_location");
            if(count == 0) {
                String start = String.valueOf(startLocation.get("lat")) + " " + String.valueOf(startLocation.get("lng"));
                String end = String.valueOf(endLocation.get("lat")) + " " +String.valueOf(endLocation.get("lng"));
                
                list.add(start);
                list.add(end);
            }else {
                String end = String.valueOf(endLocation.get("lat")) + " " +String.valueOf(endLocation.get("lng"));
                list.add(end);
            }
        }
        return list.toString();
        
    }
    
}

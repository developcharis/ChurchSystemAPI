package com.cbfacademy.apiassessment.volunteer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ch.qos.logback.classic.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.stereotype.Repository;


@Repository
public class JsonVolunteerRepository implements VolunteerRepository {
    
    // Field declarations
    
    // List to hold Volunteer objects loaded from the JSON file.
    private List<Volunteer> volunteers = new ArrayList<>();
    
    // Gson instance for serializing and deserializing JSON data.
    private final Gson gson - new Gson();
   
    // Path to the JSON file where volunteer data is stored.
    private final String filePath = "C:/Users/missg/Desktop/CBF/ChurchSystemAPI/src/main/resources/volunteers.json";

    // Logger instance for logging errors and information.
    private static final Logger logger = LoggerFactory.getLogger(JsonVolunteerRepository.class);

    
    // Constructor that initialises the repository by loading volunteers from the JSON file.
    public JsonVolunteerRepository() {
        loadVolunteers();
    }
    
    // Intialisation methods 
    
    // Loads volunteers from the JSON file into the 'volunteers' list.
    private void loadVolunteers() {
        File file = new File(filePath);
        // Check if the file exists and is not empty
        if (file.exists() && file.length() != 0) {
            try (FileReader reader = new FileReader(filePath)) {
                Type listOfVolunteersType = new TypeToken<ArrayList<Volunteer>>() {}.getType();
                volunteers = gson.fromJson(reader, listOfVolunteersType);
                // Handle the case where the JSON file is empty or contains invalid data.
                if (volunteers == null) volunteers = new ArrayList<>();      
            } catch (IOException e) {
                logger.error("Failed to load volunteers from file: {}", filePath, e);
                volunteers = new ArrayList<>();
            }    
        } else {
            // Initialize an empty list if the file doesn't exist or is empty.
            volunteers = new ArrayList<>();
            }  
        }


}

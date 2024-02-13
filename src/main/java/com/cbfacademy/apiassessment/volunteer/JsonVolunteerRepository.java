package com.cbfacademy.apiassessment.volunteer;

import com.google.gson.Gson;

import ch.qos.logback.classic.Logger;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
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
    
    

}

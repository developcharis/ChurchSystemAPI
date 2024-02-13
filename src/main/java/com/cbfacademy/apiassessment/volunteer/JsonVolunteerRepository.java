package com.cbfacademy.apiassessment.volunteer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ch.qos.logback.classic.Logger;
import jakarta.annotation.PostConstruct;


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

        
    // Saves the current state of 'volunteers' list back to the JSON file.
    private void saveVolunteers() {
        try (FileWriter writer = new FileWriter(filePath)){
            gson.toJson(volunteers, writer);  
        } catch (IOException e) {
            logger.error("Failed to save volunteers to file: {}", filePath, e);
        }
    }

    // Initializes volunteer data with a predefined set if the JSON file is empty.
    @PostConstruct
    private void initVolunteersData() {
        if (volunteers.isEmpty()) {
            // Predefined volunteers added to the list for initial setup.
            // Example volunteer data is hardcoded here for demonstration.
            volunteers.add(new Volunteer(
                "Elizabeth", 
                "John", 
                LocalDate.of(1980,01,15), 
                "07584986718", 
                "lizzi.john@yahoo.com", 
                "Administrator", 
                Arrays.asList("Organisational","Attention to detail","Computer skills"), 
                true, 
                LocalDate.of(2023,8,15)
            ));

            volunteers.add(new Volunteer(
                "Matthew", 
                "Lazarus", 
                LocalDate.of(1962, 12, 07), 
                "07553698744", 
                "matthewlazarus12@gmail.com", 
                "Accountant", 
                Arrays.asList("Financial analysis", "Attention to detail", "Integrity"), 
                false, 
                LocalDate.of(2012, 04, 25)
            ));

            volunteers.add(new Volunteer(
                "Rachael", 
                "Mark", 
                LocalDate.of(2001, 11, 14), 
                "07956412843", 
                "markrachael2001@live.co.uk", 
                "Greeter", 
                Arrays.asList("Interpersonal skills", "Warmth", "Sign Language Proficiency"), 
                true, 
                LocalDate.of(2024, 01, 18)
            ));

            volunteers.add(new Volunteer(
                "Joshua", 
                "Nun", 
                LocalDate.of(1990, 01, 01), 
                "07383555777", 
                "j.nun@gmail.com", 
                "Choir Member", 
                Arrays.asList("Musical talent", "Teamwork", "Performance skills"), 
                true, 
                LocalDate.of(2020, 01, 01)
            ));

            volunteers.add(new Volunteer(
                "Sarah", 
                "Bethel", 
                LocalDate.of(1985, 05, 15), 
                "07958444888", 
                "bethelsarah@me.com", 
                "Accountant", 
                Arrays.asList("Financial reporting", "Analytical thinking", "Ethics"), 
                true, 
                LocalDate.of(2019, 05, 20)
            ));

        }
    }


}

package com.cbfacademy.apiassessment.volunteer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.annotation.PostConstruct;




@Repository
public class JsonVolunteerRepository implements VolunteerRepository {
    
    // Field declarations
    
    // List to hold Volunteer objects loaded from the JSON file.
    private List<Volunteer> volunteers = new ArrayList<>();
    
    // Gson instance for serializing and deserializing JSON data.
    private final Gson gson = new Gson();

    // Path to the JSON file where volunteer data is stored.
    private final String filePath = "src/main/resources/volunteers.json";

    // Logger instance for logging errors and information.
    private static final Logger logger = LoggerFactory.getLogger(JsonVolunteerRepository.class);

    
    // Constructor that initialises the repository by loading volunteers from the JSON file.
    public JsonVolunteerRepository() {
        loadVolunteers();
    }
    

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
            populateInitialVolunteers();
            saveVolunteers(); // Save initial data to the JSON file  
        }
    }

    // Helper method to populate the list with initial volunteer data.
    private void populateInitialVolunteers() {
    // Hardcoded volunteer data for initial setup.
        volunteers.add(new Volunteer(
                UUID.randomUUID(),
                "Elizabeth", 
                "John", 
                "07584986718", 
                "lizzi.john@yahoo.com", 
                "Administrator", 
                Arrays.asList("Organizational", "Attention to detail", "Computer skills"), 
                true 
        ));

        volunteers.add(new Volunteer(
                UUID.randomUUID(),
                "Matthew", 
                "Lazarus",
                "07553698744", 
                "matthewlazarus12@gmail.com", 
                "Accountant", 
                Arrays.asList("Financial analysis", "Integrity", "Taxation knowledge"), 
                false
        ));

        volunteers.add(new Volunteer(
                UUID.randomUUID(),
                "Rachael", 
                "Mark",
                "07956412843", 
                "markrachael2001@live.co.uk", 
                "Greeter", 
                Arrays.asList("Welcoming", "Friendly demeanor", "Effective communication"), 
                true
        ));

            volunteers.add(new Volunteer(
                UUID.randomUUID(),
                "Joshua", 
                "Nun",
                "07383555777", 
                "j.nun@gmail.com", 
                "Choir Member", 
                Arrays.asList("Vocal ability", "Musicality", "Team collaboration"), 
                true
            ));

            volunteers.add(new Volunteer(
                UUID.randomUUID(),
                "Sarah", 
                "Bethel", 
                "07958444888", 
                "bethelsarah@me.com", 
                "Accountant", 
                Arrays.asList("Financial reporting", "Analytical thinking", "Ethics"), 
                true
            ));

        }
    

    // CRUD operations 

    // Saves a new volunteer to the list or updates an existing one, then saves the list to the JSON file.
    @Override
    public Volunteer save(Volunteer volunteer) {
        Optional<Volunteer> existingVolunteer = findById(volunteer.getId());
        if (existingVolunteer.isPresent()) {
            // Update the existing volunteer's fields except for the ID
            int index = volunteers.indexOf(existingVolunteer.get());
            volunteers.set(index, volunteer); // Assumes that volunteer has the same ID
        } else {
            // For a new volunteer, ensure the ID is set (possibly in the constructor before this point)
            volunteers.add(volunteer);
        }
        saveVolunteers();
        return volunteer;
    }

    // Finds a volunteer by their UUID.
    @Override
    public Optional<Volunteer> findById(UUID id) {
            return volunteers.stream()
                             .filter(volunteer -> volunteer.getId().equals(id))
                             .findFirst();
    }

    // Returns a list of all volunteers.
    @Override
    public List<Volunteer> findAll() {
        return volunteers;
    }

    // Removes a volunteer from the list and updates the JSON file.
    @Override
        public void delete(Volunteer volunteer) {
        volunteers.remove(volunteer);
        saveVolunteers();
    }

    // Finds volunteers by a specific skill.
    @Override 
    public List<Volunteer> findBySkills(List<String> skills) { // Change 'skill' to 'skills' in the parameter
    return volunteers.stream()
                     .filter(volunteer -> volunteer.getSkills() != null && !Collections.disjoint(volunteer.getSkills(), skills))
                     .collect(Collectors.toList());
    }


     // Finds volunteers based on their activity status.
    @Override
    public List<Volunteer> findByIsActive(boolean isActive) {
        return volunteers.stream()
                         .filter(volunteer -> volunteer.isActive() == isActive)
                         .collect(Collectors.toList());
    }

}
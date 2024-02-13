package com.cbfacademy.apiassessment.volunteer;

import com.google.gson.Gson;
import org.springframework.stereotype.Repository;

@Repository
public class JsonVolunteerRepository implements VolunteerRepository {
    // Path to the JSON file where volunteer data is stored.
    private final String filePath = "C:/Users/missg/Desktop/CBF/ChurchSystemAPI/src/main/resources/volunteers.json";

    // Gson instance for serializing and deserializing JSON data.
    private Gson gson - new Gson();
}

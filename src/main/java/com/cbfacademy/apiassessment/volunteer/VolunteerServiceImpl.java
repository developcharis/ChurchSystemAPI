package com.cbfacademy.apiassessment.volunteer;

import org.springframework.stereotype.Service;

// Marks this class a Spring-managed service component.
@Service
public class VolunteerServiceImpl implements VolunteerService {

    // Injects the repository dependency injection for data access operations 
    private final VolunteerRepository volunteerRepository;

    // Constructor-based dependency injection for the repository


    /**
     * Constructs a VolunteerServiceImpl with a specified VolunteerRepository.
     *
     * @param volunteerRepository the repository used for volunteer data operations
     */
   
     public VolunteerServiceImpl(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    /**
     * Creates and saves a new volunteer to the repository after validation.
     *
     * @param volunteer the volunteer to be created and saved
     * @return the saved volunteer with persisted data (e.g., generated ID)
     * @throws IllegalArgumentException if any mandatory field of the volunteer is empty
     */
    @Override
    public Volunteer createVolunteer (Volunteer volunteer) {
        validateVolunteer(volunteer);
        return volunteerRepository.save(volunteer);
    }






    
}

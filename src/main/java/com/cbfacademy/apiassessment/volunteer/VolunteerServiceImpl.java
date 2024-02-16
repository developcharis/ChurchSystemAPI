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





    
}

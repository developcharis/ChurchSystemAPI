package com.cbfacademy.apiassessment.volunteer;

import java.util.UUID;

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

    @Override
    public Volunteer updateVolunteer(UUID id, Volunteer volunteer) {
        validateVolunteer(volunteer);
        return volunteerRepository.findById(id)
                .map(existingVolunteer -> {
                    // Update only the fields that are meant to be changed
                    if (volunteer.getFirstName() != null && !volunteer.getFirstName().trim().isEmpty()) {
                        existingVolunteer.setFirstName(volunteer.getFirstName());
                    }
                    if (volunteer.getLastName() != null && !volunteer.getLastName().trim().isEmpty()) {
                        existingVolunteer.setLastName(volunteer.getLastName());
                    }
                    if (volunteer.getContactNumber() != null && !volunteer.getContactNumber().trim().isEmpty()) {
                        existingVolunteer.setContactNumber(volunteer.getContactNumber());
                    }
                    if (volunteer.getEmail() != null && !volunteer.getEmail().trim().isEmpty()) {
                        existingVolunteer.setEmail(volunteer.getEmail());
                    }
                    if (volunteer.getRole() != null && !volunteer.getRole().trim().isEmpty()) {
                        existingVolunteer.setRole(volunteer.getRole());
                    }
                    if (volunteer.getSkills() != null && !volunteer.getSkills().isEmpty()) {
                        existingVolunteer.setSkills(volunteer.getSkills());
                    }
                        existingVolunteer.setActive(volunteer.isActive());
                    
                    // Save and return the updated entity
                    return volunteerRepository.save(existingVolunteer);
                })
                .orElseThrow(() -> new VolunteerNotFoundException(id));
}






    
}

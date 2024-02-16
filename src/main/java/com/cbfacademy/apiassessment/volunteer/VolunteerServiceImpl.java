package com.cbfacademy.apiassessment.volunteer;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cbfacademy.apiassessment.exception.VolunteerNotFoundException;

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

    /**
     * Updates an existing volunteer with new information.
     * <p>
     * This method first validates the incoming volunteer information for mandatory fields. It then attempts to find an existing
     * volunteer by the provided UUID. If found, it updates the volunteer's information with non-null and non-empty values from
     * the incoming volunteer object. The method ensures that only specified fields are updated to maintain data integrity.
     * The 'isActive' status is updated directly as it is a boolean field that does not require null checks.
     * </p>
     *
     * @param id The UUID of the volunteer to update.
     * @param volunteer The volunteer object containing updated information.
     * @return The updated volunteer entity, saved to the repository.
     * @throws VolunteerNotFoundException if no volunteer is found with the specified UUID.
     * @throws IllegalArgumentException if any mandatory field of the volunteer is empty as validated by validateVolunteer method.
     */
    @Override
    public Volunteer updateVolunteer(UUID id, Volunteer volunteer) {
        // Validate mandatory fields in the incoming volunteer object.
        validateVolunteer(volunteer);

        return volunteerRepository.findById(id)
                .map(existingVolunteer -> {
                    // Update first name if provided and not empty.
                    if (volunteer.getFirstName() != null && !volunteer.getFirstName().trim().isEmpty()) {
                        existingVolunteer.setFirstName(volunteer.getFirstName());
                    }

                    // Update last name if provided and not empty.
                    if (volunteer.getLastName() != null && !volunteer.getLastName().trim().isEmpty()) {
                        existingVolunteer.setLastName(volunteer.getLastName());
                    }

                    // Update contact number if provided and not empty.
                    if (volunteer.getContactNumber() != null && !volunteer.getContactNumber().trim().isEmpty()) {
                        existingVolunteer.setContactNumber(volunteer.getContactNumber());
                    }

                    // Update email if provided and not empty.
                    if (volunteer.getEmail() != null && !volunteer.getEmail().trim().isEmpty()) {
                        existingVolunteer.setEmail(volunteer.getEmail());
                    }

                    // Update role if provided and not empty.
                    if (volunteer.getRole() != null && !volunteer.getRole().trim().isEmpty()) {
                        existingVolunteer.setRole(volunteer.getRole());
                    }

                    // Update skills list if provided and not empty.
                    if (volunteer.getSkills() != null && !volunteer.getSkills().isEmpty()) {
                        existingVolunteer.setSkills(volunteer.getSkills());
                    }

                    // Update active status. No null check required for boolean fields.
                        existingVolunteer.setActive(volunteer.isActive());
                    
                    // Save and return the updated volunteer entity
                    return volunteerRepository.save(existingVolunteer);
                })
                .orElseThrow(() -> new VolunteerNotFoundException(id));
}

    /**
    * Validates the mandatory fields of a volunteer.
    *
    * @param volunteer the volunteer to validate.
    * @throws IllegalArgumentException if any mandatory field is empty.
    */
    private void validateVolunteer(Volunteer volunteer) {
        if (volunteer.getFirstName() == null || volunteer.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name is required.");
        }
        if (volunteer.getLastName() == null || volunteer.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Last name is required.");
        }
        if (volunteer.getContactNumber() == null || volunteer.getContactNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Contact number is required.");
        }
        if (volunteer.getEmail() == null || volunteer.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required.");
        }
        // Email format validation
        if (!volunteer.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format.");
    }


    /**
     * Retrieves all volunteers from the repository
     *
     * @return a list of all volunteers
     */
    @Override
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }


    /**
     * Fetches a single volunteer by their UUID.
     *
     * @param id the UUID of the volunteer to retrieve
     * @return the found volunteer
     * @throws VolunteerNotFoundException if no volunteer is found with the specified UUID
     */
    @Override
    public Volunteer getVolunteerById(UUID id) {
        return volunteerRepository.findById(id)
                .orElseThrow(() -> new VolunteerNotFoundException(id));
    }


    /**
     * Deletes a volunteer identified by their UUID.
     *
     * @param id the UUID of the volunteer to delete
     * @throws VolunteerNotFoundException if no volunteer is found with the specified UUID
     */
    @Override
    public void deleteVolunteer(UUID id) {
        Volunteer volunteer = getVolunteerById(id); // Ensures volunteer exists before deletion
        volunteerRepository.delete(volunteer);
    }
   
    /**
     * Searches for volunteers based on an advanced query that includes active status, skills, and role.
     * <p>
     * This method retrieves all volunteers and filters them based on the provided criteria in the {@link AdvancedSearchQuery} object.
     * The filtering process considers the volunteer's active status, their skills, and optionally their role if it is specified in the query.
     * </p>
     *
     * @param query the advanced search criteria including active status, skills list, and optionally the role
     * @return a list of volunteers matching the search criteria
     */
    @Override
    public List<Volunteer> searchVolunteers(AdvancedSearchQuery query) {
        List<Volunteer> allVolunteers = volunteerRepository.findAll();
        
        return allVolunteers.stream()
                // Filter by active status
                .filter(volunteer -> volunteer.isActive() == query.isActive())
                // Filter by matching skills, if skills are specified in the query
                .filter(volunteer -> matchesSkills(volunteer, query.getSkills()))
                // Filter by role, if a role is specified in the query
                .filter(volunteer -> query.getRole() == null || volunteer.getRole().equals(query.getRole()))
                .collect(Collectors.toList()); // Collect and return the list of matching volunteers


    }







    
}

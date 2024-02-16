package com.cbfacademy.apiassessment.volunteer;

import java.util.List;
import java.util.UUID;

public interface VolunteerService {
    
    /**
     * Creates a new volunteer in the system.
     * This method is responsible for persisting the volunteer's information.
     *
     * @param volunteer The volunteer object containing all necessary information for creation.
     * @return The created volunteer with any additional data that might have been set during persistence, such as an ID.
     */
    Volunteer createVolunteer(Volunteer volunteer);

    /**
     * Retrieves a list of all volunteers in the system.
     * This method is typically used for administrative purposes or to display a list of volunteers.
     *
     * @return A list of all volunteers.
     */
    List<Volunteer> getAllVolunteers();

    /**
     * Retrieves a volunteer by their unique identifier.
     * This method is essential for operations needing to access or modify a specific volunteer's details.
     *
     * @param id The unique identifier of the volunteer to retrieve.
     * @return The volunteer associated with the given ID, or null if no such volunteer exists.
     */
    Volunteer getVolunteerById(UUID id);


}

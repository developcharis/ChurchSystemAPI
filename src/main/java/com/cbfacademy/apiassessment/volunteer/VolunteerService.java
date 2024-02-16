package com.cbfacademy.apiassessment.volunteer;

public interface VolunteerService {
    
    /**
     * Creates a new volunteer in the system.
     * This method is responsible for persisting the volunteer's information.
     *
     * @param volunteer The volunteer object containing all necessary information for creation.
     * @return The created volunteer with any additional data that might have been set during persistence, such as an ID.
     */
    Volunteer createVolunteer(Volunteer volunteer);

}

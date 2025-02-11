package com.cbfacademy.apiassessment.volunteer;

import org.springframework.web.bind.annotation.RestController;

import com.cbfacademy.apiassessment.search.AdvancedSearchQuery;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Handles HTTP requests related to volunteer management/
 */
@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {
    
    @Autowired
    private VolunteerService volunteerService;

     /**
     * Creates a new volunteer.
     *
     * @param volunteer the volunteer to create
     * @return the created volunteer with a 201 Created status
     */
    @PostMapping
    public ResponseEntity<Volunteer> createVolunteer(@RequestBody Volunteer volunteer) {
        Volunteer savedVolunteer = volunteerService.createVolunteer(volunteer);
        return new ResponseEntity<>(savedVolunteer, HttpStatus.CREATED);
    }
   
    /**
     * Retrieves a list of all volunteers.
     *
     * @return a list of volunteers with a 200 OK status
     */
    @GetMapping
    public ResponseEntity<List<Volunteer>> getAllVolunteers() {
        List<Volunteer> volunteers = volunteerService.getAllVolunteers();
        return new ResponseEntity<>(volunteers, HttpStatus.OK);
    }


    /**
     * Retrieves a volunteer by their ID.
     *
     * @param id the UUID of the volunteer to retrieve
     * @return the requested volunteer with a 200 OK status
     */
    @GetMapping("/api/volunteers/{id}")
    public ResponseEntity<Volunteer> getVolunteerById(@PathVariable UUID id) {
        Volunteer volunteer = volunteerService.getVolunteerById(id);
        return new ResponseEntity<>(volunteer, HttpStatus.OK);
    }
   
    /**
     * Updates an existing volunteer.
     *
     * @param id the UUID of the volunteer to update
     * @param volunteerUpdate the updated volunteer information
     * @return the updated volunteer with a 200 OK status
     */
    @PutMapping("/api/volunteers/{id}")
    public ResponseEntity<Volunteer> updateVolunteer(@PathVariable UUID id, @RequestBody Volunteer volunteerUpdate) {
        Volunteer updatedVolunteer = volunteerService.updateVolunteer(id, volunteerUpdate);
        return new ResponseEntity<>(updatedVolunteer, HttpStatus.OK);
    }


    /**
     * Deletes a volunteer by their ID.
     *
     * @param id the UUID of the volunteer to delete
     * @return a 204 No Content status on successful deletion
     */
    @DeleteMapping("/api/volunteers/{id}")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable UUID id) {
        volunteerService.deleteVolunteer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
    /**
     * Searches for volunteers based on provided criteria such as skills and active status.
     *
     * @param query The search criteria encapsulated in an AdvancedSearchQuery object.
     * @return A list of volunteers that match the search criteria, wrapped in a ResponseEntity.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Volunteer>> searchVolunteers(@ModelAttribute AdvancedSearchQuery query) {
        List<Volunteer> volunteers = volunteerService.searchVolunteers(query);
        return new ResponseEntity<>(volunteers, HttpStatus.OK);
    }


}
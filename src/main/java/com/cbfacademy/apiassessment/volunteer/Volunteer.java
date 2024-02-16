package com.cbfacademy.apiassessment.volunteer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cbfacademy.apiassessment.person.Person;

/**
 * Represents a Volunteer, extending the Person class with additional attributes
 * specific to the volunteer's role and activities within the organization.
 * Each volunteer is assigned a unique identifier and can have a specific role, skills,
 * and activity status.
 */
public class Volunteer extends Person {
   // Unique identifier for each volunteer, automatically generated to ensure uniqueness
    private final UUID id;
   
    // The volunteers role or position within the organisation
    private String role;

    // Descriptive list of skills or expertise the volunteer brings to the organization
    private List<String> skills;
   
    // Indicates whether the volunteer is currently active within the organization
    private boolean isActive;


    /**
     * Constructs a new Volunteer with specified personal and volunteer-specific details.
     * A unique UUID is generated for each new volunteer to ensure distinct identification..
     *
     * @param firstName        The first name of the volunteer.
     * @param lastName         The last name of the volunteer.
     * @param contactNumber    The contact number of the volunteer.
     * @param email            The email address of the volunteer.
     * @param role             The designated role or position of the volunteer within the organization.
     * @param skills           The skills or expertise the volunteer contributes to their role.
     * @param isActive         A flag indicating whether the volunteer is currently active.
     */
    public Volunteer(String firstName, String lastName, String contactNumber, String email, String role, List<String> skills, boolean isActive) {
        super(firstName, lastName, contactNumber, email); // Initialize Person attributes
        this.id = UUID.randomUUID(); // Generate a unique identifier
        this.role = role;
        this.skills = new ArrayList<>(skills); // Initialise with a copy of the provided list
        this.isActive = isActive;
    }

    // Getters and Setters

     /**
     * Returns the unique identifier of the volunteer.
     * The UUID is generated at the time of object creation and remains constant throughout the lifecycle of the volunteer.
     *
     * @return UUID representing the unique identifier of the volunteer.
     */
    public UUID getId() {
        return id;
    }


    // Getter and Setter methods for volunteer-specific attributes

    /**
     * Returns the role or position of the volunteer within the organization.
     *
     * @return the role or position of the volunteer within the organization.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role or position of the volunteer within the organization.
     *
     * @param role A String representing the volunteer's role or position within the organization.
     */
    public void setRole(String role) {
        this.role = role;
    }


    /**
     * Returns the list of skills or expertise the volunteer contributes to their role.
     * 
     * @return A list of skills or expertise the volunteer contributes to their role.
     */
    public List<String> getSkills() {
        return skills;
    }

    /**
     * Sets the list of skills or expertise the volunteer contributes to their role.
     * 
     * @param skills A list representing the skills or expertise the volunteer contributes to their role.
     */
    public void setSkills(List<String> skills) {
        this.skills = new ArrayList<>(skills); // Assign a copy of the provided list
    }


    /**
     * Returns a flag indicating whether the volunteer is currently active.
     *
     * @return A flag indicating whether the volunteer is currently active.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets a flag indicating whether the volunteer is currently active.
     *
     * @param isActive A boolean representing whether the volunteer is currently active
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    // Methods for adding and removing skills

    /**
     * Add a skill to the volunteer's list of skills.
     * 
     * @param skill The skill to be added to the volunteer's list of skills.
     */
    public void addSkill(String skill) {
        this.skills.add(skill);
    }

    /**
     * Removes a skill from the volunteer's lst of skills.
     * 
     * @param skill The skill to be removed from the volunteer's list of skills. 
     */    
    public void removeSkill(String skill) {
        this.skills.remove(skill);
    }
    
}

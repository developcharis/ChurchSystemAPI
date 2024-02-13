package com.cbfacademy.apiassessment.volunteer;

import java.time.LocalDate;
import java.util.UUID;

import com.cbfacademy.apiassessment.person.Person;

/**
 * Represents a Volunteer, extending the Person class with additional attributes
 * specific to the volunteer's role and activities within the organization.
 * Each volunteer is assigned a unique identifier and can have a specific role, skills,
 * activity status, and a record of their joining date.
 */
public class Volunteer extends Person {
   // Unique identifier for each volunteer, automatically generated to ensure uniqueness
    private final UUID id;
   
    // The volunteers role or position within the organisation
    private String role;

    // Descriptive list of skills or expertise the volunteer brings to the organization
    private String skills;
   
    // Indicates whether the volunteer is currently active within the organization
    private boolean isActive;

    // Records the date when the volunteer first joined the organization
    private LocalDate dateJoined; 
}

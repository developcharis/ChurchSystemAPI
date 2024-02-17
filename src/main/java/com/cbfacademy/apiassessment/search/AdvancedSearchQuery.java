package com.cbfacademy.apiassessment.search;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;


/**
 * Represents the criteria for performing advanced searches on volunteers.
 * This class encapsulates the search parameters such as skills, active status, and role.
 */
public class AdvancedSearchQuery {
    private List<String> skills; // List of skills to match against volunteers
    private boolean isActive; // Flag to indicate if only active volunteers should be considered
    private String role; // The role to match against volunteers


    public AdvancedSearchQuery() {
        //Default constructor
    }
    
    /**
     * Constructs an AdvancedSearchQuery with specified skills, active status, and role.
     *
     * @param skills    List of skills volunteers must possess to match the query
     * @param isActive  Flag indicating whether to search for only active volunteers
     * @param role      The role volunteers must have to match the query
     */
    @JsonCreator
    public AdvancedSearchQuery(List<String> skills, boolean isActive, String role) {
        this.skills = skills;
        this.isActive = isActive;
        this.role = role;
    
}

    /**
     * Constructs an AdvancedSearchQuery with specified skills and active status.
     * This constructor is maintained for backward compatibility and does not include role-based searching.
     *
     * @param skills   List of skills volunteers must possess to match the query
     * @param isActive Flag indicating whether to search for only active volunteers
     */
    public AdvancedSearchQuery (List<String> skills, boolean isActive) {
        this(skills, isActive, null); // Call the main constructor with role as null


    }

    // Getters and Setters
    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
package com.cbfacademy.apiassessment.search;

import java.util.List;

public class AdvancedSearchQuery {
    private List<String> skills;
    private boolean isActive;
    private String role;


    // Constructors
    public AdvancedSearchQuery(List<String> skills, boolean isActive, String role) {
        this.skills = skills;
        this.isActive = isActive;
        this.role = role;
    
}

    // Additional contructor to maintain compatibility
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
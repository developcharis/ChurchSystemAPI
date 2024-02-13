package com.cbfacademy.apiassessment.person;

import java.time.LocalDate;

/**
 * Represents a person with basic personal information. This class serves as a base
 * to encapsulate common attributes that can be shared with other entities in the system.
 * Attributes include first name, last name, date of birth, contact number, and email address.
 */
public class Person {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String contactNumber;
    private String email;

/**
 * Constructs a new Person object with the specified details.
 *
 * @param firstName     The first name of the person, not null.
 * @param lastName      The last name of the person, not null.
 * @param dateOfBirth   The date of birth of the person , not null.
 * @param contactNumber The contact number of the person..
 * @param email         The email address of the person.
 */
public Person(String firstName, String lastName, LocalDate dateOfBirth, String contactNumber, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.contactNumber = contactNumber;
    this.email = email;    
}

}

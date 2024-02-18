package com.cbfacademy.apiassessment.volunteer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cbfacademy.apiassessment.exception.VolunteerNotFoundException;
import com.cbfacademy.apiassessment.search.AdvancedSearchQuery;


/**
 * Unit test class for VolunteerServiceImpl.
 * Utilizes Mockito for mocking dependencies and injecting mock objects,
 * ensuring that the tests are isolated and focus solely on the behavior of VolunteerServiceImpl.
 */
public class VolunteerServiceImplTest {
    
    // Mock declaration for VolunteerRepository to simulate its behavior without relying on its actual implementation.
    @Mock
    private VolunteerRepository volunteerRepository;

    // AutoCloseable instance used to manage the lifecycle of the Mockito annotations.
    private AutoCloseable closeable;
    
    // Object under test, with mock dependencies injected.
    @InjectMocks
    private VolunteerServiceImpl volunteerService;

    /**
     * Sets up the testing environment before each test.
     * Initializes Mockito annotations to enable mock functionality and injects the mocks into the VolunteerServiceImpl.
     */
    @BeforeEach
    void setUp() {
        // Initializes the mocks and prepares the test environment.
        closeable = MockitoAnnotations.openMocks(this);
         // Re-initialize the service to ensure it uses the latest mocked dependencies.
        volunteerService = new VolunteerServiceImpl(volunteerRepository);
    }

    /**
     * Tears down the testing environment after each test.
     * Ensures that resources allocated during the setup are properly released to avoid memory leaks.
     * 
     * @throws Exception if the close operation on the AutoCloseable resource fails.
     */
    @AfterEach
    void tearDown() throws Exception {
        // Close the resources initialized during setup to clean up the test environment.
        closeable.close();
    }



    @Test
    public void testCreateVolunteer_Success() {
        // Initialize a volunteer object with default null values and then set its properties.
        // This simulates the data a user might input when creating a new volunteer.
        Volunteer volunteer = new Volunteer(UUID.randomUUID(), "David", "Judah", "07777777333", "judahdavid@gmail.com", "Bookkeeper", Arrays.asList ("Numerical skills", "Organisational skills", "Accuracy"), true);
        volunteer.setFirstName("David");
        volunteer.setLastName("Judah");
        volunteer.setEmail("judahdavid@gmail.com");
        volunteer.setContactNumber("07777777333");

        // Mock the behavior of the volunteer repository to return the volunteer object when save is called.
        // This avoids actual database operations, ensuring the test remains fast and isolated.
        when(volunteerRepository.save(any(Volunteer.class))).thenReturn(volunteer);

        // Call the createVolunteer method with the mocked volunteer object.
        // This is the actual operation being tested, ensuring it processes the volunteer creation as expected.
        Volunteer created = volunteerService.createVolunteer(volunteer);

        // Assert that the created volunteer object is not null, ensuring the createVolunteer method's successful execution.
        assertNotNull(created);

        // Further assert that the first name of the created volunteer matches the expected value,
        // verifying the integrity and correctness of the data processed by the createVolunteer method.
        assertEquals("David", created.getFirstName());

        // Verify that the save method on the repository was called exactly once,
        // confirming that the service method interacts with the repository as intended.
        verify(volunteerRepository, times(1)).save(volunteer);
    }

    /**
     * Tests the behavior of the createVolunteer method when mandatory fields are missing in the Volunteer object.
     * This test ensures that an IllegalArgumentException is thrown, signaling a violation of data integrity rules
     * due to missing required volunteer information, such as the first name in this scenario. field.
     */
    @Test
    public void testCreateVolunteer_MissingMandatoryFields() {
        // Initialize a volunteer object with only an email, intentionally omitting the first name
        // to simulate a scenario where a mandatory field is missing.
        Volunteer volunteer = new Volunteer(UUID.randomUUID(), null, "David", "07777777333", "judahdavid@gmail.com", "Bookkeeper", Arrays.asList ("Numerical skills", "Organisational skills", "Accuracy"), true);
        volunteer.setEmail("john.doe@example.com"); // Email set, but first name (a mandatory field) is missing

        // Use assertThrows to verify that the expected exception is thrown due to missing mandatory fields.
        assertThrows(IllegalArgumentException.class, () -> {
            // Attempt to create a volunteer with missing mandatory fields.
            // This operation is expected to throw an IllegalArgumentException due to the incomplete data.
            volunteerService.createVolunteer(volunteer);
        });
    }

    @Test
    public void testUpdateVolunteer_Success() {
        // Arange: Set up the testing environment with an existing volunteer and new update information
        UUID id = UUID.randomUUID(); // Unique ID for the existing volunteer
        // Create an existing volunteer with a unique UUID and predefined attributes
        Volunteer existingVolunteer = new Volunteer(UUID.randomUUID(),"David", "Judah", "07777777333", "judahdavid@gmail.com", "Bookkeeper", Arrays.asList ("Numerical skills", "Organisational skills", "Accuracy"), true);

        // Define update information with a new first name but keeping other attributes constant
        Volunteer updateInfo = new Volunteer(UUID.randomUUID(),"Solomon", "Judah", "07777777333", "judahdavid@gmail.com", "Bookkeeper", Arrays.asList ("Numerical skills", "Organisational skills", "Accuracy"), true);

        // Mock the findById method to return the existing volunteer when the corresponding ID is queried
        when(volunteerRepository.findById(id)).thenReturn(Optional.of(existingVolunteer));
        // Mock the save method to simulate the database save operation
        when(volunteerRepository.save(any(Volunteer.class))).thenReturn(existingVolunteer);

        // Act: Execute the updateVolunteer method with the update information
        Volunteer updatedVolunteer = volunteerService.updateVolunteer(id, updateInfo);

        // Assert: Validate that the update operation was successful
        assertNotNull(updatedVolunteer, "The updated volunteer should not be null");
        assertEquals("Solomon", updatedVolunteer.getFirstName(), "The first name of the volunteer should be updated to Solomon");
        // Verify that the repository methods were called as expected
        verify(volunteerRepository, times(1)).findById(id);
        verify(volunteerRepository, times(1)).save(existingVolunteer); // Verifies that a Volunteer object was saved exactly once
}

    @Test
    public void testUpdateVolunteer_NotFound() {
        // Arrange: Set up the test with a non-existent volunteer ID and update information
        UUID nonExistentId = UUID.fromString("9ae002a2-3215-43fc-aa9a-2ee8f2883641");
        Volunteer updateInfo = new Volunteer(UUID.randomUUID(), "Solomon", "Judah", "07777777333", "judahdavid@gmail.com", "Bookkeeper", Arrays.asList ("Numerical skills", "Organisational skills", "Accuracy"), true); // Create a new Volunteer object for update information
        updateInfo.setFirstName("Jane"); // Set the first name to simulate an update operation

        // Mock the findById method to return an empty Optional, simulating the absence of the volunteer in the repository
        when(volunteerRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert: Attempt to update a volunteer with the given ID, expecting a VolunteerNotFoundException to be thrown
        assertThrows(VolunteerNotFoundException.class, () -> {
            volunteerService.updateVolunteer(nonExistentId, updateInfo);
        }, "Expected updateVolunteer to throw VolunteerNotFoundException for a non-existent volunteer ID");
    }

    @Test
    public void testGetVolunteerById_Success() {
        // Arrange: Set up a mock volunteer and its corresponding repository response
        UUID id = UUID.randomUUID(); // Generate a unique ID for the mock volunteer
        Volunteer volunteer = new Volunteer(UUID.randomUUID(), "David", "Judah", "07777777333", "judahdavid@gmail.com", "Bookkeeper", Arrays.asList ("Numerical skills", "Organisational skills", "Accuracy"), true); // Initialize the volunteer object

        // Mock the behavior of the repository to return the mock volunteer when findById is called with the specific ID
        when(volunteerRepository.findById(id)).thenReturn(Optional.of(volunteer));

        // Act: Retrieve the volunteer by ID using the service method
        Volunteer foundVolunteer = volunteerService.getVolunteerById(id);

        // Assert: Verify the retrieved volunteer is as expected
        assertNotNull(foundVolunteer, "The found volunteer should not be null");
        assertEquals("David", foundVolunteer.getFirstName(), "The first name of the found volunteer should match the mock");
        verify(volunteerRepository, times(1)).findById(id); // Ensure findById is called exactly once
    }

    @Test
    public void testGetVolunteerById_NotFound() {
        // Arrange: Generate a unique ID to simulate a search for a non-existent volunteer
        UUID id = UUID.randomUUID();

        // Mock the behavior of the repository to return an empty Optional when searching for the volunteer by ID
        when(volunteerRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert: Verify that the getVolunteerById method throws VolunteerNotFoundException when the volunteer is not found
        assertThrows(VolunteerNotFoundException.class, () -> {
            volunteerService.getVolunteerById(id);
        }, "Expected getVolunteerById to throw VolunteerNotFoundException for a non-existent volunteer ID");
    }

    @Test
    public void testDeleteVolunteer_Success(){
        // Arrange: Create a volunteer with a fixed UUID to simulate fetching from the repository
        UUID id = UUID.randomUUID();
        Volunteer volunteer = new Volunteer(UUID.randomUUID(), "David", "Judah", "07777777333", "judahdavid@gmail.com", "Bookkeeper", Arrays.asList ("Numerical skills", "Organisational skills", "Accuracy"), true);
        // Mock the behavior of the repository to return the created volunteer when findById is called with the specific ID
        when(volunteerRepository.findById(id)).thenReturn(Optional.of(volunteer));
        doNothing().when(volunteerRepository).delete(volunteer);

        // Act: Delete the volunteer by ID using the service method
        volunteerService.deleteVolunteer(id);

        // Assert: Verify that the repository methods are called as expected
        verify(volunteerRepository, times(1)).findById(id);
        verify(volunteerRepository, times(1)).delete(volunteer);

    }

    @Test
    public void testDeleteVolunteer_NotFound() {
        // Arrange: Generate a unique ID to simulate a search for a non-existent volunteer
        UUID id = UUID.randomUUID();

        // Mock the behavior of the repository to return an empty Optional when searching for the volunteer by ID
        when(volunteerRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert: Verify that the deleteVolunteer method throws VolunteerNotFoundException when the volunteer is not found
        assertThrows(VolunteerNotFoundException.class, () -> {
            volunteerService.deleteVolunteer(id);
        }, "Expected deleteVolunteer to throw VolunteerNotFoundException for a non-existent volunteer ID");
    }

    @Test
    public void testGetAllVolunteers() {
        // Arrange: Create a list of volunteers with fixed UUIDs
        Volunteer volunteer1 = new Volunteer(UUID.randomUUID(), "David", "Judah", "07777777333", "judahdavid@gmail.com", "Bookkeeper", Arrays.asList ("Numerical skills", "Organisational skills", "Accuracy"), true);
        Volunteer volunteer2 = new Volunteer(UUID.randomUUID(), "Luke", "Branch", "07756888373", "lukeb@aol.com", "Greeter", Arrays.asList ("Sign Language Proficiency", "Customer Service", "Welcoming"), false);
        List<Volunteer> volunteers = Arrays.asList(volunteer1, volunteer2);

        // Mock the findAll method to return the list of volunteers
        when(volunteerRepository.findAll()).thenReturn(volunteers);

        // Act: Retrieve all volunteers
        List<Volunteer> retrievedVolunteers = volunteerService.getAllVolunteers();

        // Assert: Verify the retrieval and contents of the volunteer list
        assertNotNull(retrievedVolunteers, "Retrieved volunteers list should not be null.");
        assertEquals(2, retrievedVolunteers.size(), "Retrieved volunteers list should contain 2 volunteers.");
        verify(volunteerRepository, times(1)).findAll();
    }

    @Test
    public void testSearchVolunteers_MatchingCriteria() {
        // Arrange: Set up the search query and mock volunteers
        AdvancedSearchQuery query = new AdvancedSearchQuery(Arrays.asList ("Numerical skills", "Organisational skills", "Accuracy"), true, "Bookkeeper");

        Volunteer volunteer1 = new Volunteer(UUID.randomUUID(), "David", "Judah", "07777777333", "judahdavid@gmail.com", "Bookkeeper", Arrays.asList ("Numerical skills", "Organisational skills", "Accuracy"), true);
        Volunteer volunteer2 = new Volunteer(UUID.randomUUID(), "Luke", "Branch", "07756888373", "lukeb@aol.com", "Greeter", Arrays.asList ("Sign Language Proficiency", "Customer Service", "Welcoming"), false);

        when(volunteerRepository.findAll()).thenReturn(Arrays.asList(volunteer1, volunteer2));

        // Act: Search for volunteers matching the query criteria
        List<Volunteer> matchingVolunteers = volunteerService.searchVolunteers(query);

        // Assert: Verify the matching volunteers' details
        assertNotNull(matchingVolunteers, "The list of matching volunteers should not be null.");
        assertEquals(1, matchingVolunteers.size(), "There should be 1 volunteer matching the search criteria.");
        assertEquals("Bookkeeper", matchingVolunteers.get(0).getRole(), "The role of the matching volunteer should be 'Role1'.");
        verify(volunteerRepository, times(1)).findAll();
    }


}



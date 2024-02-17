package com.cbfacademy.apiassessment.volunteer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


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


}


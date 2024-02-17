package com.cbfacademy.apiassessment.volunteer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class VolunteerServiceImplTest {
    
    @Mock
    private VolunteerRepository volunteerRepository;

    private AutoCloseable closeable;
    
    @InjectMocks
    private VolunteerServiceImpl volunteerService;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        volunteerService = new VolunteerServiceImpl(volunteerRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }


}


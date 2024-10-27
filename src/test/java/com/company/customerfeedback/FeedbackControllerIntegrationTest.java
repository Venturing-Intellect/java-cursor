package com.company.customerfeedback;

import com.company.customerfeedback.domain.Feedback;
import com.company.customerfeedback.infrastructure.persistence.FeedbackRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
class FeedbackControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Test
    void submitFeedback_shouldSaveFeedbackAndReturnOk() throws Exception {
        Feedback feedback = new Feedback();
        feedback.setName("John Doe");
        feedback.setEmail("test@example.com");
        feedback.setFeedbackText("Great service!");

        mockMvc.perform(post("/api/feedback")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(feedback)))
                .andExpect(status().isOk());

        // Verify that the feedback was saved to the database
        Feedback savedFeedback = feedbackRepository.findByEmail("test@example.com");
        assertNotNull(savedFeedback);
        assertEquals("John Doe", savedFeedback.getName());
        assertEquals("Great service!", savedFeedback.getFeedbackText());
    }
}

package com.company.customerfeedback;

import com.company.customerfeedback.application.FeedbackService;
import com.company.customerfeedback.domain.Feedback;
import com.company.customerfeedback.ports.out.SaveFeedbackPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class FeedbackServiceTest {

    @Mock
    private SaveFeedbackPort saveFeedbackPort;

    private FeedbackService feedbackService;

    private Validator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        feedbackService = new FeedbackService(saveFeedbackPort);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void submitFeedback_shouldSaveFeedback() {
        Feedback feedback = new Feedback();
        feedback.setEmail("test@example.com");
        feedback.setFeedbackText("Great service!");

        feedbackService.submitFeedback(feedback);

        verify(saveFeedbackPort).save(feedback);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "test@example.com",
        "user.name+tag@example.com",
        "user-name@example.co.uk",
        "1234567890@example.com",
        "email@example-one.com",
        "_______@example.com",
        "email@example.web"
    })
    void testValidEmails(String email) {
        Feedback feedback = new Feedback();
        feedback.setEmail(email);
        feedback.setFeedbackText("Test feedback");

        var violations = validator.validate(feedback);
        assertEquals(0, violations.size(), "Email should be valid: " + email);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "plainaddress",
        "#@%^%#$@#$@#.com",
        "@example.com",
        "Joe Smith <email@example.com>",
        "email.example.com",
        "email@example@example.com",
        ".email@example.com",
        "email.@example.com",
        "email..email@example.com",
        "email@example.com (Joe Smith)",
        "email@example",
        "email@-example.com",
        "email@111.222.333.44444",
        "email@example..com",
        "Abc..123@example.com"
    })
    void testInvalidEmails(String email) {
        Feedback feedback = new Feedback();
        feedback.setEmail(email);
        feedback.setFeedbackText("Test feedback");

        var violations = validator.validate(feedback);
        assertEquals(1, violations.size(), "Email should be invalid: " + email);
    }
}

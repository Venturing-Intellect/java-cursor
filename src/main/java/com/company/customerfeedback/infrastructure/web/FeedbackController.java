package com.company.customerfeedback.infrastructure.web;

import com.company.customerfeedback.domain.Feedback;
import com.company.customerfeedback.ports.in.SubmitFeedbackUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final SubmitFeedbackUseCase submitFeedbackUseCase;

    public FeedbackController(SubmitFeedbackUseCase submitFeedbackUseCase) {
        this.submitFeedbackUseCase = submitFeedbackUseCase;
    }

    @PostMapping
    public ResponseEntity<String> submitFeedback(@Valid @RequestBody Feedback feedback) {
        submitFeedbackUseCase.submitFeedback(feedback);
        return ResponseEntity.ok("Feedback submitted successfully");
    }
}

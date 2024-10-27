package com.company.customerfeedback.infrastructure.persistence;

import com.company.customerfeedback.domain.Feedback;
import com.company.customerfeedback.ports.out.SaveFeedbackPort;
import org.springframework.stereotype.Component;

@Component
public class FeedbackPersistenceAdapter implements SaveFeedbackPort {

    private final FeedbackRepository feedbackRepository;

    public FeedbackPersistenceAdapter(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public void save(Feedback feedback) {
        feedbackRepository.save(feedback);
    }
}

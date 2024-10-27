package com.company.customerfeedback.application;

import com.company.customerfeedback.domain.Feedback;
import com.company.customerfeedback.ports.in.SubmitFeedbackUseCase;
import com.company.customerfeedback.ports.out.SaveFeedbackPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedbackService implements SubmitFeedbackUseCase {

    private final SaveFeedbackPort saveFeedbackPort;

    public FeedbackService(SaveFeedbackPort saveFeedbackPort) {
        this.saveFeedbackPort = saveFeedbackPort;
    }

    @Override
    @Transactional
    public void submitFeedback(Feedback feedback) {
        saveFeedbackPort.save(feedback);
    }
}

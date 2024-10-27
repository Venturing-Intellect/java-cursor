package com.company.customerfeedback.ports.in;

import com.company.customerfeedback.domain.Feedback;

public interface SubmitFeedbackUseCase {
    void submitFeedback(Feedback feedback);
}

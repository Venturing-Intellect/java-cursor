package com.company.customerfeedback.ports.out;

import com.company.customerfeedback.domain.Feedback;

public interface SaveFeedbackPort {
    void save(Feedback feedback);
}

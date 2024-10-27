package com.company.customerfeedback.infrastructure.persistence;

import com.company.customerfeedback.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Feedback findByEmail(String email);
}

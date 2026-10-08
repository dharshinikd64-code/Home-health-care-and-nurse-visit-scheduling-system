package com.homehealthcare.service;

import com.homehealthcare.entity.Feedback;
import com.homehealthcare.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
    }

    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Feedback updateFeedback(Long id, Feedback feedbackDetails) {
        Feedback feedback = getFeedbackById(id);

        feedback.setAppointmentId(feedbackDetails.getAppointmentId());
        feedback.setPatientId(feedbackDetails.getPatientId());
        feedback.setRating(feedbackDetails.getRating());
        feedback.setComments(feedbackDetails.getComments());
        feedback.setCreatedAt(feedbackDetails.getCreatedAt());

        return feedbackRepository.save(feedback);
    }

    public void deleteFeedback(Long id) {
        Feedback feedback = getFeedbackById(id);
        feedbackRepository.delete(feedback);
    }
}

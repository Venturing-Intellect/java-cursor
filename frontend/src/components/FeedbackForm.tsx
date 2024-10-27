import React, { useState } from 'react';
import { submitFeedback } from '../services/feedbackService';

const FeedbackForm: React.FC = () => {
  const [email, setEmail] = useState('');
  const [feedbackText, setFeedbackText] = useState('');
  const [message, setMessage] = useState('');
  const [isError, setIsError] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await submitFeedback({ email, feedbackText });
      setMessage('Feedback submitted successfully!');
      setIsError(false);
      setEmail('');
      setFeedbackText('');
    } catch (error) {
      setMessage('Error submitting feedback. Please try again.');
      setIsError(true);
    }
  };

  return (
    <div className="container mt-5">
      <form onSubmit={handleSubmit} className="needs-validation" noValidate>
        <div className="mb-3">
          <label htmlFor="email" className="form-label">Email address</label>
          <input
            type="email"
            className="form-control"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <div className="invalid-feedback">
            Please provide a valid email address.
          </div>
        </div>
        <div className="mb-3">
          <label htmlFor="feedbackText" className="form-label">Feedback</label>
          <textarea
            className="form-control"
            id="feedbackText"
            rows={3}
            value={feedbackText}
            onChange={(e) => setFeedbackText(e.target.value)}
            required
          ></textarea>
          <div className="invalid-feedback">
            Please provide your feedback.
          </div>
        </div>
        <button type="submit" className="btn btn-primary">Submit Feedback</button>
      </form>
      {message && (
        <div className={`alert ${isError ? 'alert-danger' : 'alert-success'} mt-3`} role="alert">
          {message}
        </div>
      )}
    </div>
  );
};

export default FeedbackForm;

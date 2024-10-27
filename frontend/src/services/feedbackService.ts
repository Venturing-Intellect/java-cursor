import axios from 'axios';

interface Feedback {
  name: string;
  email: string;
  feedbackText: string;
}

export const submitFeedback = async (feedback: Feedback): Promise<void> => {
  await axios.post('http://localhost:8080/api/feedback', feedback);
};

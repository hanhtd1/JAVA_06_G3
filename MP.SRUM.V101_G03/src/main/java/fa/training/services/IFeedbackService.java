package fa.training.services;

import fa.training.models.Feedback;

public interface IFeedbackService {

	public Feedback getAllFeedback(int userId, int subjectId);
	
}

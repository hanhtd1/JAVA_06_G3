package fa.training.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.models.Feedback;
import fa.training.repositories.FeedbackRepository;
import fa.training.services.IFeedbackService;

@Service
public class FeedbackServiceImpl implements IFeedbackService{

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Override
	public Feedback getAllFeedback(int userId, int subjectId) {
		return feedbackRepository.findByUserIdAndSubjectId(userId, subjectId);
	}

}

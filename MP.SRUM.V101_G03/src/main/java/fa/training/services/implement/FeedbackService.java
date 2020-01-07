package fa.training.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.models.Feedback;
import fa.training.repositories.FeedbackRepository;
import fa.training.services.IFeedbackService;

@Service
public class FeedbackService implements IFeedbackService{

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	/**
	 * @author HoangLV7
	 * 
	 */
	@Override
	public Feedback getAllFeedback(int userId, int subjectId) {
		return feedbackRepository.findByUserIdAndSubjectId(userId, subjectId);
	}

	/**
	 * @author HoangLV7
	 * 
	 */
	@Override
	public Feedback save(Feedback feedback) {
		return feedbackRepository.save(feedback);
	}

}
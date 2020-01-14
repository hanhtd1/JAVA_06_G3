package fa.training.services.implement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.models.Feedback;
import fa.training.repositories.FeedbackRepository;
import fa.training.services.IFeedbackService;

@Service
public class FeedbackService implements IFeedbackService{
	
	private static final Logger LOGGER = LogManager.getLogger(FeedbackService.class);

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	/**
	 * @author HoangLV7
	 * 
	 */
	@Override
	public Feedback getAllFeedback(int userId, int subjectId) {
		LOGGER.info("Get feedbacks of UserID " + userId + " and SubjectID " + subjectId);
		return feedbackRepository.findByUserIdAndSubjectId(userId, subjectId);
	}

	/**
	 * @author HoangLV7
	 * 
	 */
	@Override
	public Feedback save(Feedback feedback) {
		LOGGER.info("Save new feedback");
		return feedbackRepository.save(feedback);
	}

}

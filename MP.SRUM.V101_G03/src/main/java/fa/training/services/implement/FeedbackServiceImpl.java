package fa.training.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.models.Feedback;
import fa.training.repositories.FeedbackRepository;
import fa.training.services.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;

	/**
	 * @author HoangLV7
	 * 
	 */
	@Override
	public Feedback getFeedback(int userId, int subjectId) {
		return feedbackRepository.findByUserIdAndSubjectId(userId, subjectId).orElse(new Feedback(subjectId, userId));
	}

	/**
	 * @author HoangLV7
	 * 
	 */
	@Override
	public Feedback save(Feedback feedback) {
		return feedback != null? feedbackRepository.save(feedback) : new Feedback();
	}

	/**
	 * @author ToanNT18
	 */
	@Override
	public List<Feedback> findFeedbackBySubjectIdAndClazzId(Integer subjectId, Integer clazzId) {
		return feedbackRepository.findFeedbackBySubjectAndClazz(subjectId, clazzId);
	}

	/**
	 * @author TrangDM2
	 * @param userId
	 * @param subjectId
	 * @return
	 */
	@Override
	public Feedback findBySubjectAndUser(Integer userId, Integer subjectId) {
		return feedbackRepository.findByUserAndSubject(userId, subjectId);
	}

	@Override
	public List<Feedback> findFeedbackBySubjecId(Integer subjectId) {
		// TODO Auto-generated method stub
		return null;
	}
}

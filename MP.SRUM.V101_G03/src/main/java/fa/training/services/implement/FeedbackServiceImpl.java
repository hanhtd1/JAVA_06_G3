package fa.training.services.implement;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.models.Feedback;
import fa.training.repositories.trainer.FeedbackRepository;
import fa.training.services.FeedbackService;

/**
 *
 * @author ToanNT18
 */
@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	public FeedbackRepository feedbackRepository;

	@Override
	public List<Feedback> findFeedbackBySubjectIdAndClazzId(Integer subjectId, Integer clazzId) {
		return feedbackRepository.findFeedbackBySubjectAndClazz(subjectId, clazzId);
	}

	@Override
	public List<Feedback> findFeedbackByfindBySubjecId(Integer subjectId) {
		return feedbackRepository.findFeedbackBySubjectId(subjectId);
	}
}

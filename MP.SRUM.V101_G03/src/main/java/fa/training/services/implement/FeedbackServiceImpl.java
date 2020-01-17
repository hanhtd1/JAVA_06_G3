package fa.training.services.implement;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.models.Feedback;
import fa.training.repositories.FeedbackRepository;
import fa.training.services.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

  private static final Logger LOGGER = LogManager.getLogger(FeedbackServiceImpl.class);

  @Autowired
  private FeedbackRepository feedbackRepository;

  /**
   * @author HoangLV7
   * 
   */
  @Override
  public Feedback getAllFeedback(int userId, int subjectId) {
    LOGGER.info("Get feedbacks of UserID " + userId + " and SubjectID " + subjectId);
    return feedbackRepository.findByUserIdAndSubjectId(userId, subjectId).orElse(new Feedback(subjectId, userId));
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

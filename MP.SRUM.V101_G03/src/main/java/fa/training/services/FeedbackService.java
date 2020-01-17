package fa.training.services;

import java.util.List;

import fa.training.models.Feedback;

public interface FeedbackService {

	public Feedback getAllFeedback(int userId, int subjectId);

	public Feedback save(Feedback feedback);

	/**
	 * @author ToanNT18
	 * @param subjectId
	 * @param clazzId
	 * @return feedback find by subject.
	 */
	List<Feedback> findFeedbackBySubjectIdAndClazzId(Integer subjectId, Integer clazzId);

	/**
	 * @author ToanNT18
	 * @param subjectId
	 * @param clazzId
	 * @return feedback find by subject.
	 */
	List<Feedback> findFeedbackBySubjecId(Integer subjectId);

  /**
   * @author TrangDM2
   * @param userId
   * @param subjectId
   * @return
   */
  Feedback findBySubjectAndUser(Integer userId, Integer subjectId);

}

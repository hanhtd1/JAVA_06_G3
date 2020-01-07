package fa.training.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.models.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

	/**
	 * @author HoangLV7
	 * 
	 */
	public Feedback findByUserIdAndSubjectId(int userId, int subjectId);

}

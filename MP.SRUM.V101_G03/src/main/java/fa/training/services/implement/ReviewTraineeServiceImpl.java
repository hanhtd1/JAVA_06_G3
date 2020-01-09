package fa.training.services.implement;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.models.ReviewTrainee;
import fa.training.repositories.ReviewTraineeRepository;
import fa.training.services.ReviewTraineeService;

/**
 *
 * @author ToanNT18
 */
@Service
@Transactional
public class ReviewTraineeServiceImpl implements ReviewTraineeService {
	@Autowired
	private ReviewTraineeRepository reviewTraineeRepository;

	@Override
	public ReviewTrainee add(ReviewTrainee reviewTrainee) {
		return reviewTraineeRepository.save(reviewTrainee);
	}
}

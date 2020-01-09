package fa.training.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.models.ReviewTrainee;
import fa.training.repositories.trainer.ReviewTraineeRepository;
import fa.training.service.ReviewTraineeService;

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

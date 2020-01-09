package fa.training.services.implement;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.dto.TraineeScoreDTO;
import fa.training.repositories.trainer.ScoreRepository;
import fa.training.services.ScoreService;

/**
 *
 * @author ToanNT18
 */
@Service
@Transactional
public class ScoreServiceImpl implements ScoreService {
	@Autowired
	private ScoreRepository scoreRepository;

	@Override
	public List<TraineeScoreDTO> findByIdUserId(Integer userId) {
		return scoreRepository.findScoreByUserId(userId);
	}

}

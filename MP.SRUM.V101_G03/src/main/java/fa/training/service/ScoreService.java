package fa.training.service;

import java.util.List;

import fa.training.dto.TraineeScoreDTO;

public interface ScoreService {
	List<TraineeScoreDTO> findByIdUserId(Integer userId);
}

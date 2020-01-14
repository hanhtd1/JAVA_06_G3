package fa.training.services;

import java.util.List;

import fa.training.dto.TraineeScoreDto;

public interface ScoreService {
	List<TraineeScoreDto> findByIdUserId(Integer userId);
}

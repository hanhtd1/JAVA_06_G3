package fa.training.services;

import java.util.List;

import fa.training.dtos.ScoreDto;
import fa.training.dtos.TraineeScoreDto;

public interface ScoreService {
	List<TraineeScoreDto> findByIdUserId(Integer userId);

  List<ScoreDto> getScoreByUser(Integer userId);
}

package fa.training.services;

import java.util.List;

import fa.training.dtos.AdminScoreDto;
import fa.training.dtos.ScoreDto;
import fa.training.dtos.TraineeScoreDto;
import fa.training.models.Score;

public interface ScoreService {
	List<TraineeScoreDto> findByIdUserId(Integer userId);
	
	List<Score> saveScores(List<AdminScoreDto> scoreDtos) throws IllegalArgumentException;

	List<ScoreDto> getScoreByUser(Integer userId);
}

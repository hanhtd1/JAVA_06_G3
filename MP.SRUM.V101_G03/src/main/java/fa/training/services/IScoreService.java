package fa.training.services;

import java.util.List;

import fa.training.dto.ScoreDto;

public interface IScoreService {

	public List<ScoreDto> getScoreByUser(Integer userId);

}

package fa.training.services.implement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.dto.ScoreDto;
import fa.training.models.Score;
import fa.training.repositories.ScoreRepository;
import fa.training.services.IScoreService;

@Service
public class ScoreService implements IScoreService {
	
	private static final Logger LOGGER = LogManager.getLogger(ScoreService.class);

	@Autowired
	private ScoreRepository scoreRepository;

	/**
	 * @author HoangLV7
	 * 
	 */
	@Override
	public List<ScoreDto> getScoreByUser(Integer userId) {
		LOGGER.info("Get score by UserId " + userId);
		List<Score> scores = scoreRepository.findAllScoreByUserId(userId);
		List<ScoreDto> scoreDtos = new ArrayList<ScoreDto>();
		scores.forEach(score -> {
			scoreDtos.add(new ScoreDto(score));
		});
		return scoreDtos;
	}

}

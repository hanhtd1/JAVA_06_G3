package fa.training.services.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.dtos.ScoreDto;
import fa.training.dtos.TraineeScoreDto;
import fa.training.models.Score;
import fa.training.repositories.ScoreRepository;
import fa.training.services.ScoreService;

/**
 *
 * @author ToanNT18
 */
@Service
@Transactional
public class ScoreServiceImpl implements ScoreService {

	private static final Logger LOGGER = LogManager.getLogger(ScoreServiceImpl.class);

	@Autowired
	private ScoreRepository scoreRepository;

	/**
	 * @author TrangDM2
	 */
	@Override
	public List<TraineeScoreDto> findByIdUserId(Integer userId) {
		return scoreRepository.findScoreByUserId(userId);
	}

	/**
	 * @author TrangDM2
	 * @param score
	 * @return
	 */
	@Override
	public Score saveScore(Score score) {
		return scoreRepository.save(score);
	}

//	/**
//	 * @author HoangLV7
//	 * 
//	 */
//	@Override
//	public List<ScoreDto> getScoreByUser(Integer userId) {
//		List<Score> scores = scoreRepository.findAllScoreByUserId(userId);
//		List<ScoreDto> scoreDtos = new ArrayList<ScoreDto>();
//		scores.forEach(score -> {
//			scoreDtos.add(new ScoreDto(score));
//		});
//		return scoreDtos;
//	}
	/**
	 * @author HoangLV7
	 * 
	 */
	@Override
	public List<ScoreDto> getScoreByUser(Integer userId) {
		return userId != null ? scoreRepository.findAllScoreByUserId(userId).stream().map(score -> new ScoreDto(score))
				.collect(Collectors.toList()) : new ArrayList<ScoreDto>();
	}
}

package fa.training.services.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.dtos.AdminScoreDto;
import fa.training.dtos.ScoreDto;
import fa.training.dtos.TraineeScoreDto;
import fa.training.models.Score;
import fa.training.models.ScorePK;
import fa.training.repositories.ScoreRepository;
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
  public List<Score> saveScores(List<AdminScoreDto> scoreDtos) throws IllegalArgumentException {
    List<Score> scores = new ArrayList<>();

    scoreDtos.forEach(scoreDto -> {
      ScorePK scorePk = new ScorePK(scoreDto.getSubjectId(), scoreDto.getUserId());
      Score score = new Score(scorePk, scoreDto.getTheory(), scoreDto.getPractice());

      scores.add(score);
    });

    return scoreRepository.saveAll(scores);
  }

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

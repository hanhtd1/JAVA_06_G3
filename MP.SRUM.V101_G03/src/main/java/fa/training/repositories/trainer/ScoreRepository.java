package fa.training.repositories.trainer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.training.dto.TraineeScoreDTO;
import fa.training.models.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {
	/**
	 * @author ToanNT18
	 * @param userId
	 * @return
	 */
	@Query(value = "SELECT * FROM udf_findScoreByUserId(:userId)", nativeQuery = true)
	List<TraineeScoreDTO> findScoreByUserId(@Param("userId") Integer userId);
}

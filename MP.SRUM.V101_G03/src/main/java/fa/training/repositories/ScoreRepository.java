package fa.training.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.training.models.Score;

/**
 * @author TrangDM2
 *
 */
@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {

	/**
	 * @author TrangDM2
	 * @param id
	 * @return
	 */
	@Query("select s from Score s where s.user.id = :id")
	List<Score> findAllScoreByUserId(@Param("id") Integer id);
	
}

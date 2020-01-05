package fa.training.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.training.models.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {
  @Query("select s from Score s where s.user.id = :id")
  List<Score> findAllScoreByUserId(@Param("id") Integer id);
}

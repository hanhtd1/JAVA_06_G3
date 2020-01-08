package fa.training.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.models.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {

}

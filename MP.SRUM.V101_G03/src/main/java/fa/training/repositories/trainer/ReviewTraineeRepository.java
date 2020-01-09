package fa.training.repositories.trainer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.models.ReviewTrainee;

/**
 *
 * @author ToanNT18
 */
@Repository
public interface ReviewTraineeRepository extends JpaRepository<ReviewTrainee, Integer> {

}

package fa.training.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.training.models.User;

/**
 *
 * @author ToanNT18
 */
@Repository
public interface TraineeRepository extends JpaRepository<User, Integer> {

	/**
	 * @author ToanNT18
	 * @param userId
	 * @return User
	 * @return find User by user id.
	 */
	Optional<User> findById(Integer id);

	/**
	 * @author ToanNT18
	 * @return top 10 trainee.
	 */
	Page<User> findAllByRole(String role, Pageable pageable);

	/**
	 * @author ToanNT18
	 * @param clazzId
	 * @return List<User>
	 * @return all trainee by clazz.
	 */
	@Query(value = "SELECT * FROM udf_udf_findTraineeByClazz(:clazzId)", nativeQuery = true)
	Page<User> findTraineeByClazzId(@Param("clazzId") Integer clazzId, Pageable pageable);

	/**
	 * @author ToanNT18
	 * @param category
	 * @param role     : Trainee
	 * @return All trainee by category
	 */
	@Query(value = "SELECT * FROM udf_findTraineeByCategory(:category, :role)", nativeQuery = true)
	Page<User> findTraineeByCategory(@Param("category") String category, @Param("role") String role, Pageable pageable);
}
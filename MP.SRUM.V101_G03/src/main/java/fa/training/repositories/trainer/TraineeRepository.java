package fa.training.repositories.trainer;

import java.util.List;

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
	@Query(value = "SELECT * FROM uzer u WHERE u.id = :userId", nativeQuery = true)
	List<User> findTraineeByUserId(@Param("userId") Integer userId);
	
	/**
	 * @author ToanNT18
	 * @return top 10 trainee.
	 */
	@Query(value = "SELECT TOP 10 *  FROM uzer u WHERE u.role = :role", nativeQuery = true)
	List<User> findTop10Trainee(@Param("role") String role);

	/**
	 * @author ToanNT18
	 * @param clazzId
	 * @return List<User>
	 * @return all trainee by clazz.
	 */
	@Query(value = "SELECT * FROM [udf_findTraineeByClazz](:clazzId)", nativeQuery = true)
	List<User> findTraineeByClazzId(@Param("clazzId") Integer clazzId, Pageable pageable);
	
	/**
	 * @author ToanNT18
	 * @param category
	 * @param role     : Trainee
	 * @return All trainee by category
	 */
	@Query(value = "SELECT * FROM udf_findTraineeByCategory(:category, :role)", nativeQuery = true)
	List<User> findTraineeByCategory(@Param("category") String category, Pageable pageable);
}
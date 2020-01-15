package fa.training.repositories;

import java.util.List;
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
	 * @return list of trainee.
	 */
	@Query(value = "SELECT DISTINCT u.status FROM uzer u", nativeQuery = true)
	List<String> findAllTraineeStatus();

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
	@Query(value = "SELECT * FROM udf_findTraineeByClazz(:clazzId)", nativeQuery = true)
	Page<User> findTraineeByClazzId(@Param("clazzId") Integer clazzId, Pageable pageable);

	/**
	 * @author ToanNT18
	 * @param category
	 * @param role     : Trainee
	 * @return All trainee by category
	 */
	@Query(value = "SELECT * FROM udf_findTraineeByCategory(:category)", nativeQuery = true)
	Page<User> findTraineeByCategory(@Param("category") String category, Pageable pageable);

	/**
	 * @author ToanNT18
	 * @param clazzId
	 * @return List<User>
	 * @return all trainee by clazz.
	 */
	@Query(value = "SELECT * FROM udf_findTraineeByClazzName(:clazzName)", nativeQuery = true)
	Page<User> findTraineeByClazzName(@Param("clazzName") String clazzName, Pageable pageable);

	@Query(value = "SELECT * FROM udf_findTraineeByClazzId(:clazzId)", nativeQuery = true)
	Page<User> findTraineeByClazzId(@Param("clazzId") int clazzId, Pageable pageable);

	/**
	 * @param status
	 * @param pageable
	 * @return all trainee by status.
	 */
	@Query(value = "SELECT * FROM uzer u WHERE u.status = :status", nativeQuery = true)
	Page<User> findTraineeByStatus(@Param("status") String status, Pageable pageable);

	/**
	 * @param category
	 * @param clazzName
	 * @param pageable
	 * @return
	 */
	@Query(value = "SELECT * FROM udf_findTraineeByCategoryAndClazz(:category, :clazzName)", nativeQuery = true)
	Page<User> findTraineeByCategoryAndClazz(@Param("category") String category, @Param("clazzName") String clazzName,
			Pageable pageable);

	/**
	 * @param category
	 * @param status
	 * @param pageable
	 * @return
	 */
	@Query(value = "SELECT * FROM udf_findTraineeByCategoryAndStatus(:category, :status)", nativeQuery = true)
	Page<User> findTraineeByCategoryAndStatus(@Param("category") String category, @Param("status") String status,
			Pageable pageable);

	@Query(value = "SELECT * FROM udf_findTraineeByClazzAndStatus(:clazz, :status)", nativeQuery = true)
	Page<User> findTraineeByClazzAndStatus(@Param("clazz") String clazz, @Param("status") String status,
			Pageable pageable);

	/**
	 * @param category
	 * @param clazzName
	 * @param status
	 * @param pageable
	 * @return
	 */
	@Query(value = "SELECT * FROM udf_findTraineeByCategoryAndClazzAndStatus(:category, :clazzName, :status)", nativeQuery = true)
	Page<User> findTraineeByCategoryAndClazzAndStatus(@Param("category") String category,
			@Param("clazzName") String clazzName, @Param("status") String status, Pageable pageable);

}
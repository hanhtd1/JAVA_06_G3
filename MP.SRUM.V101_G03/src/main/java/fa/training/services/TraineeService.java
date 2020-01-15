package fa.training.services;

import java.util.List;

import fa.training.models.User;

public interface TraineeService {
	List<String> findAllTraineeStatus();

	/**
	 * @author ToanNT18
	 * @param id
	 * @return trainee by id
	 */
	User findTraineeById(Integer id);

	/**
	 * @author ToanNT18
	 * @return all trainee by top 10
	 */
	List<User> findAllTrainee(int page);

	/**
	 * @author ToanNT18
	 * @param clazzId
	 * @return by all trainee by category
	 */
	List<User> findTraineeByCategory(String category, Integer pageIndex);

	/**
	 * @author ToanNT18
	 * @param clazzId
	 * @return by all trainee who study in the clazz
	 */
	List<User> findTraineeByClazzName(String clazzName, Integer pageIndex);

	List<User> findTraineeByClazzId(int clazzId, Integer pageIndex);

	/**
	 * @author ToanNT18
	 * @param clazzId
	 * @return by all trainee by category
	 */
	List<User> findTraineeByStatus(String status, Integer pageIndex);

	/**
	 * @author ToanNT18
	 * @param clazzId
	 * @return by all trainee by category
	 */
	List<User> findTraineeByCategoryAndClazz(String category, String clazzName, Integer pageIndex);

	/**
	 * @author ToanNT18
	 * @param clazzId
	 * @return by all trainee by category
	 */
	List<User> findTraineeByCategoryAndStatus(String category, String status, Integer pageIndex);

	/**
	 * @author ToanNT18
	 * @param clazzId
	 * @return by all trainee by category
	 */
	List<User> findTraineeByClazzAndStatus(String clazzName, String status, Integer pageIndex);

	/**
	 * @author ToanNT18
	 * @param clazzId
	 * @return by all trainee by category
	 */
	List<User> findTraineeByCategoryAndClazzAndStatus(String category, String clazzName, String status,
			Integer pageIndex);
}

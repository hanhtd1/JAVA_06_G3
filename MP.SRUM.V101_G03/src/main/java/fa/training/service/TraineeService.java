package fa.training.service;

import java.util.List;

import fa.training.models.User;

public interface TraineeService {
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
	List<User> findAllTrainee();

	/**
	 * @author ToanNT18
	 * @param clazzId
	 * @return by all trainee who study in the clazz
	 */
	List<User> findTraineeByClazz(Integer clazzId, Integer pageIndex);

	/**
	 * @author ToanNT18
	 * @param clazzId
	 * @return by all trainee by category
	 */
	List<User> findTraineeByCategory(String category, Integer pageIndex);
}

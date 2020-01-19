package fa.training.services;

import java.util.List;
import java.util.Map;

import fa.training.dtos.BestTraineeDto;
import fa.training.models.User;

/**
 * @author HoangLV7
 *
 */
public interface UserService {
	Map<String, User> findAllByRole(String role);

	List<User> getMembers(User user);

	User getUser(String account);

	User getUserById(int userId);

	List<User> findAll();

	List<BestTraineeDto> findTopThreeBestTrainee();
}

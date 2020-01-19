package fa.training.services;

import java.util.List;

import fa.training.dtos.BestTraineeDto;
import fa.training.models.User;

/**
 * @author HoangLV7
 *
 */
public interface UserService {

	List<User> getMembers(User user);

	User getUser(String account);

	User getUserById(int userId);

	List<User> findAll();

	List<BestTraineeDto> findTopThreeBestTrainee();
}

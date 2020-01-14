package fa.training.services;

import java.util.List;
import java.util.Map;

import fa.training.models.User;

/**
 * @author HoangLV7
 *
 */
public interface IUserService {
	Map<String, User> findAllByRole(String role);

	List<User> getMembers(User user);

	User getUser(String account);

	User getUserById(int userId);
}

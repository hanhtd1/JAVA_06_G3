package fa.training.services.implement;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.models.Clazz;
import fa.training.models.User;
import fa.training.repositories.UserRepository;
import fa.training.services.IUserService;

@Service
public class UserService implements IUserService {
	
	private static final Logger LOGGER = LogManager.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	/**
	 * @author HoangLV7
	 * 
	 */
	@Override
	public List<User> getMembers(User user) {
		LOGGER.info("Get classmate of user " + user.getAccount());
		return userRepository.getMembers(user);
	}

	/**
	 * @author HoangLV7
	 * 
	 */
	@Override
	public User getUser(String account) {
		LOGGER.info("Get User by Account " + account);
		return userRepository.findByAccount(account).get();
	}

	/**
	 * @author HoangLV7
	 */
	@Override
	public User getUserById(int userId) {
		LOGGER.info("Get User by UserID " + userId);
		return userRepository.findUserById(userId);
	}
	
	/**
	 * @author HoangLV7
	 */
	@Override
	public Map<String, User> findAllByRole(String role) {
		
		return null;
	}
}

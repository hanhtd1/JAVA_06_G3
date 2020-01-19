package fa.training.services.implement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.models.User;
import fa.training.repositories.UserRepository;
import fa.training.services.UserService;
import fa.training.utils.Constant;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	/**
	 * @author HoangLV7
	 * 
	 */
	@Override
	public List<User> getMembers(User user) {
		String role = Constant.TRAINEE;
		String status = Constant.TRAINEE_ACTIVE_STATUS;
		return (user != null && user.getId() != null) ? userRepository.getMembers(role, status, user)
				: new ArrayList<User>();
	}

	/**
	 * @author HoangLV7
	 * 
	 */
	@Override
	public User getUser(String account) {
		return userRepository.findByAccount(account).orElse(new User(Constant.DEFAULT_ID));
	}

	/**
	 * @author HoangLV7
	 */
	@Override
	public User getUserById(int userId) {
		return userRepository.findUserById(userId).orElse(new User());
	}
}

package fa.training.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.models.Attendance;
import fa.training.models.User;
import fa.training.repositories.UserRepository;
import fa.training.services.IUserService;

/**
 * @author HoangLV7
 *
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * @author HoangLV7
	 * 
	 */
	@Override
	public List<User> getMembers(User user) {
		return userRepository.getMembers(user);
	}

	/**
	 * @author HoangLV7
	 * 
	 */
	@Override
	public User getUser(String account, String password) {
		return userRepository.findByAccountAndPassword(account, password);
	}

}

package fa.training.services;

import java.util.List;

import fa.training.models.User;

public interface IUserService {
	public List<User> getMembers(User user);

	public User getUser(String account, String password);
}

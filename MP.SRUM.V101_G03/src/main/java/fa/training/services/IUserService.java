package fa.training.services;

import java.util.List;

import fa.training.models.User;

/**
 * @author HoangLV7
 *
 */
public interface IUserService {
  public List<User> getMembers(User user);

  User getUser(String account);

  User getUserById(int userId);
}

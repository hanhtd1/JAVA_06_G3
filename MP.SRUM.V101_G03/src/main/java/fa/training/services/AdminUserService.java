package fa.training.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fa.training.dto.ScoreDto;
import fa.training.dto.UserDto;
import fa.training.models.User;

/**
 * @author TrangDM2
 *
 */
@Service
public interface AdminUserService {

  User getUser(int id);

  List<UserDto> getUsersByRole(String role);

  boolean saveUser(User user);

  List<User> getAllUsers();

  List<ScoreDto> getScore(Integer id);

}

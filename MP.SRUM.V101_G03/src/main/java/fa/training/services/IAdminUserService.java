package fa.training.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fa.training.dto.ScoreDto;
import fa.training.dto.UserDto;
import fa.training.models.User;

/**
 * @author TrangDM2
 *
 */
@Service
public interface IAdminUserService {

  User getUser(int id);

  List<UserDto> getUsersByRole(String role);

  boolean saveUser(User user);

  List<User> getAllUsers();

  List<ScoreDto> getScore(Integer id);

  String generateAccount(String firstName, String lastName);

  Optional<User> getUserByAccount(String account);

  List<UserDto> findUserByKeyword(String keyword, String role, String status);

  void updateUserStatus(int id, String status);

}

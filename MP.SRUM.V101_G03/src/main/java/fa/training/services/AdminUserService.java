package fa.training.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fa.training.dtos.ScoreDto;
import fa.training.dtos.UserDto;
import fa.training.models.User;

/**
 * @author TrangDM2
 *
 */
@Service
public interface AdminUserService {

  User getUser(int id);

  List<UserDto> getUsersByRole(String role);

  User saveUser(User user);

  List<User> getAllUsers();

  List<ScoreDto> getScore(Integer id);

  String generateAccount(String firstName, StringBuilder lastName);

  Optional<User> getUserByAccount(String account);

  List<UserDto> findUserByKeyword(String keyword, String role, String status);

  void updateUserStatus(int id, String status);

  List<User> getUserByClazzAndRole(Integer clazzId, String role);

}

package fa.training.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fa.training.dto.UserDto;
import fa.training.models.User;

@Service
public interface AdminUserService {

  User getUser(int id);

  List<UserDto> getUsersByRole(String role);

  List<UserDto> saveUser(User user, String role);

  List<User> getAllUsers();

}

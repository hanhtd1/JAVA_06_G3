package fa.training.services.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.dto.UserDto;
import fa.training.models.Clazz;
import fa.training.models.User;
import fa.training.repositories.UserRepository;
import fa.training.services.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public User getUser(int id) {
    return userRepository.findById(id).get();
  }

  @Override
  public List<UserDto> getUsersByRole(String role) {
    List<User> users = userRepository.getAllUserByRole(role);
    List<UserDto> userDtos = new ArrayList<>();
    users.forEach(user->{
      Clazz clazz = user.getClazzList().get(0);
      userDtos.add(new UserDto(user, clazz));
    });
    return userDtos;
  }

  @Override
  public List<UserDto> saveUser(User user, String role) {
    // Validate
    userRepository.save(user);
    return getUsersByRole(role);
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
}

package fa.training.services.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.dto.ScoreDto;
import fa.training.dto.UserDto;
import fa.training.models.Clazz;
import fa.training.models.Score;
import fa.training.models.User;
import fa.training.repositories.ScoreRepository;
import fa.training.repositories.UserRepository;
import fa.training.services.AdminUserService;

/**
 * @author TrangDM2
 *
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
  @Autowired
  private UserRepository userRepository;
 @Autowired
 private ScoreRepository scoreRepository;
  @Override
  public User getUser(int id) {
    return userRepository.findById(id).get();
  }

  @Override
  public List<UserDto> getUsersByRole(String role) {
    List<User> users = userRepository.getAllUserByRole(role);
    List<UserDto> userDtos = new ArrayList<>();
    users.forEach(user->{
      Clazz clazz = null;
      List<Clazz> clazzs = user.getClazzList();
      if(!clazzs.isEmpty()) {
        clazz = clazzs.get(0);
      }
      userDtos.add(new UserDto(user, clazz));
    });
    return userDtos;
  }

  @Override
  public boolean saveUser(User user) {
    // Validate
    userRepository.save(user);
    return true;
  }
  
  @Override
  public List<ScoreDto> getScore(Integer id){
    List<Score> scores = scoreRepository.findAllScoreByUserId(id);
    List<ScoreDto> scoresDto = new ArrayList<>();
    scores.forEach(x->{
      scoresDto.add(new ScoreDto(x));
    });
    return scoresDto;
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
}

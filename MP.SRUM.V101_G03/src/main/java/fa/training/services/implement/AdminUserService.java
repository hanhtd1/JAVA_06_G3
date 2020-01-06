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
import fa.training.services.IAdminUserService;

/**
 * @author TrangDM2
 *
 */
@Service
public class AdminUserService implements IAdminUserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ScoreRepository scoreRepository;

  /**
   *@author TrangDM2
   */
  @Override
  public User getUser(int id) {
    return userRepository.findById(id).get();
  }

  /**
   *@author TrangDM2
   */
  @Override
  public User getUserByAccount(String account) {
    return userRepository.findByAccount(account);
  }

  /**
   *@author TrangDM2
   */
  @Override
  public List<UserDto> getUsersByRole(String role) {
    List<User> users = userRepository.getAllUserByRole(role);
    List<UserDto> userDtos = new ArrayList<>();
    users.forEach(user -> {
      Clazz clazz = null;
      List<Clazz> clazzs = user.getClazzList();
      if (!clazzs.isEmpty()) {
        clazz = clazzs.get(0);
      }
      userDtos.add(new UserDto(user, clazz));
    });
    return userDtos;
  }

  /**
   *@author TrangDM2
   */
  @Override
  public boolean saveUser(User user) {
    //TODO Validate
    userRepository.save(user);
    return true;
  }

  /**
   *@author TrangDM2
   */
  @Override
  public List<ScoreDto> getScore(Integer id) {
    List<Score> scores = scoreRepository.findAllScoreByUserId(id);
    List<ScoreDto> scoresDto = new ArrayList<>();
    scores.forEach(x -> {
      scoresDto.add(new ScoreDto(x));
    });
    return scoresDto;
  }

  /**
   *@author TrangDM2
   */
  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  /**
   *@author TrangDM2
   */
  @Override
  public List<UserDto> findUserByKeyword(String keyword, String role, String status){
    List<User> users = userRepository.findUsersByKeyword("%"+keyword+"%",role, "%"+status+"%");
    List<UserDto> userDtos = new ArrayList<>();
    users.forEach(user -> {
      Clazz clazz = null;
      List<Clazz> clazzs = user.getClazzList();
      if (!clazzs.isEmpty()) {
        clazz = clazzs.get(0);
      }
      userDtos.add(new UserDto(user, clazz));
    });
    return userDtos;
  }

  /**
   *@author TrangDM2
   */
  @Override
  public void updateUserStatus(int id, String status) {
    userRepository.updateUserStatus(status, id);
  }

  /**
   *@author TrangDM2
   */
  @Override
  public String generateAccount(String firstName, String lastName) {
    String account = lastName;
    for (int i = 0; i < firstName.length(); i++) {
      char c = firstName.charAt(i);
      if ((int)c <= 90 && (int)c >64) {
        account += c;
      }
    }
    int number = userRepository.getNumberOfAccount(account + "%");;
    if(number==0) {
      account += "";
    } else account += String.valueOf(number);
    return account;
  }
}

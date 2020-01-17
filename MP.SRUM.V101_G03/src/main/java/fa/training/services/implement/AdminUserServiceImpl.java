package fa.training.services.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fa.training.dtos.ScoreDto;
import fa.training.dtos.UserDto;
import fa.training.models.Clazz;
import fa.training.models.Score;
import fa.training.models.User;
import fa.training.repositories.ScoreRepository;
import fa.training.repositories.UserRepository;
import fa.training.services.AdminUserService;
import fa.training.utils.Constant;

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

  /**
   *@author TrangDM2
   */
  @Override
  public User getUser(int id) {
    return userRepository.findById(id).orElseThrow(()->{
      throw new UsernameNotFoundException(Constant.NOT_FOUND_MESSAGE);
    });
  }

  /**
   *@author TrangDM2
   */
  @Override
  public Optional<User> getUserByAccount(String account) {
    return userRepository.findByAccount(account);
  }

  /**
   *@author TrangDM2
   */
  @Override
  public List<UserDto> getUsersByRole(String role) {
    List<User> users = userRepository.findAllByRole(role);
    List<UserDto> userDtos = new ArrayList<>();
    users.forEach(user -> {
      Clazz clazz = null;
      Set<Clazz> clazzs = user.getClazzList();
      if (!clazzs.isEmpty()) {
        clazz = clazzs.iterator().next();
      }
      userDtos.add(new UserDto(user, clazz));
    });
    return userDtos;
  }

  /**
   *@author TrangDM2
   */
  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  /**
   *@author TrangDM2
   */
  @Override
  public List<ScoreDto> getScore(Integer id) {
    List<Score> scores = scoreRepository.findAllScoreByUserId(id);
    List<ScoreDto> scoresDto = new ArrayList<>();
    scores.forEach(score -> {
      scoresDto.add(new ScoreDto(score));
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
      Set<Clazz> clazzs = user.getClazzList();
      if (!clazzs.isEmpty()) {
        clazz = clazzs.iterator().next();
      }
      userDtos.add(new UserDto(user, clazz));
    });
    return userDtos;
  }
  
  /**
   * @author TrangDM2
   * @param classId
   * @return
   */
  @Override
  public List<User> findUserNotInClass(Integer classId, String role){
    return userRepository.findTrainerNotInClassByClassId(classId, role);
  }

  /**
   *@author TrangDM2
   */
  @Override
  public void updateUserStatus(int id, String status) {
    userRepository.updateUserStatus(status, id);
  }
  
  /**
   * @author TrangDM2
   * @param clazzId
   * @param role
   * @return
   */
  @Override
  public List<User> getUserByClazzAndRole(Integer clazzId, String role){
    return userRepository.findUserByClazzAndRole(clazzId, role);
  }

  /**
   *@author TrangDM2
   */
  @Override
  public List<User> saveUsers(List<User> traineeList) {
    return userRepository.saveAll(traineeList);
  }

  /**
   *@author TrangDM2
   */
  @Override
  public String generateAccount(String firstName, StringBuilder lastName) {
    StringBuilder account = lastName;
    for (int i = 0; i < firstName.length(); i++) {
      char c = firstName.charAt(i);
      if ((int)c <= 90 && (int)c >64) {
        account.append(c);
      }
    }
    int number = userRepository.getNumberOfAccount(account + "%");;
    if(number==0) {
      account.append("");
    } else account.append(String.valueOf(number));
    return account.toString();
  }

}

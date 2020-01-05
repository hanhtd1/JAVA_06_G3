package fa.training.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.training.dto.ScoreDto;
import fa.training.dto.UserDto;
import fa.training.models.User;
import fa.training.services.AdminUserService;

/**
 * @author TrangDM2
 *
 */
@RestController
@RequestMapping("admin")
public class AdminUserManageRestController {

  @Autowired
  private AdminUserService adminUserService;

  /**
   *@author TrangDM2
   */
  @GetMapping("trainee-info")
  public ResponseEntity<User> getTraineeInfo(@RequestParam int id) {
    User user = adminUserService.getUser(id);
    return new ResponseEntity<User>(user, HttpStatus.OK);
  }

  /**
   *@author TrangDM2
   */
  @GetMapping("get-trainees")
  public ResponseEntity<List<UserDto>> getTrainees(@RequestParam String keyword, @RequestParam String status) {
    List<UserDto> trainees = adminUserService.findUserByKeyword(keyword, "ROLE_TRAINEE", status);
    return new ResponseEntity<List<UserDto>>(trainees, HttpStatus.OK);
  }

  /**
   *@author TrangDM2
   */
  @GetMapping("trainee-score")
  public ResponseEntity<List<ScoreDto>> getScores(@RequestParam int id) {
    List<ScoreDto> scores = adminUserService.getScore(id);
    return new ResponseEntity<List<ScoreDto>>(scores, HttpStatus.OK);
  }

  /**
   *@author TrangDM2
   */
  @PostMapping("create-user")
  public ResponseEntity<String> createUser(@RequestBody User user) {
    byte role = Byte.parseByte(user.getRole());
    if (adminUserService.getUserByAccount(user.getAccount()) == null) {
      user.setRole(role==1?"ROLE_TRAINEE":"ROLE_TRAINER");
      user.setStatus("Active");
      user.setPassword("123456789");
      adminUserService.saveUser(user);
      return new ResponseEntity<String>("Add trainee Success", HttpStatus.CREATED);
    } else {
      return new ResponseEntity<String>("Add trainee failed, trainee already exist!", HttpStatus.BAD_REQUEST);
    }
  }

  /**
   *@author TrangDM2
   */
  @PostMapping("update-user")
  public ResponseEntity<String> updaterUser(@RequestBody User user){
    User u = adminUserService.getUserByAccount(user.getAccount());
    user.setPassword(u.getPassword());
    user.setRole(u.getRole());
    user.setStatus(u.getStatus());
    adminUserService.saveUser(user);
    return new ResponseEntity<String>("Update information success",HttpStatus.OK);
  }

  /**
   *@author TrangDM2
   */
  @GetMapping("update-status")
  public ResponseEntity<String> updateStatus(@RequestParam int id, @RequestParam String status){
    adminUserService.updateUserStatus(id, status);
    return new ResponseEntity<String>("Update successfully!",HttpStatus.OK);
  }
  
  /**
   *@author TrangDM2
   */
  @GetMapping("generate-account")
  public ResponseEntity<String> generateAccount(@RequestParam String firstName, @RequestParam String lastName) {
    String account = adminUserService.generateAccount(firstName, lastName);
    return new ResponseEntity<String>(account, HttpStatus.CREATED);
  }

}

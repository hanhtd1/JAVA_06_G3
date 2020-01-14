package fa.training.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.training.dto.ScoreDto;
import fa.training.dto.UserDto;
import fa.training.models.User;
import fa.training.services.IAdminUserService;
import fa.training.utils.Constant;

/**
 * @author TrangDM2
 *
 */
@RestController
@RequestMapping("admin")
public class AdminUserManageRestController {

  @Autowired
  private IAdminUserService adminUserService;

  @Autowired
  private BCryptPasswordEncoder bcrypt;
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
    List<UserDto> trainees = adminUserService.findUserByKeyword(keyword, Constant.TRAINEE, status);
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
    User requestUser = user;
    String message = new String();
    HttpStatus status = null;
    
    try {
      requestUser.setRole(role==1?Constant.TRAINEE:Constant.TRAINER);
      requestUser.setStatus(Constant.DEFAULT_TRAINEE_STATUS);
      requestUser.setPassword(bcrypt.encode(Constant.DEFAULT_PASSWORD));
      adminUserService.saveUser(requestUser);
      message = Constant.CREATE_SUCCESS_MESSAGE;
      status = HttpStatus.OK;
    } catch (Exception e) {
      message = Constant.CREATE_FAIL_MESSAGE;
      status = HttpStatus.BAD_REQUEST;
    }
    
    return new ResponseEntity<String>(message, status);
  }

  /**
   *@author TrangDM2
   */
  @PostMapping("update-user")
  public ResponseEntity<String> updaterUser(@RequestBody User user){
    User updateUser = user;
    User toUpdateUser = adminUserService.getUserByAccount(user.getAccount()).orElseThrow(() -> {
      throw new UsernameNotFoundException(Constant.NOT_FOUND_MESSAGE);
    });
    
    updateUser.setPassword(toUpdateUser.getPassword());
    updateUser.setRole(toUpdateUser.getRole());
    updateUser.setStatus(toUpdateUser.getStatus());
    adminUserService.saveUser(updateUser);
    
    return new ResponseEntity<String>(Constant.UPDATE_SUCCESS_MESSAGE,HttpStatus.OK);
  }

  /**
   *@author TrangDM2
   */
  @GetMapping("update-status")
  public ResponseEntity<String> updateStatus(@RequestParam int id, @RequestParam String status){
    adminUserService.updateUserStatus(id, status);
    return new ResponseEntity<String>(Constant.UPDATE_SUCCESS_MESSAGE,HttpStatus.OK);
  }
  
  /**
   *@author TrangDM2
   */
  @GetMapping("generate-account")
  public ResponseEntity<String> generateAccount(@RequestParam String firstName, @RequestParam StringBuilder lastName) {
    String account = adminUserService.generateAccount(firstName, lastName);
    return new ResponseEntity<String>(account, HttpStatus.CREATED);
  }

}

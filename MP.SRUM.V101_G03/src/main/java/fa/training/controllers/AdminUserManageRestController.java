package fa.training.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.training.dtos.ScoreDto;
import fa.training.dtos.UserDto;
import fa.training.models.Feedback;
import fa.training.models.User;
import fa.training.services.AdminUserService;
import fa.training.services.FeedbackService;
import fa.training.utils.Constant;

/**
 * @author TrangDM2
 *
 */
@RestController
@RequestMapping("admin")
public class AdminUserManageRestController {

  @Autowired
  private AdminUserService adminUserService;

  @Autowired
  private FeedbackService feedbackService;
  @Autowired
  private BCryptPasswordEncoder bcrypt;

  /**
   * @author TrangDM2
   */
  @GetMapping("user-info")
  public ResponseEntity<User> getTraineeInfo(@RequestParam int id) {
    User user = adminUserService.getUser(id);
    return new ResponseEntity<User>(user, HttpStatus.OK);
  }
  
  /**
   * @author TrangDM2
   * @param userId
   * @param subjectId
   * @return
   */
  @GetMapping("get-user-feedback")
  public ResponseEntity<Feedback> getUserFeedback(@RequestParam Integer userId, @RequestParam Integer subjectId){
    Feedback feedback = feedbackService.findBySubjectAndUser(userId, subjectId);
    return new ResponseEntity<Feedback>(feedback, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   */
  @GetMapping("get-users")
  public ResponseEntity<List<UserDto>> getUsers(@RequestParam String keyword, @RequestParam String status,
      @RequestParam Integer role) {
    String userRole = role == 1 ? Constant.TRAINEE : Constant.TRAINER;
    List<UserDto> trainees = adminUserService.findUserByKeyword(keyword, userRole, status);
    return new ResponseEntity<List<UserDto>>(trainees, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   */
  @GetMapping("get-trainers-toadd")
  public ResponseEntity<List<User>> getTrainers(@RequestParam Integer classId) {
    List<User> trainees = adminUserService.findUserNotInClass(classId, Constant.TRAINER);
    return new ResponseEntity<List<User>>(trainees, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   */
  @GetMapping("trainee-score")
  public ResponseEntity<List<ScoreDto>> getScores(@RequestParam int id) {
    List<ScoreDto> scores = adminUserService.getScore(id);
    return new ResponseEntity<List<ScoreDto>>(scores, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   */
  @PostMapping("create-user")
  public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result) {
    if (result.hasErrors()) {
      Map<String, String> errors = result.getFieldErrors().stream()
          .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
      return ResponseEntity.badRequest().body(errors);
    } else {
      byte role = Byte.parseByte(user.getRole());
      User requestUser = user;
      String message = new String();
      try {
        requestUser.setRole(role == 1 ? Constant.TRAINEE : Constant.TRAINER);
        requestUser.setStatus(Constant.USER_DEFAULT_STATUS);
        requestUser.setPassword(bcrypt.encode(Constant.DEFAULT_PASSWORD));
        adminUserService.saveUser(requestUser);
        message = Constant.CREATE_SUCCESS_MESSAGE;
      } catch (Exception e) {
        message = Constant.CREATE_FAIL_MESSAGE;
      }

      return new ResponseEntity<String>(message, HttpStatus.OK);
    }
  }

  /**
   * @author TrangDM2
   */
  @PostMapping("update-user")
  public ResponseEntity<?> updaterUser(@Valid @RequestBody User user, BindingResult result) {
    if (result.hasErrors()) {
      Map<String, String> errors = result.getFieldErrors().stream()
          .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
      return ResponseEntity.badRequest().body(errors);
    } else {
      String message = new String();
      User updateUser = user;
      User toUpdateUser = adminUserService.getUserByAccount(user.getAccount()).orElseThrow(() -> {
        throw new UsernameNotFoundException(Constant.NOT_FOUND_MESSAGE);
      });

      updateUser.setPassword(toUpdateUser.getPassword());
      updateUser.setRole(toUpdateUser.getRole());
      updateUser.setStatus(toUpdateUser.getStatus());

      try {
        adminUserService.saveUser(updateUser);
        message = Constant.UPDATE_SUCCESS_MESSAGE;
      } catch (Exception e) {
        message = Constant.UPDATE_FAIL_MESSAGE;
      }

      return new ResponseEntity<String>(message, HttpStatus.OK);
    }
  }

  /**
   * @author TrangDM2
   */
  @GetMapping("update-status")
  public ResponseEntity<String> updateStatus(@RequestParam int id, @RequestParam String status) {
    adminUserService.updateUserStatus(id, status);
    return new ResponseEntity<String>(Constant.UPDATE_SUCCESS_MESSAGE, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   */
  @GetMapping("generate-account")
  public ResponseEntity<String> generateAccount(@RequestParam String firstName, @RequestParam StringBuilder lastName) {
    String account = adminUserService.generateAccount(firstName, lastName);
    return new ResponseEntity<String>(account, HttpStatus.CREATED);
  }

}

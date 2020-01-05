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

  @GetMapping("trainee-info")
  public ResponseEntity<User> getTraineeInfo(@RequestParam int id) {
    User user = adminUserService.getUser(id);
    return new ResponseEntity<User>(user, HttpStatus.OK);
  }
  
  @GetMapping("trainee-score")
  public ResponseEntity<List<ScoreDto>> getScores(@RequestParam int id) {
    List<ScoreDto> scores = adminUserService.getScore(id);
    return new ResponseEntity<List<ScoreDto>>(scores, HttpStatus.OK);
  }

  @PostMapping("create-user")
  public ResponseEntity<String> createUser(@RequestBody User user) {
    user.setRole("ROLE_TRAINEE");
    user.setStatus("Active");
    user.setPassword("123456789");
    adminUserService.saveUser(user);
    return new ResponseEntity<String>("Add trainee Success", HttpStatus.CREATED);
  }

  
}

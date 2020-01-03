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

import fa.training.models.User;
import fa.training.services.AdminUserService;

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

  @PostMapping("create-user")
  public ResponseEntity<List<User>> createUser(@RequestBody User user) {
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

}

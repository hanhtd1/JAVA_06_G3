package fa.training.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.models.User;

@RestController
@RequestMapping("admin")
public class UserRestService {
  
  @PostMapping("create-user")
  public ResponseEntity<List<User>> createUser() {
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

}

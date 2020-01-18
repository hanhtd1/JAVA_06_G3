package fa.training.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fa.training.dtos.ChangePasswordDto;
import fa.training.models.User;
import fa.training.services.AdminUserService;
import fa.training.utils.Constant;

@Controller
public class AuthenticationController {

  @Autowired
  private AdminUserService adminUserService;

  @Autowired
  private BCryptPasswordEncoder bcrypt;

  /**
   * @author TrangDM2
   * @return
   */
  @GetMapping("login")
  public String login(Authentication auth) {
    if (null == auth) {
      return "sign-in";
    }

    return "redirect:/";
  }

  /**
   * @author TrangDM2
   * @param changePassword
   * @param auth
   * @return
   */
  @PostMapping("change-password")
  public ResponseEntity<?> doChangePassword(@RequestBody ChangePasswordDto changePassword, Authentication auth) {
    String messsage = new String();
    HttpStatus status = null;

    if (changePassword.getAccount().equals(auth.getName().toLowerCase()) && changePassword.isPasswordMatch()) {
      User user = adminUserService.getUserByAccount(auth.getName()).orElse(null);
      user.setPassword(bcrypt.encode(changePassword.getPassword()));
      user.setLastLogin(LocalDateTime.now());
      adminUserService.saveUser(user);
      messsage = Constant.UPDATE_SUCCESS_MESSAGE;
      status = HttpStatus.CREATED;
    } else {
      messsage = Constant.UPDATE_FAIL_MESSAGE;
      status = HttpStatus.BAD_REQUEST;
    }

    return new ResponseEntity<String>(messsage, status);
  }
}

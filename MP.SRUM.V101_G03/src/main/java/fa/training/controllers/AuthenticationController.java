package fa.training.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author TrangDM2
 *
 */
public class AuthenticationController {
  
  @GetMapping({ "/", "login" })
  public String login() {
    return "sign-in";
  }

  @PostMapping("login")
  public String doLogin(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
    System.out.println(email + ", " + password);
    return "redirect:admin/";
  }
}

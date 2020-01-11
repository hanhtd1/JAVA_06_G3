package fa.training.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
  /**
   * @author TrangDM2
   * @return
   */
  @GetMapping("login")
  public String login(Authentication auth) {
    if(null==auth) {
      return "sign-in";      
    }
    return "redirect:/";
  }
}

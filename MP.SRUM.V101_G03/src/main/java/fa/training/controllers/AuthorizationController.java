package fa.training.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorizationController {
  
  /**
   * @author TrangDM2
   * @param req
   * @return
   */
  @RequestMapping("authorization")
  public String authorization(HttpServletRequest req) {
    return req.isUserInRole("ROLE_ADMIN")?"redirect:admin/":req.isUserInRole("ROLE_TRAINER")?"redirect:trainer/":"redirect:trainee/";
  }
  
  /**
   * @author TrangDM2
   * @return
   */
  @GetMapping("/403")
  public String accessDenied() {
    return "403";
  }
}

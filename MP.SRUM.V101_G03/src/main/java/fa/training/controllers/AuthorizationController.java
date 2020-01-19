package fa.training.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.training.models.User;
import fa.training.services.AdminUserService;
import fa.training.utils.Constant;

/**
 * @author TrangDM2
 *
 */
@Controller
public class AuthorizationController {

  @Autowired
  private AdminUserService adminUserService;

  /**
   * @author TrangDM2
   * @return
   */
  @GetMapping("/")
  public String index(Authentication auth) {
    User user = adminUserService.getUserByAccount(auth.getName()).orElse(null);
    return user.getLastLogin() == null ? "change-password" : "redirect:authorization";
  }

  /**
   * @author TrangDM2
   * @param req
   * @return
   */
  @RequestMapping("authorization")
  public String authorization(HttpServletRequest req) {
    return req.isUserInRole(Constant.ADMIN) ? "redirect:admin/"
        : req.isUserInRole(Constant.TRAINER) ? "redirect:trainer/" : "redirect:trainee/";
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

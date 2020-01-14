package fa.training.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.training.utils.Constant;

@Controller
public class AuthorizationController {
  
  /**
   * @author TrangDM2
   * @return
   */
  @GetMapping("/")
  public String index() {
    return "redirect:authorization";
  }
  
  /**
   * @author TrangDM2
   * @param req
   * @return
   */
  @RequestMapping("authorization")
  public String authorization(HttpServletRequest req) {
    return req.isUserInRole(Constant.ADMIN)?"redirect:admin/":req.isUserInRole(Constant.TRAINER)?"redirect:trainer/":"redirect:trainee/";
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

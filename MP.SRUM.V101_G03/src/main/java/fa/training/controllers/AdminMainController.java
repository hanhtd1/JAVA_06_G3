package fa.training.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.training.dtos.UserDto;
import fa.training.models.Clazz;
import fa.training.models.Subject;
import fa.training.models.User;
import fa.training.services.AdminClassService;
import fa.training.services.AdminUserService;
import fa.training.services.SubjectService;

/**
 * @author TrangDM2
 *
 */
@Controller
@RequestMapping("admin")
public class AdminMainController {
  
  @Autowired
  private AdminUserService adminUserService;
  
  @Autowired
  private AdminClassService adminClassService;
  
  @Autowired
  private SubjectService subjectService;
  
  /**
   * @author TrangDM2
   */
  @GetMapping("/")
  public String home(Model model, Authentication auth) {
    String account = auth.getName();
    User trainee = adminUserService.getUserByAccount(account).get();
    model.addAttribute("currentUser", trainee);
    return "index";
  }
  
  /**
   * @author TrangDM2
   */
  @GetMapping("home")
  public String classAdmin() {
    return "class-admin-dashboard";
  }
  
  /**
   * @author TrangDM2
   */
  @GetMapping("class-manage")
  public String classManage(Model model) {
    List<Clazz> clazzes = adminClassService.getClasses();
    model.addAttribute("classes", clazzes);
    return "class-admin-class-manage";
  }
  
  /**
   * @author TrangDM2
   */
  @GetMapping("trainee-manage")
  public String traineeManage(Model model) {
    List<UserDto> trainees = adminUserService.getUsersByRole("ROLE_TRAINEE");
    List<Clazz> classes = adminClassService.getClasses();
    model.addAttribute("trainees", trainees);
    model.addAttribute("classes", classes);
    return "class-admin-trainee-manage";
  }
  
  /**
   * @author HoangLV7
   */
  @GetMapping("subject-manage")
  public String subjectManage(Model model) {
	  List<Subject> subjects = subjectService.findAll();
	  model.addAttribute("subjects", subjects);
    return "class-admin-subject-manage";
  }
  
  /**
   * @author TrangDM2
   */
  @GetMapping("trainer-manage")
  public String trainerManage() {
    return "class-admin-trainer-manage";
  }
  
  /**
   * @author TrangDM2
   */
  @GetMapping("file-manage")
  public String fileManage() {
    return "import-export";
  } 
  
  /**
   * @author TrangDM2
   */
  @GetMapping("guide")
  public String guide() {
    return "guide";
  }
}

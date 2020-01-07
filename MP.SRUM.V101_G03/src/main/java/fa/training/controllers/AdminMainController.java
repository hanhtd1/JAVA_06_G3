package fa.training.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fa.training.dto.UserDto;
import fa.training.models.Clazz;
import fa.training.services.IAdminClassService;
import fa.training.services.IAdminUserService;

/**
 * @author TrangDM2
 *
 */
@Controller
@RequestMapping("admin")
public class AdminMainController {
  
  @Autowired
  private IAdminUserService adminUserService;
  
  @Autowired
  private IAdminClassService adminClassService;
  
  /**
   * @author TrangDM2
   */
  @GetMapping("/")
  public String home() {
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
  @GetMapping("class-detail")
  public String classDetail(Model model, @RequestParam Integer id) {
    Clazz clazz = adminClassService.getClass(id);
    model.addAttribute("class", clazz);
    return "class-detail";
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
   * @author TrangDM2
   */
  @GetMapping("subject-manage")
  public String subjectManage() {
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

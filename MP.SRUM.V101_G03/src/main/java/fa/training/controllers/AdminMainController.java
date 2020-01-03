package fa.training.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.training.dto.UserDto;
import fa.training.services.AdminUserService;

@Controller
@RequestMapping("admin")
public class AdminMainController {
  
  @Autowired
  private AdminUserService adminUserService;
  
  @GetMapping("/")
  public String home() {
    return "index";
  }
  
  @GetMapping("home")
  public String classAdmin() {
    return "class-admin-dashboard";
  }
  
  @GetMapping("class-manage")
  public String classManage() {
    return "class-admin-class-manage";
  }
  
  @GetMapping("trainee-manage")
  public String traineeManage(Model model) {
    List<UserDto> trainees = adminUserService.getUsersByRole("ROLE_TRAINER");
    model.addAttribute("trainees", trainees);
    return "class-admin-trainee-manage";
  }
  
  @GetMapping("subject-manage")
  public String subjectManage() {
    return "class-admin-subject-manage";
  }
  
  @GetMapping("trainer-manage")
  public String trainerManage() {
    return "class-admin-trainer-manage";
  }
  
  @GetMapping("file-manage")
  public String fileManage() {
    return "import-export";
  } 
  
  @GetMapping("guide")
  public String guide() {
    return "guide";
  }
}

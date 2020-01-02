package fa.training.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class MainController {
  
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
  public String traineeManage() {
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

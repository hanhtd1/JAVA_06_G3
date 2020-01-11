package fa.training.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fa.training.models.Clazz;
import fa.training.services.IAdminClassService;
import fa.training.services.IAdminUserService;

@Controller
@RequestMapping("admin")
public class AdminClassManageRestController {
  
  @Autowired
  private IAdminUserService adminUserService;

  @Autowired
  private IAdminClassService adminClassService;

  /**
   * @author TrangDM2
   */
  @GetMapping("class-detail")
  public ResponseEntity<Clazz> classDetail(@RequestParam Integer id) {
    Clazz clazz = adminClassService.getClass(id);
    return new ResponseEntity<Clazz>(clazz, HttpStatus.OK);
  }
  
//  /**
//   * @author TrangDM2
//   */
//  @GetMapping("class-detail")
//  public String classDetail(@RequestParam Integer id, Model model) {
//    Clazz clazz = adminClassService.getClass(id);
//    model.addAttribute("class", clazz);
//    return "class-admin-class-manage :: class-detail";
//  }
  
  @GetMapping("get-clazz-info")
  public ResponseEntity<Clazz> getClazz(@RequestParam Integer id){
    Clazz clazz = adminClassService.getClass(id);
    return new ResponseEntity<Clazz>(clazz, HttpStatus.OK);
  }
  
  @GetMapping("get-classes")
  public ResponseEntity<List<Clazz>> filterClasses(@RequestParam String keyword, @RequestParam String status){
    List<Clazz> clazzs = adminClassService.findClazzByKeyword(keyword, status);
    return new ResponseEntity<List<Clazz>>(clazzs, HttpStatus.OK);
  }
  
  @PostMapping("create-class")
  public ResponseEntity<String> saveClass(@RequestBody Clazz clazz){
    if(clazz.getId()==null) {
      clazz.setStatus("In Coming");
      adminClassService.saveClass(clazz);
      return new ResponseEntity<String>("Create class successfully!", HttpStatus.OK);
    } else {
      Clazz c = adminClassService.getClass(clazz.getId());
      clazz.setUserList(c.getUserList());
      clazz.setClazzSubjectList(c.getClazzSubjectList());
      adminClassService.saveClass(clazz);
      return new ResponseEntity<String>("Update class successfully!", HttpStatus.OK);
    }
  }
  
  @GetMapping("get-classname")
  public ResponseEntity<String> getClassName(@RequestParam String location, @RequestParam String category, @RequestParam String type) {
    String className = adminClassService.getClassName(location, type, category);
    return new ResponseEntity<String>(className, HttpStatus.CREATED);
  }
}

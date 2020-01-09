package fa.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.training.models.Clazz;
import fa.training.services.IAdminClassService;
import fa.training.services.IAdminUserService;

@RestController
@RequestMapping("admin")
public class AdminClassManageRestController {
  
  @Autowired
  private IAdminUserService adminUserService;

  @Autowired
  private IAdminClassService adminClassService;

  /**
   * @author TrangDM2
   */
  @GetMapping("class-info")
  public ResponseEntity<Clazz> classDetail(@RequestParam Integer id) {
    Clazz clazz = adminClassService.getClass(id);
    return new ResponseEntity<Clazz>(clazz, HttpStatus.OK);
  }
  
  @GetMapping("get-clazz-info")
  public ResponseEntity<Clazz> getClazz(@RequestParam Integer id){
    Clazz clazz = adminClassService.getClass(id);
    return new ResponseEntity<Clazz>(clazz, HttpStatus.OK);
  }
}

package fa.training.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.training.dto.AttendanceDto;
import fa.training.dto.UserDto;
import fa.training.models.Attendance;
import fa.training.models.Clazz;
import fa.training.models.User;
import fa.training.services.AdminClassService;
import fa.training.services.AdminUserService;
import fa.training.services.AttendanceService;
import fa.training.utils.Constant;

@RestController
@RequestMapping("admin")
public class AdminClassManageRestController {

  @Autowired
  private AdminClassService adminClassService;

  @Autowired
  private AdminUserService adminUserService;

  @Autowired
  private AttendanceService attendanceService;
  /**
   * @author TrangDM2
   */
  @GetMapping("class-detail")
  public ResponseEntity<Clazz> classDetail(@RequestParam Integer id) {
    Clazz clazz = adminClassService.getClass(id);
    return new ResponseEntity<Clazz>(clazz, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   */
  @GetMapping("get-clazz-info")
  public ResponseEntity<Clazz> getClazz(@RequestParam Integer id) {
    Clazz clazz = adminClassService.getClass(id);
    return new ResponseEntity<Clazz>(clazz, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   */
  @GetMapping("get-classes")
  public ResponseEntity<List<Clazz>> filterClasses(@RequestParam String keyword, @RequestParam String status) {
    List<Clazz> clazzs = adminClassService.findClazzByKeyword(keyword, status);
    return new ResponseEntity<List<Clazz>>(clazzs, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   */
  @PostMapping("create-class")
  public ResponseEntity<String> saveClass(@RequestBody Clazz clazz) {
    String message = new String();
    Clazz updateClazz = clazz;

    if (clazz.getId() == null) {
      try {
        updateClazz.setStatus(Constant.CLASS_DEFAULT_STATUS);
        adminClassService.saveClass(clazz);
        message = Constant.CREATE_SUCCESS_MESSAGE;
      } catch (Exception e) {
        return new ResponseEntity<String>(Constant.CREATE_FAIL_MESSAGE, HttpStatus.BAD_REQUEST);
      }
    } else {
      Clazz toUpdateClazz = adminClassService.getClass(updateClazz.getId());
      updateClazz.setUserList(toUpdateClazz.getUserList());
      updateClazz.setStatus(toUpdateClazz.getStatus());
      updateClazz.setClazzSubjectList(toUpdateClazz.getClazzSubjectList());
      adminClassService.saveClass(updateClazz);
      message = Constant.CREATE_SUCCESS_MESSAGE;
    }

    return new ResponseEntity<String>(message, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   * @param attendancesDto
   * @return
   */
  @PostMapping("do-attendance")
  public ResponseEntity<String> attendance(@RequestBody List<AttendanceDto> attendances) {
    List<Attendance> attendanceList = new ArrayList<>();
    String message = new String();
    
    attendances.forEach(attend -> {
      Attendance attendace = new Attendance();
      attendace.setDate(LocalDate.now());
      attendace.setType(attend.getType());
      attendace.setNote(attend.getNote());
      attendace.setUser(adminUserService.getUser(attend.getUserId()));
      attendanceList.add(attendace);
    });
    
    try {
      attendanceService.saveAttendances(attendanceList);
      message = Constant.CREATE_SUCCESS_MESSAGE;
    } catch (Exception e) {
     message = Constant.UPDATE_FAIL_MESSAGE; 
    }
    
    return new ResponseEntity<String>(message, HttpStatus.CREATED);
  }

  /**
   * @author TrangDM2
   */
  @GetMapping("update-classstatus")
  public ResponseEntity<String> updateStatus(@RequestParam String status, @RequestParam int id) {
    Clazz clazz = adminClassService.getClass(id);
    clazz.setStatus(status);
    adminClassService.saveClass(clazz);
    return new ResponseEntity<String>(Constant.CREATE_SUCCESS_MESSAGE, HttpStatus.OK);
  }

  /**
   * TODO
   * 
   * @author TrangDM2
   * @param id
   * @return
   */
  @GetMapping("add-trainees")
  public ResponseEntity<List<UserDto>> addTrainees(@RequestParam Integer id, @RequestParam String keyword) {
    List<UserDto> trainees = adminUserService.findUserByKeyword(keyword, Constant.TRAINEE, Constant.TRAINEE_DEFAULT_STATUS);
    return new ResponseEntity<List<UserDto>>(trainees, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   * @param traineeId
   * @param classId
   * @return
   */
  @GetMapping("add-trainee-toclass")
  public ResponseEntity<String> addTraineeToClass(@RequestParam Integer traineeId, @RequestParam Integer classId) {
    Clazz clazz = adminClassService.getClass(classId);
    User user = adminUserService.getUser(traineeId);
    String message = new String();
    
    user.setStatus(Constant.TRAINEE_ACTIVE_STATUS);
    user.getClazzList().add(clazz);
    clazz.getUserList().add(user);
    
    try {
      adminClassService.saveClass(clazz);
      adminUserService.saveUser(user); 
      message = Constant.CREATE_SUCCESS_MESSAGE;
    } catch(Exception e) {
      message = Constant.UPDATE_FAIL_MESSAGE;
    }
    
    return new ResponseEntity<String>(message,HttpStatus.OK);
  }
  
  /**
   * @author TrangDM2
   * @param traineeId
   * @param classId
   * @return
   */
  @GetMapping("remove-trainee")
  public ResponseEntity<String> removeTraineeFromClass(@RequestParam Integer traineeId, @RequestParam Integer classId) {
    Clazz clazz = adminClassService.getClass(classId);
    User user = adminUserService.getUser(traineeId);
    String message = new String();
    
    user.setStatus(Constant.TRAINEE_DEFAULT_STATUS);
    user.getClazzList().remove(clazz);
    clazz.getUserList().remove(user);
    
    try {
      adminClassService.saveClass(clazz);
      adminUserService.saveUser(user); 
      message = Constant.CREATE_SUCCESS_MESSAGE;
    } catch(Exception e) {
      message = Constant.UPDATE_FAIL_MESSAGE;
    }
    
    return new ResponseEntity<String>(message,HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   */
  @GetMapping("get-classname")
  public ResponseEntity<String> getClassName(@RequestParam String location, @RequestParam String category,
      @RequestParam String type) {
    String className = adminClassService.generateClassName(location, type, category);
    return new ResponseEntity<String>(className, HttpStatus.CREATED);
  }
}

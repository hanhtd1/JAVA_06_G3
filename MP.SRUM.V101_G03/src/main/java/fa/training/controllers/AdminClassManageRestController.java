package fa.training.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.training.dtos.AdminScoreDto;
import fa.training.dtos.AttendanceDto;
import fa.training.dtos.UserDto;
import fa.training.models.Clazz;
import fa.training.models.Subject;
import fa.training.models.User;
import fa.training.services.AdminClassService;
import fa.training.services.AdminUserService;
import fa.training.services.AttendanceService;
import fa.training.services.ScoreService;
import fa.training.services.SubjectService;
import fa.training.utils.Constant;

/**
 * @author TrangDM2
 *
 */
@RestController
@RequestMapping("admin")
public class AdminClassManageRestController {

  @Autowired
  private AdminClassService adminClassService;

  @Autowired
  private AdminUserService adminUserService;

  @Autowired
  private AttendanceService attendanceService;

  @Autowired
  private SubjectService subjectService;

  @Autowired
  private ScoreService scoreService;

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
  public ResponseEntity<?> saveClass(@Valid @RequestBody Clazz clazz, BindingResult result) {
    if (result.hasErrors()) {
      Map<String, String> errors = result.getFieldErrors().stream()
          .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
      return ResponseEntity.badRequest().body(errors);
    } else {
      String message = new String();
      Clazz updateClazz = clazz;

      try {
        adminClassService.saveClass(updateClazz, Constant.CLASS_DEFAULT_STATUS);
        message = Constant.UPDATE_SUCCESS_MESSAGE;
      } catch (Exception e) {
        message = Constant.UPDATE_FAIL_MESSAGE;
      }

      return new ResponseEntity<String>(message, HttpStatus.OK);
    }
  }

  /**
   * @author TrangDM2
   * @param attendancesDto
   * @return
   */
  @PostMapping("do-attendance")
  public ResponseEntity<String> attendance(@RequestBody List<AttendanceDto> attendances) {
    String message = new String();
    HttpStatus status = null;

    if (attendanceService.saveAttendances(attendances)) {
      message = Constant.UPDATE_SUCCESS_MESSAGE;
      status = HttpStatus.CREATED;
    } else {
      message = Constant.UPDATE_FAIL_MESSAGE;
      status = HttpStatus.BAD_REQUEST;
    }

    return new ResponseEntity<String>(message, status);
  }

  /**
   * @author TrangDM2
   */
  @GetMapping("update-classstatus")
  public ResponseEntity<String> updateStatus(@RequestParam String status, @RequestParam int id) {
    Clazz clazz = adminClassService.getClass(id);
    adminClassService.saveClass(clazz, status);
    return new ResponseEntity<String>(Constant.UPDATE_SUCCESS_MESSAGE, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   * @param id
   * @return
   */
  @GetMapping("load-add-trainees")
  public ResponseEntity<List<UserDto>> addTrainees(@RequestParam Integer id, @RequestParam String keyword) {
    List<UserDto> trainees = adminUserService.findUserByKeyword(keyword, Constant.TRAINEE,
        Constant.USER_DEFAULT_STATUS);
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
    String message = new String();

    try {
      adminClassService.addUserToClass(traineeId, classId);
      message = Constant.CREATE_SUCCESS_MESSAGE;
    } catch (Exception e) {
      message = Constant.CREATE_FAIL_MESSAGE;
    }

    return new ResponseEntity<String>(message, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   * @param traineeId
   * @param classId
   * @return
   */
  @GetMapping("remove-trainee")
  public ResponseEntity<String> removeTraineeFromClass(@RequestParam Integer traineeId, @RequestParam Integer classId) {
    String message = new String();

    try {
      adminClassService.removeUserFromClass(traineeId, classId);
      message = Constant.CREATE_SUCCESS_MESSAGE;
    } catch (Exception e) {
      message = Constant.CREATE_FAIL_MESSAGE;
    }

    return new ResponseEntity<String>(message, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   * @param classId
   * @return
   */
  @GetMapping("load-all-subjects")
  public ResponseEntity<List<Subject>> getAllSubjects() {
    List<Subject> subjects = subjectService.findAll();
    return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   * @param classId
   * @return
   */
  @GetMapping("load-added-subjects")
  public ResponseEntity<List<Subject>> getAddedSubjects(@RequestParam Integer clazzId) {
    List<Subject> subjects = subjectService.findSubjectsByClass(clazzId);
    return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   * @param subjectId
   * @param classId
   * @return
   */
  @GetMapping("add-subject-to-class")
  public ResponseEntity<String> addSubjectToClass(@RequestParam Integer subjectId, @RequestParam Integer classId) {
    String message = new String();

    try {
      adminClassService.addSubjectToClass(subjectId, classId);
      message = Constant.UPDATE_SUCCESS_MESSAGE;
    } catch (Exception e) {
      message = Constant.UPDATE_FAIL_MESSAGE;
    }

    return new ResponseEntity<String>(message, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   * @param clazzId
   * @param trainerId
   * @return
   */
  @GetMapping("remove-subject-fromclass")
  public ResponseEntity<String> removeSubject(@RequestParam Integer subjectId, @RequestParam Integer clazzId) {
    String message = new String();

    try {
      adminClassService.removeSubjectFromClass(subjectId, clazzId);
      message = Constant.UPDATE_SUCCESS_MESSAGE;
    } catch (Exception e) {
      message = Constant.UPDATE_FAIL_MESSAGE;
    }

    return new ResponseEntity<String>(message, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   */
  @GetMapping("trainer-class")
  public ResponseEntity<List<Clazz>> trainerClass(@RequestParam int id) {
    List<Clazz> classes = adminClassService.getClassByUser(id);
    return new ResponseEntity<List<Clazz>>(classes, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   * @param clazzId
   * @return
   */
  @GetMapping("get-inclass-trainer")
  public ResponseEntity<List<User>> getTrainersOfClass(@RequestParam Integer clazzId) {
    List<User> clazzs = adminUserService.getUserByClazzAndRole(clazzId, Constant.TRAINER);
    return new ResponseEntity<List<User>>(clazzs, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   * @param clazzId
   * @param trainerId
   * @return
   */
  @GetMapping("add-trainer-toclass")
  public ResponseEntity<String> addTrainer(@RequestParam Integer clazzId, @RequestParam Integer trainerId) {
    String message = new String();

    try {
      adminClassService.addUserToClass(trainerId, clazzId);
      message = Constant.CREATE_SUCCESS_MESSAGE;
    } catch (Exception e) {
      message = Constant.CREATE_FAIL_MESSAGE;
    }

    return new ResponseEntity<String>(message, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   * @param clazzId
   * @param trainerId
   * @return
   */
  @GetMapping("remove-trainer-fromclass")
  public ResponseEntity<String> removeTrainer(@RequestParam Integer clazzId, @RequestParam Integer trainerId) {
    String message = new String();

    try {
      adminClassService.removeUserFromClass(trainerId, clazzId);
      message = Constant.CREATE_SUCCESS_MESSAGE;
    } catch (Exception e) {
      message = Constant.CREATE_FAIL_MESSAGE;
    }

    return new ResponseEntity<String>(message, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   * @param scoreDtos
   * @return
   */
  @PostMapping("update-marks")
  public ResponseEntity<?> updateMarks(@RequestBody List<AdminScoreDto> scoreDtos) {
    String message = new String();
    HttpStatus status = null;

    try {
      scoreService.saveScores(scoreDtos);
      
      message = Constant.UPDATE_SUCCESS_MESSAGE;
      status = HttpStatus.CREATED;
    } catch (Exception e) {
      message = Constant.UPDATE_FAIL_MESSAGE;
      status = HttpStatus.BAD_REQUEST;
    }

    return new ResponseEntity<String>(message, status);
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

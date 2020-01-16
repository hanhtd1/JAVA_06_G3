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

import fa.training.dtos.AdminScoreDto;
import fa.training.dtos.AttendanceDto;
import fa.training.dtos.UserDto;
import fa.training.models.Attendance;
import fa.training.models.Clazz;
import fa.training.models.Score;
import fa.training.models.ScorePK;
import fa.training.models.Subject;
import fa.training.models.User;
import fa.training.services.AdminClassService;
import fa.training.services.AdminUserService;
import fa.training.services.AttendanceService;
import fa.training.services.ScoreService;
import fa.training.services.SubjectService;
import fa.training.utils.Constant;

@RestController
@RequestMapping("admin")
public class AdminClassManageRestController {

  @Autowired
  private AdminClassService adminClazzService;

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
    Clazz clazz = adminClazzService.getClass(id);
    return new ResponseEntity<Clazz>(clazz, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   */
  @GetMapping("get-clazz-info")
  public ResponseEntity<Clazz> getClazz(@RequestParam Integer id) {
    Clazz clazz = adminClazzService.getClass(id);
    return new ResponseEntity<Clazz>(clazz, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   */
  @GetMapping("get-classes")
  public ResponseEntity<List<Clazz>> filterClasses(@RequestParam String keyword, @RequestParam String status) {
    List<Clazz> clazzs = adminClazzService.findClazzByKeyword(keyword, status);
    return new ResponseEntity<List<Clazz>>(clazzs, HttpStatus.OK);
  }

  /**
   * @author TrangDM2
   */
  @PostMapping("create-class")
  public ResponseEntity<String> saveClass(@RequestBody Clazz clazz) {
    String message = new String();
    HttpStatus status = null;
    Clazz updateClazz = clazz;

    try {
      if (clazz.getId() == null) {
        updateClazz.setStatus(Constant.CLASS_DEFAULT_STATUS);
        adminClazzService.saveClass(clazz);
        message = Constant.UPDATE_SUCCESS_MESSAGE;
        status = HttpStatus.OK;
      } else {
        Clazz toUpdateClazz = adminClazzService.getClass(updateClazz.getId());
        updateClazz.setUserList(toUpdateClazz.getUserList());
        updateClazz.setStatus(toUpdateClazz.getStatus());
        updateClazz.setSubjectList(toUpdateClazz.getSubjectList());
        adminClazzService.saveClass(updateClazz);
        message = Constant.CREATE_SUCCESS_MESSAGE;
        status = HttpStatus.CREATED;
      }
    } catch (Exception e) {
      message = Constant.UPDATE_FAIL_MESSAGE;
      status = HttpStatus.BAD_REQUEST;
    }

    return new ResponseEntity<String>(message, status);
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
      User trainee = adminUserService.getUser(attend.getUserId());
      Attendance attendace = new Attendance();
      attendace.setDate(LocalDate.now());
      attendace.setType(attend.getType());
      attendace.setNote(attend.getNote());
      attendace.setUser(trainee);
      attendanceList.add(attendace);
      System.out.println(attendace);
    });

    try {
      attendanceService.saveAttendances(attendanceList);
      message = Constant.UPDATE_SUCCESS_MESSAGE;
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
    Clazz clazz = adminClazzService.getClass(id);
    clazz.setStatus(status);
    adminClazzService.saveClass(clazz);
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
    Clazz clazz = adminClazzService.getClass(classId);
    User user = adminUserService.getUser(traineeId);
    String message = new String();

    user.setStatus(Constant.TRAINEE_ACTIVE_STATUS);
    user.getClazzList().add(clazz);
    clazz.getUserList().add(user);

    try {
      adminClazzService.saveClass(clazz);
      adminUserService.saveUser(user);
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
    Clazz clazz = adminClazzService.getClass(classId);
    User user = adminUserService.getUser(traineeId);
    String message = new String();

    user.setStatus(Constant.USER_DEFAULT_STATUS);
    user.getClazzList().remove(clazz);
    clazz.getUserList().remove(user);

    try {
      adminClazzService.saveClass(clazz);
      adminUserService.saveUser(user);
      message = Constant.UPDATE_SUCCESS_MESSAGE;
    } catch (Exception e) {
      message = Constant.UPDATE_FAIL_MESSAGE;
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
    Subject subject = subjectService.findSubjectById(subjectId);
    Clazz clazz = adminClazzService.getClass(classId);
    String message = new String();

    subject.getClazzList().add(clazz);
    clazz.getSubjectList().add(subject);

    try {
      subjectService.save(subject);
      adminClazzService.saveClass(clazz);
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
    Subject subject = subjectService.findSubjectById(subjectId);
    Clazz clazz = adminClazzService.getClass(clazzId);
    String message = new String();

    subject.getClazzList().remove(clazz);
    clazz.getSubjectList().remove(subject);

    try {
      subjectService.save(subject);
      adminClazzService.saveClass(clazz);
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
    List<Clazz> classes = adminClazzService.getClassByUser(id);
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
    Clazz clazz = adminClazzService.getClass(clazzId);
    User trainer = adminUserService.getUser(trainerId);
    String message = new String();

    clazz.getUserList().add(trainer);
    trainer.getClazzList().add(clazz);

    try {
      adminClazzService.saveClass(clazz);
      adminUserService.saveUser(trainer);
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
  @GetMapping("remove-trainer-fromclass")
  public ResponseEntity<String> removeTrainer(@RequestParam Integer clazzId, @RequestParam Integer trainerId) {
    Clazz clazz = adminClazzService.getClass(clazzId);
    User trainer = adminUserService.getUser(trainerId);
    String message = new String();

    clazz.getUserList().remove(trainer);
    trainer.getClazzList().remove(clazz);

    try {
      adminClazzService.saveClass(clazz);
      adminUserService.saveUser(trainer);
      message = Constant.UPDATE_SUCCESS_MESSAGE;
    } catch (Exception e) {
      message = Constant.UPDATE_FAIL_MESSAGE;
    }
    return new ResponseEntity<String>(message, HttpStatus.OK);
  }

  @PostMapping("update-marks")
  public ResponseEntity<String> updateMarks(@RequestBody List<AdminScoreDto> scoreDtos) {
    String message = new String();
    HttpStatus status = null;
    
    try {
      scoreDtos.forEach(scoreDto -> {
        ScorePK scorePk = new ScorePK(scoreDto.getSubjectId(), scoreDto.getUserId());
        Score score = new Score(scorePk, scoreDto.getTheory(), scoreDto.getPractice());
        scoreService.saveScore(score);
      });
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
    String className = adminClazzService.generateClassName(location, type, category);
    return new ResponseEntity<String>(className, HttpStatus.CREATED);
  }
}

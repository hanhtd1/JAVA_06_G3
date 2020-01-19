package fa.training.services.implement;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.models.Clazz;
import fa.training.models.Subject;
import fa.training.models.User;
import fa.training.repositories.ClassRepository;
import fa.training.repositories.SubjectRepository;
import fa.training.repositories.UserRepository;
import fa.training.services.AdminClassService;
import fa.training.utils.Constant;

/**
 * @author TrangDM2
 *
 */
@Service
public class AdminClassServiceImpl implements AdminClassService {

  @Autowired
  private ClassRepository classRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private SubjectRepository subjectRepository;

  /**
   * @author TrangDM2
   */
  @Override
  public Clazz saveClass(Clazz clazz, String status) {
    Clazz updateClazz = clazz;

    if (updateClazz.getId() != null) {
      Clazz toUpdateClazz = this.getClass(updateClazz.getId());
      
      updateClazz.setUserList(toUpdateClazz.getUserList());
      updateClazz.setStatus(toUpdateClazz.getStatus());
      updateClazz.setSubjectList(toUpdateClazz.getSubjectList());
    }
    
    updateClazz.setStatus(status);
    
    return classRepository.save(updateClazz);
  }

  /**
   * @author TrangDM2
   * @param userId
   * @param classId
   * @return
   * @throws IllegalArgumentException
   */
  @Override
  public Clazz addUserToClass(Integer userId, Integer classId) throws IllegalArgumentException {
    Clazz clazz = this.getClass(classId);
    User user = userRepository.findUserById(userId);
    
    if(user.getRole().equals(Constant.TRAINEE)) {
      user.setStatus(Constant.TRAINEE_ACTIVE_STATUS);
      userRepository.save(user);      
    }
    
    clazz.getUserList().add(user);
    
    return classRepository.save(clazz);
  }

  /**
   * @author TrangDM2
   * @param userId
   * @param classId
   * @return
   * @throws IllegalArgumentException
   */
  @Override
  public Clazz removeUserFromClass(Integer userId, Integer classId) throws IllegalArgumentException {
    Clazz clazz = this.getClass(classId);
    User user = userRepository.findUserById(userId);
    
    if(user.getRole().equals(Constant.TRAINEE)) {
      user.setStatus(Constant.TRAINEE_DEFAULT_STATUS);
      userRepository.save(user);
    }
    
    clazz.getUserList().remove(user);

    return classRepository.save(clazz);
  }

  /**
   * @author TrangDM2
   * @param subjectId
   * @param classId
   * @return
   * @throws IllegalArgumentException
   */
  @Override
  public Clazz addSubjectToClass(Integer subjectId, Integer classId) throws IllegalArgumentException {
    Subject subject = subjectRepository.findSubjectById(subjectId).orElse(null);
    Clazz clazz = this.getClass(classId);
    
    clazz.getSubjectList().add(subject);
    
    return classRepository.save(clazz);
  }

  /**
   * @author TrangDM2
   * @param subjectId
   * @param classId
   * @return
   * @throws IllegalArgumentException
   */
  @Override
  public Clazz removeSubjectFromClass(Integer subjectId, Integer classId) throws IllegalArgumentException {
    Subject subject = subjectRepository.findSubjectById(subjectId).orElse(null);
    Clazz clazz = this.getClass(classId);
    
    clazz.getSubjectList().remove(subject);
    
    return classRepository.save(clazz);
  }

  /**
   * @author TrangDM2
   */
  @Override
  public List<Clazz> getClasses() {
    return classRepository.findAll();
  }

  /**
   * @author TrangDM2
   */
  @Override
  public List<Clazz> findClazzByKeyword(String keyword, String status) {
    return classRepository.findClassByKeyword("%" + keyword + "%", "%" + status + "%");
  }

  /**
   * @author TrangDM2
   * @param
   * @return
   */
  @Override
  public String generateClassName(String location, String type, String category) {
    String year = Integer.toString(LocalDate.now().getYear());
    String subYear = year.substring(2, 4);
    StringBuilder clazzName = new StringBuilder();

    clazzName.append(location);
    clazzName.append(subYear);
    clazzName.append("_");
    clazzName.append(type);
    clazzName.append("_");
    clazzName.append(category.toUpperCase());
    clazzName.append("_");

    int count = classRepository.getClassNumber(clazzName + "%") + 1;
    
    if (count < 10) {
      clazzName.append("0");
    }

    clazzName.append(count);
    
    return clazzName.toString();
  }

  /**
   * @author TrangDM2
   */
  @Override
  public Clazz getClass(Integer id) {
    return classRepository.findById(id).get();
  }

  /**
   * @author TrangDM2
   */
  @Override
  public Clazz getClassByName(String name) {
    return classRepository.findByName(name);
  }

  /**
   * @author TrangDM2
   * @param userId
   * @return
   */
  @Override
  public List<Clazz> getClassByUser(Integer userId) {
    return classRepository.findClazzsByUser(userId);
  }
}

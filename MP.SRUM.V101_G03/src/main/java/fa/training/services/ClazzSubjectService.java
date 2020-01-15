package fa.training.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fa.training.models.Clazz;
import fa.training.models.ClazzSubject;
import fa.training.models.Subject;

/**
 * @author TrangDM2
 *
 */
@Service
public interface ClazzSubjectService {
  
  /**
   * @author TrangDM2
   * @param clazzSubject
   * @return
   */
  ClazzSubject saveClazzSubject(ClazzSubject clazzSubject);

  /**
   * @author TrangDM2
   * @param clazz
   * @return
   */
  List<Subject> getSubjectByClazz(Clazz clazz);
  
  /**
   * @author TrangDM2
   * @param clazz
   * @return
   */
  List<Subject> findSubjectsByClass(Clazz clazz);
}

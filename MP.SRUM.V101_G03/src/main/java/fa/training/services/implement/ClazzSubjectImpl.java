package fa.training.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.models.Clazz;
import fa.training.models.ClazzSubject;
import fa.training.models.Subject;
import fa.training.repositories.ClazzSubjectRepository;
import fa.training.services.ClazzSubjectService;

@Service
public class ClazzSubjectImpl implements ClazzSubjectService {

  @Autowired
  private ClazzSubjectRepository clazzSubjectRepository;

  /**
   *@author TrangDM2
   */
  @Override
  public ClazzSubject saveClazzSubject(ClazzSubject clazzSubject) {
    return clazzSubjectRepository.save(clazzSubject);
  }

  @Override
  public List<Subject> getSubjectByClazz(Clazz clazz) {
    return clazzSubjectRepository.findSubjectByClazz(clazz);
  }

  @Override
  public List<Subject> findSubjectsByClass(Clazz clazz) {
    return clazzSubjectRepository.findSubjectByClazz(clazz);
  }
}

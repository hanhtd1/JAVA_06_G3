package fa.training.services.implement;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fa.training.dtos.ApiObject;
import fa.training.models.Subject;
import fa.training.repositories.SubjectRepository;
import fa.training.services.SubjectService;
import fa.training.utils.Constant;

/**
 *
 * @author ToanNT18
 */
@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

  public static int pageTotals;

  /**
   * @author TrangDM2
   * @param clazz
   * @return
   */
  @Override
  public List<Subject> findSubjectsByClass(Integer clazzId) {
    return subjectRepository.findSubjectsByClass(clazzId);
  }

  @Autowired
  private SubjectRepository subjectRepository;

  @Override
  public List<Subject> findAll() {
    return subjectRepository.findByStatus(Constant.SUBJECT_ACTIVE_STATUS);
  }

  @Override
  public ApiObject<Subject> save(Subject subject) {
    ApiObject<Subject> apiObject = new ApiObject<Subject>();
    boolean checkExisted = checkSubjectExisted(subject.getCode());
    if (checkExisted && subject.getDuration() < 0) {
      apiObject.setMessage(Constant.CREATE_FAIL_MESSAGE);
    } else {
      subject = subjectRepository.save(subject);
      apiObject.setMessage(Constant.CREATE_SUCCESS_MESSAGE);
      apiObject.setT(subject);
    }
    return apiObject;
  }

  @Override
  public ApiObject<Subject> update(Subject subject) {
    ApiObject<Subject> apiObject = new ApiObject<Subject>();
    boolean checkExisted = checkSubjectExisted(subject.getCode());
    if (!checkExisted && subject.getDuration() < 0) {
      apiObject.setMessage(Constant.UPDATE_FAIL_MESSAGE);
    } else {
      subject = subjectRepository.save(subject);
      apiObject.setMessage(Constant.UPDATE_SUCCESS_MESSAGE);
      apiObject.setT(subject);
    }
    return apiObject;
  }

  @Override
  public Subject findSubjectByCode(String code) {
    return subjectRepository.findSubjectByCode(code).orElse(new Subject());
  }

  @Override
  public Boolean checkSubjectExisted(String code) {
    return subjectRepository.findSubjectByCode(code).isPresent();
  }

  @Override
  public Subject findSubjectById(int id) {
    return subjectRepository.findSubjectById(id).orElse(new Subject());
  }

  @Override
  public Subject delSubject(int id) {
    Subject subject = subjectRepository.findSubjectById(id).orElse(new Subject());
    subject.setStatus(Constant.SUBJECT_DISABLED_STATUS);
    return subject.getId() != null ? subjectRepository.save(subject) : new Subject();
  }

  @Override
  public List<Subject> findByStatus(String status) {
    return status != null ? subjectRepository.findSubjectByStatus(status) : new ArrayList<Subject>();
  }

  @Override
  public List<Subject> findSubjectByClazz(Integer clazzId, Pageable pageable) {
    Page<Subject> pageSubject = subjectRepository.findSubjectByClazzId(clazzId, pageable);
    return pageSubject.getContent();
  }

  @Override
  public List<Subject> findSubjectByUserId(Integer userId, Pageable pageable) {
    Page<Subject> pageSubject = subjectRepository.findSubjectByUserId(userId, pageable);
    return pageSubject.getContent();
  }

}

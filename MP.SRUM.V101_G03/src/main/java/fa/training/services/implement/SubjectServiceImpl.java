package fa.training.services.implement;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public List<Subject> findSubjectByClazz(Integer clazzId) {
		return subjectRepository.findSubjectByClazzId(clazzId);
	}

	@Override
	public List<Subject> findSubjectByUserId(Integer userId) {
		return subjectRepository.findSubjectByUserId(userId);
	}

	@Override
	public List<Subject> findAll() {
		String status = Constant.SUBJECT_STATUS_ACTIVE;
		return subjectRepository.findByStatus(status);
	}

	@Override
	public Subject save(Subject subject) {
		return subjectRepository.save(subject);
	}

	@Override
	public Subject findSubjectByCode(String code) {
		return subjectRepository.findSubjectByCode(code).orElse(new Subject());
	}

	@Override
	public Boolean checkSubjectExisted(String code) {
		return findSubjectByCode(code).getId() != null? true : false;
	}

	@Override
	public Subject findSubjectById(int id) {
		return subjectRepository.findSubjectById(id).orElse(new Subject());
	}

}

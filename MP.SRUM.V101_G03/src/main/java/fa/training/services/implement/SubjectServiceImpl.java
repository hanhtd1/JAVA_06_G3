package fa.training.services.implement;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public static int pageTotals;

	@Autowired
	private SubjectRepository subjectRepository;

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

	@Override
	public List<Subject> findAll() {
		String status = Constant.SUBJECT_ACTIVE_STATUS;
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
		return findSubjectByCode(code).getId() != null ? true : false;
	}

	@Override
	public Subject findSubjectById(int id) {
		return subjectRepository.findSubjectById(id).orElse(new Subject());
	}

}

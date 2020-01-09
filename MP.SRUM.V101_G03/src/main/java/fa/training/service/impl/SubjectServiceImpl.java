package fa.training.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.models.Subject;
import fa.training.repositories.trainer.SubjectRepository;
import fa.training.service.SubjectService;

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

}

package fa.training.services.implement;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
	public Subject findSubjectByClazz(Integer clazzId) {
		return subjectRepository.findSubjectByClazzId(clazzId);
	}

	@Override
	public List<Subject> findSubjectByUserId(Integer userId, Integer pageIndex) {
		return subjectRepository.findSubjectByUserId(userId, PageRequest.of(pageIndex, Constant.PAGE_SIZE))
				.getContent();
	}

}

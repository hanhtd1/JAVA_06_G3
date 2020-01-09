package fa.training.service;

import java.util.List;

import fa.training.models.Subject;

public interface SubjectService {
	/**
	 * @author ToanNT18
	 * @param clazzId
	 * @return all subject by clazz
	 */
	List<Subject> findSubjectByClazz(Integer clazzId);

	/**
	 * @author ToanNT18
	 * @param userId
	 * @return all subject which is teaach by trainer
	 */
	List<Subject> findSubjectByUserId(Integer userId);
}

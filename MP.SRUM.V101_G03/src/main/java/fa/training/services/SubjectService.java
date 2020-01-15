package fa.training.services;

import java.util.List;

import fa.training.models.Clazz;
import fa.training.models.Subject;

public interface SubjectService {
	/**
	 * @author ToanNT18
	 * @param clazzId
	 * @return all subject by clazz
	 */
	Subject findSubjectByClazz(Integer clazzId);

	/**
	 * @author HoangLV7
	 *
	 * @return list of subject that be active
	 */
	List<Subject> findAll();
	
	/**
	 * @author HoangLV7
	 *
	 * @param subject
	 * @return
	 */
	Subject save(Subject subject);

	/**
	 * @author HoangLV7
	 *
	 * @param code
	 * @return
	 */
	Subject findSubjectByCode(String code);
	
	/**
	 * @author HoangLV7
	 *
	 * @param code
	 * @return true is subject existed, or not return false
	 */
	Boolean checkSubjectExisted(String code);
	
	/**
	 * @author HoangLV7
	 *
	 * @param id
	 * @return
	 */
	Subject findSubjectById(int id);

	/**@author ToanNT18
	 * @param userId
	 * @param pageIndex
	 * @return
	 */
	List<Subject> findSubjectByUserId(Integer userId, Integer pageIndex);

  List<Subject> findSubjectsByClass(Clazz clazz);

}

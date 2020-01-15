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
<<<<<<< HEAD

  List<Subject> findSubjectsByClass(Clazz clazz);

=======
	
	/**
	 * @author HoangLV7
	 *
	 * @param id
	 * @return change status of subject
	 */
	Subject delSubject(int id);
	
	/**
	 * @author HoangLV7
	 *
	 * @param status
	 * @return get subjects by status
	 */
	List<Subject> findByStatus(String status);
>>>>>>> ffc10e42682fc56e7d4fe93acdb0324f39b6f462
}

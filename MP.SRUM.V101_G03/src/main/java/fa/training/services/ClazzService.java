package fa.training.services;

import java.util.List;

import fa.training.models.Clazz;

public interface ClazzService {
	/**
	 * @return
	 */
	List<String> findAllClazzCategory();

	/**
	 * @return
	 */
	public List<String> findAllClazzName();

	/**
	 * @author ToanNT18
	 * @param userId
	 * @return List<Clazz>
	 * @return all clazz by trainer's id
	 */
	List<Clazz> findAllClazzByTrainerId(Integer userId, Integer pageIndex);

	/**
	 * @author ToanNT18
	 * @param clazzName
	 * @return find all clazz by clazz's name or category
	 */
	List<Clazz> findClazzByCategory(Integer userId, Integer pageIndex, String contentSearch);

	/**
	 * @author ToanNT18
	 * @param clazzName
	 * @return find all clazz by clazz's name or category
	 */
	List<Clazz> findClazzByNameOrCategory(Integer userId, Integer pageIndex, String contentSearch);

	/**
	 * @author ToanNT18
	 * @param userId
	 * @param status
	 * @return all clazz by trainer's status
	 */
	List<Clazz> findClazzByStatus(Integer userId, Integer pageIndex, String status);

	/**
	 * @author ToanNT18
	 * @param userId
	 * @param status
	 * @param contentSearch
	 * @return find all clazz which is found by trainee id, class's status and
	 *         contentSearch
	 */
	List<Clazz> findClazzByStatusAndContent(Integer userId, Integer pageIndex, String status, String contentSearch);
}

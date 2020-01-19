package fa.training.services.implement;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fa.training.models.Clazz;
import fa.training.models.User;
import fa.training.repositories.ClassRepository;
import fa.training.services.ClazzService;
import fa.training.utils.Constant;

/**
 *
 * @author ToanNT18
 */
@Service
@Transactional
public class ClazzServiceImpl implements ClazzService {
	public static int totalPage;
	@Autowired
	private ClassRepository clazzRepository;

	@Override
	public List<String> findAllClazzCategory() {
		return clazzRepository.findAllCategory();
	}

	@Override
	public List<String> findAllClazzName() {
		return clazzRepository.findAllClazzName();
	}

	@Override
	public List<Clazz> findAllClazzByTrainerId(Integer userId, Integer pageIndex) {
		Page<Clazz> pageClazz = clazzRepository.findAllClazz(userId, PageRequest.of(pageIndex, Constant.PAGE_SIZE));
		totalPage = pageClazz.getTotalPages();
		return pageClazz.getContent();
	}

	@Override
	public List<Clazz> findClazzByCategory(Integer userId, Integer pageIndex, String category) {

		Page<Clazz> pageClazz = clazzRepository.findClazzByCategory(userId, category,
				PageRequest.of(pageIndex - 1, Constant.PAGE_SIZE));
		totalPage = pageClazz.getTotalPages();
		return pageClazz.getContent();
	}

	@Override
	public List<Clazz> findClazzByNameOrCategory(Integer userId, Integer pageIndex, String contentSearch) {
		Page<Clazz> pageClazz = clazzRepository.findClazzByNameOrCategory(userId, contentSearch,
				PageRequest.of(pageIndex - 1, Constant.PAGE_SIZE));
		totalPage = pageClazz.getTotalPages();
		return pageClazz.getContent();
	}

	@Override
	public List<Clazz> findClazzByStatus(Integer userId, Integer pageIndex, String status) {
		Page<Clazz> pageClazz = clazzRepository.findClazzByStatus(userId, status,
				PageRequest.of(pageIndex - 1, Constant.PAGE_SIZE));
		totalPage = pageClazz.getTotalPages();
		return pageClazz.getContent();
	}

	@Override
	public List<Clazz> findClazzByStatusAndContent(Integer userId, Integer pageIndex, String status,
			String contentSearch) {
		Page<Clazz> pageClazz = clazzRepository.findClazzByStatusAndContent(userId, status, contentSearch,
				PageRequest.of(pageIndex - 1, Constant.PAGE_SIZE));
		totalPage = pageClazz.getTotalPages();
		return pageClazz.getContent();
	}

	/**
	 * @author HoangLV7
	 */
	@Override
	public List<Clazz> findBySubject(int subjectId) {
		String status = Constant.CLASS_ACTIVE_STATUS;
		return clazzRepository.findBySubject(status, subjectId);
	}

	/**
	 * @author HoangLV7
	 */
	@Override
	public Clazz findClazzByTrainee(User trainee, String role) {
		return (trainee != null && trainee.getId() != null)
				? clazzRepository.findClazzByUser(trainee.getId(), role).orElse(new Clazz())
				: new Clazz();

	}
}
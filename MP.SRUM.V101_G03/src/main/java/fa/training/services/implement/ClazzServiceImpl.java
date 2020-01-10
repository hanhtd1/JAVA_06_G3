package fa.training.services.implement;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fa.training.models.Clazz;
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
	@Autowired
	private ClassRepository clazzRepository;

	@Override
	public List<Clazz> findAllClazzByTrainerId(Integer userId, Integer pageIndex) {
		return clazzRepository.findAllClazz(userId, PageRequest.of(pageIndex, Constant.PAGE_SIZE)).getContent();
	}
	
	@Override
	public List<Clazz> findClazzByCategory(Integer userId, Integer pageIndex, String category) {
		return clazzRepository.findClazzByCategory(userId, category, PageRequest.of(pageIndex, Constant.PAGE_SIZE))
				.getContent();
	}

	@Override
	public List<Clazz> findClazzByNameOrCategory(Integer userId, Integer pageIndex, String contentSearch) {
		return clazzRepository
				.findClazzByNameOrCategory(userId, contentSearch, PageRequest.of(pageIndex, Constant.PAGE_SIZE))
				.getContent();
	}

	@Override
	public List<Clazz> findClazzByStatus(Integer userId, Integer pageIndex, String status) {
		return clazzRepository.findClazzByStatus(userId, status, PageRequest.of(pageIndex, Constant.PAGE_SIZE))
				.getContent();
	}

	@Override
	public List<Clazz> findClazzByStatusAndContent(Integer userId, Integer pageIndex, String status,
			String contentSearch) {
		return clazzRepository.findClazzByStatusAndContent(userId, status, contentSearch,
				PageRequest.of(pageIndex, Constant.PAGE_SIZE)).getContent();
	}
}
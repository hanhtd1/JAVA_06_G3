package fa.training.services.implement;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fa.training.models.User;
import fa.training.repositories.TraineeRepository;
import fa.training.services.TraineeService;
import fa.training.utils.Constant;

/**
 *
 * @author ToanNT18
 */
@Service
@Transactional
public class TraineeServiceImpl implements TraineeService {

	public static int numberOfPage = 0;
	@Autowired
	private TraineeRepository traineeRepository;

	@Override
	public List<String> findAllTraineeStatus() {
		return traineeRepository.findAllTraineeStatus();
	}

	@Override
	public User findTraineeById(Integer id) {
		return traineeRepository.findById(id).orElse(new User());
	}

	@Override
	public List<User> findAllTrainee() {
		Page<User> users = traineeRepository.findAllByRole(Constant.TRAINER,
				PageRequest.of(Constant.FIRST_PAGE, Constant.PAGE_SIZE));
		numberOfPage = users.getTotalPages();
		return users.getContent();
	}

	@Override
	public List<User> findTraineeByCategory(String category, Integer pageIndex) {
		Page<User> users = traineeRepository.findTraineeByCategory(category,
				PageRequest.of(pageIndex, Constant.PAGE_SIZE));
		numberOfPage = users.getTotalPages();
		return users.getContent();
	}

	@Override
	public List<User> findTraineeByClazzName(String clazzName, Integer pageIndex) {
		Page<User> users = traineeRepository.findTraineeByClazzName(clazzName,
				PageRequest.of(pageIndex, Constant.PAGE_SIZE));
		numberOfPage = users.getTotalPages();
		return users.getContent();
	}

	@Override
	public List<User> findTraineeByStatus(String status, Integer pageIndex) {
		Page<User> users = traineeRepository.findTraineeByStatus(status, PageRequest.of(pageIndex, Constant.PAGE_SIZE));
		numberOfPage = users.getTotalPages();
		return users.getContent();
	}

	@Override
	public List<User> findTraineeByCategoryAndClazz(String category, String clazzName, Integer pageIndex) {
		Page<User> users = traineeRepository.findTraineeByCategoryAndClazz(category, clazzName,
				PageRequest.of(pageIndex, Constant.PAGE_SIZE));
		numberOfPage = users.getTotalPages();
		return users.getContent();
	}

	@Override
	public List<User> findTraineeByCategoryAndStatus(String category, String status, Integer pageIndex) {
		Page<User> users = traineeRepository.findTraineeByCategoryAndStatus(category, status,
				PageRequest.of(pageIndex, Constant.PAGE_SIZE));
		numberOfPage = users.getTotalPages();
		return users.getContent();
	}

	@Override
	public List<User> findTraineeByClazzAndStatus(String clazzName, String status, Integer pageIndex) {
		Page<User> users = traineeRepository.findTraineeByClazzAndStatus(clazzName, status,
				PageRequest.of(pageIndex, Constant.PAGE_SIZE));
		numberOfPage = users.getTotalPages();
		return users.getContent();
	}

	@Override
	public List<User> findTraineeByCategoryAndClazzAndStatus(String category, String clazzName, String status,
			Integer pageIndex) {
		Page<User> users = traineeRepository.findTraineeByCategoryAndClazzAndStatus(category, clazzName, status,
				PageRequest.of(pageIndex, Constant.PAGE_SIZE));
		numberOfPage = users.getTotalPages();
		return users.getContent();
	}
}

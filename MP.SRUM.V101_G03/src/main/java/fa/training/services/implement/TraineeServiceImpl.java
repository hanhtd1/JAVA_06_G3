package fa.training.services.implement;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private TraineeRepository traineeRepository;

	@Override
	public User findTraineeById(Integer id) {
		return traineeRepository.findById(id).orElse(new User());
	}

	@Override
	public List<User> findAllTrainee() {
		return traineeRepository.findAllByRole(Constant.ROLE_TRAINER, PageRequest.of(0, Constant.PAGE_SIZE))
				.getContent();
	}

	@Override
	public List<User> findTraineeByClazz(Integer clazzId, Integer pageIndex) {
		return traineeRepository.findTraineeByClazzId(clazzId, PageRequest.of(pageIndex, Constant.PAGE_SIZE))
				.getContent();
	}

	@Override
	public List<User> findTraineeByCategory(String category, String role, Integer pageIndex) {
		return traineeRepository.findTraineeByCategory(category, role, PageRequest.of(pageIndex, Constant.PAGE_SIZE))
				.getContent();
	}
}

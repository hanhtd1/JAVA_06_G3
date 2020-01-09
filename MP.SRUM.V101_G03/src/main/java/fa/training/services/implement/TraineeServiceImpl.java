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
		return traineeRepository.findTraineeByUserId(id).get(0);
	}

	@Override
	public List<User> findAllTrainee() {
		return traineeRepository.findTop10Trainee("Trainee");
	}

	@Override
	public List<User> findTraineeByClazz(Integer clazzId, Integer pageIndex) {
		return traineeRepository.findTraineeByClazzId(clazzId, PageRequest.of(pageIndex, Constant.PAGE_SIZE));
	}

	@Override
	public List<User> findTraineeByCategory(String category, Integer pageIndex) {
		return traineeRepository.findTraineeByCategory(category, PageRequest.of(pageIndex, Constant.PAGE_SIZE));
	}

}

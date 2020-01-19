package fa.training.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import fa.training.models.User;
import fa.training.repositories.TraineeRepository;
import fa.training.utils.Constant;

@RunWith(SpringRunner.class)
public class TraineeRepositoryTests {
	@Mock
	private TraineeRepository traineeRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAllTraineeStatusTest() {
		List<String> status = traineeRepository.findAllTraineeStatus();
		assertTrue(status != null);
	}

	@Test
	public void findByIdTest() {
		Page<User> pageUser = traineeRepository.findAllByRole(Constant.TRAINEE,
				PageRequest.of(Constant.FIRST_RESULT, Constant.PAGE_SIZE));
		assertTrue(pageUser == null);
	}

	@Test
	public void findAllByRoleTest() {
		Page<User> pageUser = traineeRepository.findTraineeByCategory(Constant.CLAZZ_CATEGORY,
				PageRequest.of(Constant.FIRST_RESULT, Constant.PAGE_SIZE));
		assertTrue(pageUser == null);
	}

	@Test
	public void findTraineeByClazzNameTest() {
		Page<User> pageUser = traineeRepository.findTraineeByClazzName("FRHN_JAVA04",
				PageRequest.of(Constant.FIRST_RESULT, Constant.PAGE_SIZE));
		assertTrue(pageUser == null);
	}

	@Test
	public void findTraineeByClazzIdTest() {
		Page<User> pageUser = traineeRepository.findTraineeByClazzId(Constant.USER_ID_DEFAULT,
				PageRequest.of(Constant.FIRST_RESULT, Constant.PAGE_SIZE));
		assertTrue(pageUser == null);
	}

	@Test
	public void findTraineeByStatusTest() {
		Page<User> pageUser = traineeRepository.findTraineeByStatus(Constant.TRAINEE_ACTIVE_STATUS,
				PageRequest.of(Constant.FIRST_RESULT, Constant.PAGE_SIZE));
		assertTrue(pageUser == null);
	}

	@Test
	public void findTraineeByCategoryAndClazzTest() {
		Page<User> pageUser = traineeRepository.findTraineeByCategoryAndClazz(Constant.CLAZZ_CATEGORY, "FRHN_JAVA04",
				PageRequest.of(Constant.FIRST_RESULT, Constant.PAGE_SIZE));
		assertTrue(pageUser == null);
	}

	@Test
	public void findTraineeByCategoryAndStatusTest() {
		Page<User> pageUser = traineeRepository.findTraineeByCategoryAndStatus(Constant.CLAZZ_CATEGORY,
				Constant.CLASS_ACTIVE_STATUS, PageRequest.of(Constant.FIRST_RESULT, Constant.PAGE_SIZE));
		assertTrue(pageUser == null);
	}

	@Test
	public void findTraineeByClazzAndStatusTest() {
		Page<User> pageUser = traineeRepository.findTraineeByClazzAndStatus("FRHN_JAVA04", Constant.CLASS_ACTIVE_STATUS,
				PageRequest.of(Constant.FIRST_RESULT, Constant.PAGE_SIZE));
		assertTrue(pageUser == null);
	}

	@Test
	public void findTraineeByCategoryAndClazzAndStatusTest() {
		Page<User> pageUser = traineeRepository.findTraineeByCategoryAndClazzAndStatus(Constant.CLAZZ_CATEGORY,
				"FRHN_JAVA04", Constant.CLASS_ACTIVE_STATUS, PageRequest.of(Constant.FIRST_RESULT, Constant.PAGE_SIZE));
		assertTrue(pageUser == null);
	}

	@After
	public void tearDown() throws Exception {

	}

}

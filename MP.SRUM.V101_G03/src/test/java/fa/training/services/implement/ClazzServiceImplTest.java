package fa.training.services.implement;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fa.training.models.Clazz;
import fa.training.models.User;
import fa.training.repositories.ClassRepository;
import fa.training.utils.Constant;

public class ClazzServiceImplTest {

	@Mock
	private ClassRepository classRepository;

	@InjectMocks
	private ClazzServiceImpl clazzServiceImpl;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * @author HoangLV7
	 * 
	 *         Test function with valid param
	 */
	@Test
	public void test_findClazzByTrainee_00() {
		when(classRepository.findClazzByUser(anyInt(), anyString())).thenReturn(Optional.of(new Clazz()));
		User user = new User((int) Math.random());
		assertThat(clazzServiceImpl.findClazzByTrainee(user, Constant.TRAINEE), is(notNullValue()));
	}

	/**
	 * @author HoangLV7
	 * 
	 *         Test function with role null
	 */
	@Test
	public void test_findClazzByTrainee_01() {
		when(classRepository.findClazzByUser(anyInt(), anyString())).thenReturn(Optional.of(new Clazz()));
		User user = new User((int) Math.random());
		assertThat(clazzServiceImpl.findClazzByTrainee(user, null), is(notNullValue()));
	}

	/**
	 * @author HoangLV7
	 * 
	 *         Test function with trainee null
	 */
	@Test
	public void test_findClazzByTrainee_02() {
		when(classRepository.findClazzByUser(anyInt(), anyString())).thenReturn(Optional.of(new Clazz()));
		assertThat(clazzServiceImpl.findClazzByTrainee(null, Constant.TRAINEE), is(notNullValue()));
	}

	/**
	 * @author HoangLV7
	 * 
	 *         Test function with traineeId null
	 */
	@Test
	public void test_findClazzByTrainee_03() {
		when(classRepository.findClazzByUser(anyInt(), anyString())).thenReturn(Optional.of(new Clazz()));
		User user = new User(null);
		assertThat(clazzServiceImpl.findClazzByTrainee(user, Constant.TRAINEE), is(notNullValue()));
	}

	/**
	 * @author HoangLV7
	 * 
	 *         Test function with trainee null and role null
	 */
	@Test
	public void test_findClazzByTrainee_04() {
		when(classRepository.findClazzByUser(anyInt(), anyString())).thenReturn(Optional.of(new Clazz()));
		assertThat(clazzServiceImpl.findClazzByTrainee(null, null), is(notNullValue()));
	}

	/**
	 * @author HoangLV7
	 * 
	 *         Test function with valid param
	 */
	@Test
	public void test_findBySubject_00() {
		when(classRepository.findBySubject(anyString(), anyInt())).thenReturn(new ArrayList<Clazz>());
		assertThat(clazzServiceImpl.findBySubject((int) Math.random()), is(notNullValue()));
	}

}

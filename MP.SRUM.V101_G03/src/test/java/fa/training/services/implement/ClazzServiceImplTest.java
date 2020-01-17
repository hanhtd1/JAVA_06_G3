package fa.training.services.implement;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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

	@Test
	public void test_findClazzByTrainee() {
//		when(classRepository.findClazzByTrainee(anyInt())).thenReturn(Optional.of(new Clazz()));
		User user = new User((int)Math.random());
		assertThat(clazzServiceImpl.findClazzByTrainee(user, Constant.TRAINEE), is(notNullValue()));
	}

}

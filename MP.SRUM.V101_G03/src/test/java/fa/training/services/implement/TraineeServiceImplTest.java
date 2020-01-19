package fa.training.services.implement;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doAnswer;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import fa.training.repositories.ClassRepository;

public class TraineeServiceImplTest {
	@Mock
	private ClassRepository classRepository;

	@Mock
	private ClazzServiceImpl clazzServiceImpl;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void findAllClazzCategoryTest() {
		doAnswer(new Answer<List<String>>() {
			@Override
			public List<String> answer(InvocationOnMock invocation) throws Throwable {
				List<String> fakeStatus = new ArrayList<>();
				fakeStatus.add("Active");
				fakeStatus.add("Inactive");
				return fakeStatus;
			}
		}).when(classRepository).findAllCategory();
	}

	@After
	public void tearDown() throws Exception {
		List<String> categories = clazzServiceImpl.findAllClazzCategory();
		assertNotNull(categories);
	}

}

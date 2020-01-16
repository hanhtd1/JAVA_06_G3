package fa.training.services.implement;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import fa.training.repositories.SubjectRepository;

public class SubjectServiceImplTest {
	
	@Mock
	private SubjectRepository subjectRepository;
	
	@InjectMocks
	private SubjectServiceImpl subjectServiceImpl;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}

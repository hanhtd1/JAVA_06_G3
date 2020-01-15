package fa.training.services.implement;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fa.training.repositories.AttendanceRepository;

public class AttendanceServiceImplTest {
	
	@Mock
	private AttendanceRepository attendanceRepository;
	
	@InjectMocks
	private AttendanceServiceImpl attendanceServiceImpl;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}

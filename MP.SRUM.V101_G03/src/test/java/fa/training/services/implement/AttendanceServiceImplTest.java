package fa.training.services.implement;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fa.training.models.Attendance;
import fa.training.models.User;
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

	/**
	 * @author HoangLV7
	 * 
	 * Test function with user null return new ArrayList
	 */
	@Test
	public void test_getAttendancesByUser_00() {
		when(attendanceRepository.findAttendanceByUser(anyInt())).thenReturn(new ArrayList<Attendance>());
		assertNotNull(attendanceServiceImpl.getAttendancesByUser(null));
	}

	/**
	 * @author HoangLV7
	 * 
	 * Test function with userId null return new ArrayList
	 */
	@Test
	public void test_getAttendancesByUser_01() {
		when(attendanceRepository.findAttendanceByUser(anyInt())).thenReturn(new ArrayList<Attendance>());
		User user = new User();
		assertNotNull(attendanceServiceImpl.getAttendancesByUser(user));
	}

	/**
	 * @author HoangLV7
	 * 
	 * Test function with userId random return new ArrayList
	 */
	@Test
	public void test_getAttendancesByUser_02() {
		when(attendanceRepository.findAttendanceByUser(anyInt())).thenReturn(new ArrayList<Attendance>());
		User user = new User((int) Math.random());
		assertNotNull(attendanceServiceImpl.getAttendancesByUser(user));
	}

}

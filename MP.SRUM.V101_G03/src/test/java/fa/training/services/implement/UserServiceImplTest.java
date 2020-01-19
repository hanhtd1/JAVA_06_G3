package fa.training.services.implement;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fa.training.models.User;
import fa.training.repositories.UserRepository;

public class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

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
	 *         test function with valid param
	 */
	@Test
	public void test_getUserById_00() {
		when(userRepository.findUserById(anyInt())).thenReturn(Optional.of(new User()));
		assertNotNull(userServiceImpl.getUserById((int) Math.random()));
	}

	/**
	 * @author HoangLV7
	 * 
	 *         test function with valid param
	 */
	@Test
	public void test_getUser_00() {
		when(userRepository.findByAccount(anyString())).thenReturn(Optional.of(new User()));
		assertNotNull(userServiceImpl.getUser(""));
	}

	/**
	 * @author HoangLV7
	 * 
	 *         test function with account null
	 */
	@Test
	public void test_getUser_01() {
		when(userRepository.findByAccount(anyString())).thenReturn(Optional.of(new User()));
		assertNotNull(userServiceImpl.getUser(null));
	}

	/**
	 * @author HoangLV7
	 * 
	 *         Test function with valid param
	 */
	@Test
	public void test_getMembers_00() {
		when(userRepository.getMembers(anyString(), anyString(), any(User.class))).thenReturn(new ArrayList<User>());
		assertThat(userServiceImpl.getMembers(any(User.class)), is(notNullValue()));
	}

	/**
	 * @author HoangLV7
	 * 
	 *         Test function with user null
	 */
	@Test
	public void test_getMembers_01() {
		when(userRepository.getMembers(anyString(), anyString(), any(User.class))).thenReturn(new ArrayList<User>());
		assertThat(userServiceImpl.getMembers(null), is(notNullValue()));
	}

	/**
	 * @author HoangLV7
	 * 
	 *         Test function with userId null
	 */
	@Test
	public void test_getMembers_02() {
		when(userRepository.getMembers(anyString(), anyString(), any(User.class))).thenReturn(new ArrayList<User>());
		User user = new User(null);
		assertThat(userServiceImpl.getMembers(user), is(notNullValue()));
	}

}

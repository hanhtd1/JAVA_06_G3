package fa.training.services.implement;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fa.training.models.Subject;
import fa.training.repositories.SubjectRepository;

public class SubjectServiceImplTest {

	@Mock
	private SubjectRepository subjectRepository;

	@InjectMocks
	private SubjectServiceImpl subjectServiceImpl;

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
	 */
	@Test
	public void test_findAll_00() {
		when(subjectRepository.findByStatus(anyString())).thenReturn(new ArrayList<Subject>());
		assertNotNull(subjectServiceImpl.findAll());
	}

	/**
	 * @author HoangLV7
	 * 
	 *         test function with valid param
	 */
	@Test
	public void test_findSubjectByCode_00() {
		when(subjectRepository.findSubjectByCode(anyString())).thenReturn(Optional.of(new Subject()));
		assertNotNull(subjectServiceImpl.findSubjectByCode(""));

	}

	/**
	 * @author HoangLV7
	 * 
	 *         test function with code null
	 */
	@Test
	public void test_findSubjectByCode_01() {
		when(subjectRepository.findSubjectByCode(anyString())).thenReturn(Optional.of(new Subject()));
		assertNotNull(subjectServiceImpl.findSubjectByCode(null));

	}

	/**
	 * @author HoangLV7
	 * 
	 *         test function with valid param
	 */
	@Test
	public void test_checkSubjectExisted_00() {
		when(subjectRepository.findSubjectByCode(anyString())).thenReturn(Optional.of(new Subject()));
		assertFalse(subjectServiceImpl.checkSubjectExisted("1"));

	}

	/**
	 * @author HoangLV7
	 * 
	 *         test function with code null
	 */
	@Test
	public void test_checkSubjectExisted_01() {
		when(subjectRepository.findSubjectByCode(anyString())).thenReturn(Optional.of(new Subject()));
		assertFalse(subjectServiceImpl.checkSubjectExisted(null));

	}

	/**
	 * @author HoangLV7
	 * 
	 *         test function with valid param
	 */
	@Test
	public void test_findSubjectById_00() {
		when(subjectRepository.findSubjectById(anyInt())).thenReturn(Optional.of(new Subject()));
		assertNotNull(subjectServiceImpl.findSubjectById((int) Math.random()));

	}

	/**
	 * @author HoangLV7
	 * 
	 *         test function with valid param
	 */
	@Test
	public void test_findByStatus_00() {
		when(subjectRepository.findSubjectByStatus(anyString())).thenReturn(new ArrayList<Subject>());
		assertNotNull(subjectServiceImpl.findByStatus(""));

	}

	/**
	 * @author HoangLV7
	 * 
	 *         test function with code null
	 */
	@Test
	public void test_findByStatus_01() {
		when(subjectRepository.findSubjectByStatus(anyString())).thenReturn(new ArrayList<Subject>());
		assertNotNull(subjectServiceImpl.findByStatus(null));

	}

}

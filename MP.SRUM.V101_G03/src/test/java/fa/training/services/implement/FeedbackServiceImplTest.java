package fa.training.services.implement;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fa.training.models.Feedback;
import fa.training.repositories.FeedbackRepository;

public class FeedbackServiceImplTest {

	@Mock
	private FeedbackRepository feedbackRepository;

	@InjectMocks
	private FeedbackServiceImpl feedbackServiceImpl;

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
	public void test_save_00() {
		Feedback feedback = new Feedback();
		when(feedbackRepository.save(feedback)).thenReturn(new Feedback());
		assertNotNull(feedbackServiceImpl.save(feedback));
	}

	/**
	 * @author HoangLV7
	 * 
	 *         test function with feedback null
	 */
	@Test
	public void test_save_01() {
		Feedback feedback = new Feedback();
		when(feedbackRepository.save(feedback)).thenReturn(new Feedback());
		assertNotNull(feedbackServiceImpl.save(null));
	}

	/**
	 * @author HoangLV7
	 * 
	 *         test function with valid param
	 */
	@Test
	public void test_getFeedback_00() {
		when(feedbackRepository.findByUserIdAndSubjectId(anyInt(), anyInt())).thenReturn(Optional.of(new Feedback()));
		assertNotNull(feedbackServiceImpl.getFeedback((int) Math.random(), (int) Math.random()));
	}
}

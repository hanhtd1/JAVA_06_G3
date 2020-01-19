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

	@Test
	public void test_save() {
//		when(feedbackRepository.save(any(Feedback.class))).thenReturn(new Feedback());
	}

	@Test
	public void test_findByUserIdAndSubjectId() {
		when(feedbackRepository.findByUserIdAndSubjectId(anyInt(), anyInt())).thenReturn(Optional.of(new Feedback()));
		assertThat(feedbackServiceImpl.getAllFeedback((int)Math.random(), (int)Math.random()), is(notNullValue()));
	}
}

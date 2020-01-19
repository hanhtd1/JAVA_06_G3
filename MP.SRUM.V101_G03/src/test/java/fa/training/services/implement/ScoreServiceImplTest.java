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

import fa.training.models.Score;
import fa.training.repositories.ScoreRepository;

public class ScoreServiceImplTest {
	@Mock
	private ScoreRepository scoreRepository;
	
	@InjectMocks
	private ScoreServiceImpl scoreServiceImpl;

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
	public void testGetScoreByUser_00() {
		when(scoreRepository.findAllScoreByUserId(anyInt())).thenReturn(new ArrayList<Score>());
		assertNotNull(scoreServiceImpl.getScoreByUser((int)Math.random()));
	}
	
	/**
	 * @author HoangLV7
	 * 
	 *         test function with userId null
	 */
	@Test
	public void testGetScoreByUser_01() {
		when(scoreRepository.findAllScoreByUserId(anyInt())).thenReturn(new ArrayList<Score>());
		assertNotNull(scoreServiceImpl.getScoreByUser(null));
	}

}

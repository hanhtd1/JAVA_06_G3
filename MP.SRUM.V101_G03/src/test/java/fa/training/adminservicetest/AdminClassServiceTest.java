package fa.training.adminservicetest;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import fa.training.models.Clazz;
import fa.training.repositories.ClassRepository;
import fa.training.repositories.SubjectRepository;
import fa.training.repositories.UserRepository;
import fa.training.services.implement.AdminClassServiceImpl;

/**
 * @author TrangDM2
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AdminClassServiceTest {

  @Mock
  private ClassRepository classRepository;

  @Mock
  private UserRepository userRepository;

  @Mock
  private SubjectRepository subjectRepository;
  
  @InjectMocks
  private AdminClassServiceImpl adminClassService;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @After
  public void tearDown() throws Exception {
  }

  /**
   * @author TrangDM2
   */
  @Test
  public void testSaveClassSuccess() {
    Clazz clazz = new Clazz();
    when(adminClassService.saveClass(clazz, clazz.getStatus())).thenReturn(new Clazz());
    assertNotNull(adminClassService.saveClass(clazz, clazz.getStatus()));
  }

  /**
   * @author TrangDM2
   */
  @Test(expected = NullPointerException.class)
  public void testSaveClassFail() {
    Clazz clazz = new Clazz();
    when(adminClassService.saveClass(clazz, clazz.getStatus())).thenReturn(new Clazz());
    assertNotNull(adminClassService.saveClass(null, null));
  }
  
  @Test
  public void testAddUserToClassSuccess() {
    when(adminClassService.addSubjectToClass(anyInt(), anyInt())).thenReturn(new Clazz());
    assertNotNull(adminClassService.addUserToClass(anyInt(), anyInt()));
  }

}

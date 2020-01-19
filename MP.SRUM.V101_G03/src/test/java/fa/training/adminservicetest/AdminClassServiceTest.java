package fa.training.adminservicetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import fa.training.models.Clazz;
import fa.training.repositories.ClassRepository;
import fa.training.services.implement.AdminClassServiceImpl;

/**
 * @author TrangDM2
 *
 */
@RunWith(SpringRunner.class)
public class AdminClassServiceTest {

  @Mock
  private ClassRepository classRepository;

  @InjectMocks
  private AdminClassServiceImpl adminClassService;

  @Before
  public static void setUp() throws Exception {
  }

  /**
   * @author TrangDM2
   */
  @Test
  public void testSaveClassSuccess() {
    Clazz clazz = new Clazz(null, "JAVA", LocalDate.now(), "new class", "java", "Active");
    when(adminClassService.saveClass(clazz, clazz.getStatus())).thenReturn(new Clazz());
    assertEquals(clazz, adminClassService.saveClass(clazz, clazz.getStatus()));
  }

  /**
   * @author TrangDM2
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSaveClassFail() {
    Clazz clazz = new Clazz();
//    doThrow(new IllegalArgumentException()).when(classRepository).save(clazz);
    when(adminClassService.saveClass(clazz, clazz.getStatus())).thenReturn(null);
    assertEquals(clazz, adminClassService.saveClass(clazz, clazz.getStatus()));
  }

}

package fa.training.adminservicetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import fa.training.models.Clazz;
import fa.training.repositories.ClassRepository;
import fa.training.services.implement.AdminClassServiceImpl;

/**
 * @author TrangDM2
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AdminClassServiceTest {

  @Mock
  private ClassRepository classRepository;

  @InjectMocks
  private AdminClassServiceImpl adminClassService;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
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

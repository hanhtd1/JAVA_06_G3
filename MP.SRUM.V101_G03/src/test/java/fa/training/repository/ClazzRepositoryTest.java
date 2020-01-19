package fa.training.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import fa.training.models.Clazz;
import fa.training.repositories.ClassRepository;
import fa.training.utils.Constant;

public class ClazzRepositoryTest {
	@Mock
	private ClassRepository classRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAllClazzTest() {
		Page<Clazz> pageClazz = classRepository.findAllClazz(Constant.USER_ID_DEFAULT,
				PageRequest.of(Constant.FIRST_RESULT, Constant.PAGE_SIZE));
		assertTrue(pageClazz == null);
	}

	@Test
	public void findClazzByCategoryTest() {
		Page<Clazz> pageClazz = classRepository.findClazzByCategory(Constant.USER_ID_DEFAULT, Constant.CLAZZ_CATEGORY,
				PageRequest.of(Constant.FIRST_RESULT, Constant.PAGE_SIZE));
		assertTrue(pageClazz == null);
	}

	@Test
	public void findClazzByNameOrCategoryTest() {
		Page<Clazz> pageClazz = classRepository.findClazzByNameOrCategory(Constant.USER_ID_DEFAULT,
				Constant.CLAZZ_CATEGORY, PageRequest.of(Constant.FIRST_RESULT, Constant.PAGE_SIZE));
		assertTrue(pageClazz == null);
	}

	@Test
	public void findClazzByStatusTest() {
		Page<Clazz> pageClazz = classRepository.findClazzByStatus(Constant.USER_ID_DEFAULT,
				Constant.CLASS_ACTIVE_STATUS, PageRequest.of(Constant.FIRST_RESULT, Constant.PAGE_SIZE));
		assertTrue(pageClazz == null);
	}

	@Test
	public void findClazzByStatusAndContentTest() {
		Page<Clazz> pageClazz = classRepository.findClazzByStatusAndContent(Constant.USER_ID_DEFAULT,
				Constant.CLASS_ACTIVE_STATUS, Constant.CLAZZ_CATEGORY,
				PageRequest.of(Constant.FIRST_RESULT, Constant.PAGE_SIZE));
		assertTrue(pageClazz == null);
	}

}

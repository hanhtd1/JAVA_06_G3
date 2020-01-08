package fa.training.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fa.training.models.Clazz;

/**
 * @author TrangDM2
 *
 */
@Service
public interface IAdminClassService {

  List<Clazz> getClasses();

  Clazz getClass(Integer id);

}

package fa.training.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.models.Clazz;
import fa.training.repositories.ClassRepository;
import fa.training.services.AdminClassService;


/**
 * @author TrangDM2
 *
 */
@Service
public class AdminClassServiceImpl implements AdminClassService {
  
  @Autowired
  private ClassRepository classRepository;
  
  @Override
  public List<Clazz> getClasses(){
    return classRepository.findAll();
  }
}

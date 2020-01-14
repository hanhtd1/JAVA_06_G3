package fa.training.services.implement;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.models.Clazz;
import fa.training.repositories.ClassRepository;
import fa.training.services.IAdminClassService;


/**
 * @author TrangDM2
 *
 */
@Service
public class AdminClassService implements IAdminClassService {
  
  @Autowired
  private ClassRepository classRepository;

  /**
   *@author TrangDM2
   */
  @Override
  public Clazz saveClass(Clazz clazz) {
      return classRepository.save(clazz);
  }
  /**
   *@author TrangDM2
   */
  @Override
  public List<Clazz> getClasses(){
    return classRepository.findAll();
  }
  
  /**
   * @author TrangDM2
   */
  @Override
  public List<Clazz> findClazzByKeyword(String keyword, String status){
    return classRepository.findClassByKeyword("%"+keyword+"%", "%"+status+"%");
  }
  
  /**
   * @author TrangDM2
   * @param 
   * @return
   */
  @Override
  public String generateClassName(String location, String type, String category) {
    String y =  Integer.toString(LocalDate.now().getYear());
    String year = y.substring(2, 4);
    StringBuilder clazzName=new StringBuilder();
    
    clazzName.append(location);
    clazzName.append(year);
    clazzName.append("_");
    clazzName.append(type);
    clazzName.append("_");
    clazzName.append(category.toUpperCase());
    clazzName.append("_");
    
    int count = classRepository.getClassNumber(clazzName+"%")+1;
    if(count<10) {
      clazzName.append("0");
    }
    
    clazzName.append(count);
    return clazzName.toString();
  }
  /**
   *@author TrangDM2
   */
  @Override
  public Clazz getClass(Integer id) {
    return classRepository.findById(id).get();
  }
  
  /**
   *@author TrangDM2
   */
  @Override
  public Clazz getClassByName(String name) {
    return classRepository.findByName(name);
  }
}

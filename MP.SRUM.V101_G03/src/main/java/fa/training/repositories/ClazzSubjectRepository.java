package fa.training.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.training.models.Clazz;
import fa.training.models.ClazzSubject;
import fa.training.models.Subject;

/**
 * @author TrangDM2
 *
 */
@Repository
public interface ClazzSubjectRepository extends JpaRepository<ClazzSubject, Integer>{
   
  /**
   * @author TrangDM2
   * @param clazz
   * @return
   */
  @Query("select s from Subject s where s in (select cs.subject from ClazzSubject cs where cs.clazz= :clazz)")
  List<Subject> findSubjectByClazz(@Param("clazz") Clazz clazz);
}

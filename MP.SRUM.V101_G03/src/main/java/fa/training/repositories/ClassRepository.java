package fa.training.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.training.models.Clazz;

@Repository
public interface ClassRepository extends JpaRepository<Clazz, Integer> {
  
  /**
   * @author TrangDM2
   * @return
   */
  @Query("select c from Clazz c where (c.name like :keyword or c.category like :keyword) and status like :status")
  List<Clazz> findClassByKeyword(@Param("keyword") String keyword, @Param("status") String status);
  
  /**
   * @author TrangDM2
   * @param clazzName
   * @return
   */
  @Query("select count(c) from Clazz c where c.name like :clazzName")
  int getClassNumber(@Param("clazzName") String clazzName);
  /**
   * @author ToanNT18
   * @param clazzId
   * @param status
   * @param category
   * @return List<Clazz>. Return all of the clazz which trainee participate to
   *         teach.
   */
  @Query(value = "SELECT * FROM udf_findClazzByUserId(:userId)", nativeQuery = true)
  List<Clazz> findAllClazz(@Param("userId") Integer userId, Pageable pageable);

  /**
   * @author ToanNT18
   * @param userId
   * @param category
   * @return Return all of the clazz which user participate to teach following
   *         category.
   */
  @Query(value = "SELECT * FROM [udf_findClazzByCategory](:userId, :category)", nativeQuery = true)
  List<Clazz> findClazzByCategory(@Param("userId") Integer userId, @Param("category") String category,
      Pageable pageable);

  /**
   * @author ToanNT18
   * @param userId
   * @param category
   * @return Return all of the clazz which user participate to teach following
   *         category.
   */
  @Query(value = "SELECT * FROM udf_findClazzByNameOrCategory(:userId, :content)", nativeQuery = true)
  List<Clazz> findClazzByNameOrCategory(@Param("userId") Integer userId, @Param("content") String content,
      Pageable pageable);

  /**
   * @author ToanNT18
   * @param status
   * @return List<Clazz>
   * @return all clazz which is find by user id and status.
   */
  @Query(value = "SELECT * FROM udf_findClazzByStatus(:userId, :status)", nativeQuery = true)
  List<Clazz> findClazzByStatus(@Param("userId") Integer userId, @Param("status") String status, Pageable pageable);

  /**
   * @author ToanNT18
   * @param userId
   * @param status
   * @return
   */
  @Query(value = "SELECT * FROM udf_findClazzByStatusAndNameOrCategory(:userId, :status, :content)", nativeQuery = true)
  List<Clazz> findClazzByStatusAndContent(@Param("userId") Integer userId, @Param("status") String status,
      @Param("content") String content, Pageable pageable);
}

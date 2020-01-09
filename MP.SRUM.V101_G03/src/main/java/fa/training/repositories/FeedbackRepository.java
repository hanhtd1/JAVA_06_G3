package fa.training.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.training.models.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

  /**
   * @author HoangLV7
   * 
   */
  public Feedback findByUserIdAndSubjectId(int userId, int subjectId);

  /**
   * @author ToanNT18
   * @param subjectId
   * @param clazzId
   * @return
   */
  @Query(value = "SELECT * FROM udf_findFeedbackBySubjectAndClazz(:subjectId, :clazzId)", nativeQuery = true)
  List<Feedback> findFeedbackBySubjectAndClazz(@Param("subjectId") Integer subjectId,
      @Param("clazzId") Integer clazzId);

  /**
   * @author ToanNT18
   * @param subjectId
   * @return
   */
  @Query(value = "SELECT * FROM udf_findFeedbackBySubjectId(:subjectId)", nativeQuery = true)
  List<Feedback> findFeedbackBySubjectId(@Param("subjectId") Integer subjectId);
}

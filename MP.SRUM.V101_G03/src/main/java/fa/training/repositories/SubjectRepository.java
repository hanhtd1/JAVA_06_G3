package fa.training.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.training.models.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
	/**
	 * @author ToanNT18
	 * @param clazzId
	 * @return
	 */
	@Query(value = "SELECT * FROM udf_findSubjectByClazz(:clazzId)", nativeQuery = true)
	Subject findSubjectByClazzId(@Param("clazzId") Integer clazzId);

	/**
	 * @author ToanNT18
	 * @param clazzId
	 * @return
	 */
	@Query(value = "SELECT * FROM udf_findSubjectByUser(:userId)", nativeQuery = true)
	Page<Subject> findSubjectByUserId(@Param("userId") Integer userId, Pageable pageable);
}

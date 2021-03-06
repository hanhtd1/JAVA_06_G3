package fa.training.repositories;

import java.util.List;
import java.util.Optional;

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
	Page<Subject> findSubjectByClazzId(@Param("clazzId") Integer clazzId, Pageable pageable);
	
	/**
	 * @author HoangLV7
	 */
	List<Subject> findByStatus(String status);

	/**
	 * @author HoangLV7
	 *
	 * @param code
	 * @return
	 */
	Optional<Subject> findSubjectByCode(String code);

	/**
	 * @author HoangLV7
	 *
	 * @param id
	 * @return
	 */
	Optional<Subject> findSubjectById(int id);

	/**
	 * @author ToanNT18
	 * @param userId
	 * @param pageRequest
	 * @return
	 */
	@Query(value = "SELECT * FROM udf_findSubjectByUser(:userId)", nativeQuery = true)
	Page<Subject> findSubjectByUserId(@Param("userId") Integer userId, Pageable pageable);

	/**
	 * @author TrangDM2
	 * @param clazz
	 * @return
	 */
	@Query(value = "select * from subject s where s.id in (select cs.subject_id from clazz_subject cs where cs.clazz_id = :clazzId)", nativeQuery = true)
	List<Subject> findSubjectsByClass(@Param("clazzId") Integer clazzId);

	/**
	 * @author HoangLV7
	 *
	 * @param status
	 * @return
	 */
	List<Subject> findSubjectByStatus(String status);
}

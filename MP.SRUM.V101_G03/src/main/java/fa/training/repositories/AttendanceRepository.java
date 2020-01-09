package fa.training.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fa.training.models.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

	/**
	 * HoangLV7
	 *
	 * @param user
	 * @return list of attendance of trainee
	 */
	@Query("SELECT a FROM Attendance a INNER JOIN a.userList ua ON ua.id = :user order by a.date desc")
	public List<Attendance> findAttendanceByUser(int user);
}

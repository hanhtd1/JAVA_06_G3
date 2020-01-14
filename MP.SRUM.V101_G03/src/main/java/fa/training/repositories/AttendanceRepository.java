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
<<<<<<< HEAD
	@Query("SELECT a FROM Attendance a INNER JOIN a.user ua ON ua.id = :user")
=======
	@Query("SELECT a FROM Attendance a INNER JOIN a.userList ua ON ua.id = :user order by a.date desc")
>>>>>>> 3f9f247e78ad453d4f34bfa546d8c31334c4bc32
	public List<Attendance> findAttendanceByUser(int user);
}

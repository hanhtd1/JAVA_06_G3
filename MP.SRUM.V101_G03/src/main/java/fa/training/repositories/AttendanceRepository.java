package fa.training.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.training.models.Attendance;
import fa.training.models.User;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

	/**
	 * HoangLV7
	 *
	 * @param user
	 * @return list of attendance of trainee
	 */
	@Query("SELECT a FROM Attendance a INNER JOIN a.user ua ON ua.id = :user order by a.date desc")
	List<Attendance> findAttendanceByUser(int user);
	
	/**
	 * @author TrangDM2
	 * @param date
	 * @param user
	 * @return
	 */
	@Query(value = "SELECT a FROM Attendance a WHERE a.date= :date AND a.user= :user")
	Attendance findByDateAndUser(@Param("date") LocalDate date, @Param("user") User user);
	
}

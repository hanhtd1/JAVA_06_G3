package fa.training.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fa.training.models.Attendance;
import fa.training.models.User;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{
	
//	@Query("")
//	public List<Attendance> findByUser(User user);

}

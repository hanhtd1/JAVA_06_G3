package fa.training.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.models.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{
   
}

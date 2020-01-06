package fa.training.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.models.Attendance;
import fa.training.models.User;
import fa.training.repositories.AttendanceRepository;
import fa.training.services.IAttendanceService;

/**
 * @author HoangLV7
 *
 */
@Service
public class AttendanceServiceImpl implements IAttendanceService{

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Override
	public List<Attendance> getAttendancesByUser(User user) {
		return attendanceRepository.findAttendanceByUser(user.getId());
	}

}

package fa.training.services;

import java.util.List;

import fa.training.dtos.AttendanceDto;
import fa.training.models.Attendance;
import fa.training.models.User;

/**
 * @author HoangLV7
 *
 */
public interface AttendanceService {

	List<Attendance> getAttendancesByUser(User user);

	Boolean saveAttendances(List<AttendanceDto> attendances);

}

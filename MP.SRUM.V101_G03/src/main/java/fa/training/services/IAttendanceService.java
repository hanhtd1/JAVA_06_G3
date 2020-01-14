package fa.training.services;

import java.util.List;

import fa.training.models.Attendance;
import fa.training.models.User;

/**
 * @author HoangLV7
 *
 */
public interface IAttendanceService {

	public List<Attendance> getAttendancesByUser(User user);

  List<Attendance> saveAttendances(List<Attendance> attendances);

}

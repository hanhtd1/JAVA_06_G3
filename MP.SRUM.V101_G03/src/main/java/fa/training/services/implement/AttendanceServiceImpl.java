package fa.training.services.implement;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.models.Attendance;
import fa.training.models.User;
import fa.training.repositories.AttendanceRepository;
import fa.training.services.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService{
	
	private static final Logger LOGGER = LogManager.getLogger(AttendanceServiceImpl.class);

	@Autowired
	private AttendanceRepository attendanceRepository;

	/**
	 * @author HoangLV7
	 * 
	 */
	@Override
	public List<Attendance> getAttendancesByUser(User user) {
		LOGGER.info("Get list of Attendace by "+ user.getAccount());
		return attendanceRepository.findAttendanceByUser(user.getId());
	}
	
	/**
	 * @author TrangDM2
	 * @param attendances
	 * @return
	 */
	@Override
	public List<Attendance> saveAttendances(List<Attendance> attendances) {
	  return attendanceRepository.saveAll(attendances);
	}

}

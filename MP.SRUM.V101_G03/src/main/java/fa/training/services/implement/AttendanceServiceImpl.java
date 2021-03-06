package fa.training.services.implement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.dtos.AttendanceDto;
import fa.training.models.Attendance;
import fa.training.models.User;
import fa.training.repositories.AttendanceRepository;
import fa.training.repositories.UserRepository;
import fa.training.services.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

  @Autowired
  private AttendanceRepository attendanceRepository;

  @Autowired
  private UserRepository userRepository;

  /**
   * @author HoangLV7
   * 
   */
  @Override
  public List<Attendance> getAttendancesByUser(User user) {
    return (user != null && user.getId() != null) ? attendanceRepository.findAttendanceByUser(user.getId())
        : new ArrayList<Attendance>();
  }

  /**
   * @author TrangDM2
   * @param attendances
   * @return
   */
  @Override
  public Boolean saveAttendances(List<AttendanceDto> attendances) {

    try {
      attendances.forEach(attend -> {
        User trainee = userRepository.findUserById(attend.getUserId()).orElse(new User());
        Attendance attendance = attendanceRepository.findByDateAndUser(LocalDate.now(), trainee);
        if (null == attendance) {
          attendance = new Attendance();
          attendance.setDate(LocalDate.now());
          attendance.setType(attend.getType());
          attendance.setNote(attend.getNote());
          attendance.setUser(trainee);
          attendanceRepository.save(attendance);
        } else {
          attendance.setType(attend.getType());
          attendance.setNote(attend.getNote());
          attendanceRepository.save(attendance);
        }
      });
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}

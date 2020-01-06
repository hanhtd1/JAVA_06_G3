package fa.training.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fa.training.models.Attendance;
import fa.training.models.Feedback;
import fa.training.models.Subject;
import fa.training.models.User;
import fa.training.services.IAttendanceService;
import fa.training.services.IFeedbackService;
import fa.training.services.IUserService;

/**
 * @author HoangLV7
 *
 */
@Controller
@RequestMapping("trainee")
public class TraineeUserController {

	@Autowired
	private IUserService iUserService;
	
	@Autowired
	private IAttendanceService iAttendanceService;
	
	@Autowired
	private IFeedbackService iFeedbackService;

	@GetMapping("/")
	public String index(Model model) {
		String account = "hoanglv7";
		String password = "123456";
		
		User trainee = iUserService.getUser(account, password);
		
		List<User> users = iUserService.getMembers(trainee);
		List<Attendance> attendances = iAttendanceService.getAttendancesByUser(trainee);
		model.addAttribute("users", users);
		model.addAttribute("trainee", trainee);
		model.addAttribute("attendances", attendances);
		return "trainee-ui";
	}
	
	@GetMapping("/view-feedback")
	public @ResponseBody String viewFeedback(@RequestParam("userId") int userId, @RequestParam("subjectId") int subjectId) {
		Feedback feedback = iFeedbackService.getAllFeedback(userId, subjectId);
		return feedback.getContent();
	}

}

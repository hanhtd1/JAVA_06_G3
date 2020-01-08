package fa.training.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fa.training.dto.ScoreDto;
import fa.training.models.Attendance;
import fa.training.models.Clazz;
import fa.training.models.Feedback;
import fa.training.models.FeedbackPK;
import fa.training.models.User;
import fa.training.services.IAttendanceService;
import fa.training.services.IFeedbackService;
import fa.training.services.IScoreService;
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

	@Autowired
	private IScoreService iScoreService;

	@GetMapping("/")
	public String index(Model model) {
		String account = "hoanglv7";
		String password = "123456";

		User trainee = iUserService.getUser(account, password);

		List<User> users = iUserService.getMembers(trainee);
		List<Attendance> attendances = iAttendanceService.getAttendancesByUser(trainee);
		List<ScoreDto> scores = iScoreService.getScoreByUser(trainee.getId());
		Clazz classTrainee = trainee.getClazzList().get(0);
		model.addAttribute("class", classTrainee);
		model.addAttribute("users", users);
		model.addAttribute("trainee", trainee);
		model.addAttribute("attendances", attendances);
		model.addAttribute("scores", scores);
		return "trainee-ui";
	}

	@GetMapping("/view-feedback")
	public @ResponseBody String viewFeedback(@RequestParam("userId") int userId,
			@RequestParam("subjectId") int subjectId) {
		Feedback feedback = iFeedbackService.getAllFeedback(userId, subjectId);
		return feedback == null ? "You didn't commit feedback!" : feedback.getContent();
	}

	@GetMapping("/feedback-info")
	public @ResponseBody Feedback feedbackInfo(@RequestParam("userId") int userId,
			@RequestParam("subjectId") int subjectId) {
		return new Feedback(subjectId, userId);
	}

	@RequestMapping(path = "/add-feedback", method = RequestMethod.POST)
	public String addFeedback(Model model, @RequestParam("feedbackContent") String feedback,
			@RequestParam("userId") int userId, @RequestParam("subjectId") int subjectId) {
		iFeedbackService.save(new Feedback(new FeedbackPK(subjectId, userId), feedback));
		return "redirect:/trainee/";
	}

	@GetMapping("/member-info")
	public String memberInfo(Model model, @RequestParam("userId") int userId) {
		User user = iUserService.getUserById(userId);
		List<ScoreDto> scores = iScoreService.getScoreByUser(userId);
		model.addAttribute("user", user);
		model.addAttribute("scores", scores);
		return "trainee-score";
	}

}

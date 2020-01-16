package fa.training.controllers;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fa.training.dtos.ScoreDto;
import fa.training.models.Attendance;
import fa.training.models.Clazz;
import fa.training.models.Feedback;
import fa.training.models.FeedbackPK;
import fa.training.models.User;
import fa.training.services.ClazzService;
import fa.training.services.AttendanceService;
import fa.training.services.FeedbackService;
import fa.training.services.ScoreService;
import fa.training.services.UserService;
import fa.training.utils.Constant;

/**
 * @author HoangLV7
 *
 */
@Controller
@RequestMapping("trainee")
public class TraineeUserController {

	private static final Logger LOGGER = LogManager.getLogger(TraineeUserController.class);

	@Autowired
	private UserService iUserService;

	@Autowired
	private AttendanceService iAttendanceService;

	@Autowired
	private FeedbackService iFeedbackService;

	@Autowired
	private ScoreService iScoreService;
	
	@Autowired
	private ClazzService clazzService;

	@GetMapping("/")
	public String index(Model model, Authentication auth) {
		String account = ((UserDetails)auth.getPrincipal()).getUsername();
		LOGGER.info(account + " login successful");
		User trainee = iUserService.getUser(account);
		List<Clazz> classTrainee = clazzService.findClazzByTrainee(trainee);
		List<User> users = iUserService.getMembers(trainee);
		List<Attendance> attendances = iAttendanceService.getAttendancesByUser(trainee);
		List<ScoreDto> scores = iScoreService.getScoreByUser(trainee.getId());
		model.addAttribute("class", classTrainee.get(Constant.FIRST_RESULT));
		model.addAttribute("users", users);
		model.addAttribute("trainee", trainee);
		model.addAttribute("attendances", attendances);
		model.addAttribute("scores", scores);
		return "trainee-ui";
	}

	@GetMapping("/view-feedback")
	public @ResponseBody String viewFeedback(@RequestParam Integer userId,
			@RequestParam Integer subjectId) {
		Feedback feedback = iFeedbackService.getAllFeedback(userId, subjectId);
		return feedback == null ? Constant.NOT_FOUND_MESSAGE: feedback.getContent();
	}

	@GetMapping("/feedback-info")
	public @ResponseBody Feedback feedbackInfo(@RequestParam Integer userId,
			@RequestParam Integer subjectId) {
		return new Feedback(subjectId, userId);
	}

	@RequestMapping(path = "/add-feedback", method = RequestMethod.POST)
	public String addFeedback(Model model, @RequestParam String feedbackContent,
			@RequestParam Integer userId, @RequestParam Integer subjectId) {
		iFeedbackService.save(new Feedback(new FeedbackPK(subjectId, userId), feedbackContent));
		return "redirect:/trainee/";
	}

	@GetMapping("/member-info")
	public String memberInfo(Model model, @RequestParam Integer userId) {
		User user = iUserService.getUserById(userId);
		List<ScoreDto> scores = iScoreService.getScoreByUser(userId);
		model.addAttribute("user", user);
		model.addAttribute("scores", scores);
		return "trainee-score";
	}

}

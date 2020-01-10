package fa.training.controllers.trainer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.dto.TraineeScoreDTO;
import fa.training.models.ReviewTrainee;
import fa.training.models.ReviewTraineePK;
import fa.training.models.User;
import fa.training.services.ReviewTraineeService;
import fa.training.services.ScoreService;
import fa.training.services.TraineeService;
import fa.training.utils.Constant;

/**
 *
 * @author ToanNT18
 */
@RestController
@RequestMapping(value = "/trainer")
public class TraineeController {
	@Autowired
	private TraineeService traineeService;

	@Autowired
	private ReviewTraineeService reviewTraineeService;

	@Autowired
	private ScoreService scoreService;

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "{id}")
	public String findTraineeById(Model model) {
		User trainee = traineeService.findTraineeById(2);
		System.out.println(trainee.toString());
		return "<h1>Success</h1>";
	}
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/all")
	public String findAll(Model model) {
		List<User> trainee = traineeService.findAllTrainee();
		System.out.println(trainee.toString());
		return "<h1>Success</h1>";
	}

	@RequestMapping(value = "/clazz/{id}")
	public String findTraineeByClazz(Model model) {
		List<User> trainees = traineeService.findTraineeByClazz(1, 0);
		trainees.forEach(System.out::println);
		return "<h1>Success</h1>";
	}

	@RequestMapping(value = "/category")
	public String findTraineeByCategory(Model model) {
		List<User> trainees = traineeService.findTraineeByCategory(Constant.CLAZZ_CATEGORY, Constant.ROLE_TRAINER, 0);
		trainees.forEach(System.out::println);
		return "<h1>Success</h1>";
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/review-trainee")
	public String addReview() {
		reviewTraineeService
				.add(new ReviewTrainee(new ReviewTraineePK(1, 2), "A", "Hoc hanh tap trung, kien thuc tot"));
		return "<h1>Success</h1>";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/score")
	public String getScore(Model model) {
		List<TraineeScoreDTO> scores = scoreService.findByIdUserId(11);
		model.addAttribute("scores", scores);
		return "<h1>Success</h1>";
	}

}

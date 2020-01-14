package fa.training.controllers.trainer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.training.dto.TraineeScoreDTO;
import fa.training.models.Clazz;
import fa.training.models.ReviewTrainee;
import fa.training.models.ReviewTraineePK;
import fa.training.models.User;
import fa.training.services.ClazzService;
import fa.training.services.ReviewTraineeService;
import fa.training.services.ScoreService;
import fa.training.services.TraineeService;
import fa.training.services.implement.TraineeServiceImpl;
import fa.training.utils.Constant;
import fa.training.utils.SearchType;

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

	@Autowired
	private ClazzService clazzService;

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
		return "<h1>Success</h1>";
	}

	@RequestMapping(value = "/category")
	public String findTraineeByCategory(@RequestParam("category") String category, Model model) {
		List<User> trainees = traineeService.findTraineeByCategory(category, Constant.FIRST_PAGE);
		trainees.forEach(System.out::println);
		return "class-admin-trainee-manage :: trainees";
	}

	@GetMapping("search-all")
	public String traineeManage(Model model) {
		List<User> trainees = traineeService.findAllTrainee();
		List<Clazz> clazzs = clazzService.findAllClazzByTrainerId(2, Constant.FIRST_PAGE);

		List<String> categories = clazzs.parallelStream().map(clazz -> clazz.getCategory()).distinct()
				.collect(Collectors.toList());

		List<String> names = clazzs.parallelStream().map(clazz -> clazz.getName()).distinct()
				.collect(Collectors.toList());
		List<String> statuss = trainees.parallelStream().map(trainee -> trainee.getStatus()).distinct()
				.collect(Collectors.toList());

		categories.add(0, Constant.TRAINEE_SEARCH_ALL);
		names.add(0, Constant.TRAINEE_SEARCH_ALL);
		statuss.add(0, Constant.TRAINEE_SEARCH_ALL);

		model.addAttribute("categories", categories);
		model.addAttribute("names", names);
		model.addAttribute("statuss", statuss);
		model.addAttribute("trainees", trainees);
		return "class-admin-trainee-manage :: trainees";
	}

	@RequestMapping(value = "/search")
	public String findTraineeAll(
			@RequestParam(name = "category", defaultValue = Constant.TRAINEE_SEARCH_ALL) String category,
			@RequestParam(name = "clazzName", defaultValue = Constant.TRAINEE_SEARCH_ALL) String clazzName,
			@RequestParam(name = "status", defaultValue = Constant.TRAINEE_SEARCH_ALL) String status, Model model) {
		List<User> trainees;
		if (category.equals(Constant.TRAINEE_SEARCH_ALL) && clazzName.equals(Constant.TRAINEE_SEARCH_ALL)
				&& status.equals(Constant.TRAINEE_SEARCH_ALL)) {
			return "redirect:search-all";
		}
		String searchType = SearchType.searchType(category, clazzName, status);

		System.out.println("search type : " + searchType);

		switch (searchType) {
		case Constant.CATEGORY:
			trainees = traineeService.findTraineeByCategory(category, Constant.FIRST_PAGE);
			break;
		case Constant.CLAZZ:
			trainees = traineeService.findTraineeByClazzName(clazzName, Constant.FIRST_PAGE);
			break;
		case Constant.STATUS:
			trainees = traineeService.findTraineeByClazzName(clazzName, Constant.FIRST_PAGE);
			break;
		case Constant.CATEGORY_CLAZZ:
			trainees = traineeService.findTraineeByCategoryAndClazz(category, clazzName, Constant.FIRST_PAGE);
			break;
		case Constant.CATEGORY_STATUS:
			trainees = traineeService.findTraineeByCategoryAndStatus(category, status, Constant.FIRST_PAGE);
			break;
		case Constant.CLAZZ_STATUS:
			trainees = traineeService.findTraineeByClazzAndStatus(clazzName, status, Constant.FIRST_PAGE);
			break;
		case Constant.CATEGORY_CLAZZ_STATUS:
			trainees = traineeService.findTraineeByCategoryAndClazzAndStatus(category, clazzName, status,
					Constant.FIRST_PAGE);
			break;
		default:
			trainees = new ArrayList<>();
			break;
		}
		System.out.println("-----------------" + TraineeServiceImpl.numberOfPage + "-----------------");
		model.addAttribute("trainees", trainees);
		return "class-admin-trainee-manage :: trainees";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/trainee-feedback/{id}")
	public String addReview(@PathVariable("id") Integer id) {
		reviewTraineeService
				.add(new ReviewTrainee(new ReviewTraineePK(1, 2), "A", "Hoc hanh tap trung, kien thuc tot"));
		return "<h1>Success</h1>";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/grade/{id}")
	public String getScore(Model model, @PathVariable("id") Integer id) {
		System.out.println("----------------------BEGIN----------------------");
		List<TraineeScoreDTO> scores = scoreService.findByIdUserId(id);

		scores.forEach(score -> score.display());
		model.addAttribute("scores", scores);
		return "class-admin-trainee-manage :: scoreModal";
	}
}

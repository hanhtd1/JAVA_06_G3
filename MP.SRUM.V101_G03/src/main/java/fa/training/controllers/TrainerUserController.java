package fa.training.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fa.training.dtos.TraineeScoreDto;
import fa.training.models.Clazz;
import fa.training.models.ReviewTrainee;
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
@Controller
@RequestMapping(value = "/trainer")
public class TrainerUserController {
	@Autowired
	private TraineeService traineeService;

	@Autowired
	private ReviewTraineeService reviewTraineeService;

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private ClazzService clazzService;

	@GetMapping("search-all")
	public String traineeManage(@RequestParam(name = "page", defaultValue = Constant.FIRST_PAGE_STRING) int page,
			Model model) {

		List<User> trainees = traineeService.findAllTrainee(page - 1);

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
		model.addAttribute("index", page);
		model.addAttribute("totalPages", TraineeServiceImpl.numberOfPage);
		model.addAttribute("categories", categories);
		model.addAttribute("names", names);
		model.addAttribute("statuss", statuss);
		model.addAttribute("trainees", trainees);
		return "trainer-trainee-manage :: trainees";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String findTraineeAll(
			@RequestParam(name = "category", defaultValue = Constant.TRAINEE_SEARCH_ALL) String category,
			@RequestParam(name = "clazzName", defaultValue = Constant.TRAINEE_SEARCH_ALL) String clazzName,
			@RequestParam(name = "status", defaultValue = Constant.TRAINEE_SEARCH_ALL) String status,
			@RequestParam(name = "page", defaultValue = Constant.FIRST_PAGE_STRING) int page, Model model) {
		List<User> trainees;
		if (category.equals(Constant.TRAINEE_SEARCH_ALL) && clazzName.equals(Constant.TRAINEE_SEARCH_ALL)
				&& status.equals(Constant.TRAINEE_SEARCH_ALL)) {
			return "redirect:search-all?page=" + page;
		}
		String searchType = SearchType.searchType(category, clazzName, status);
		System.out.println("search type : " + searchType);
		switch (searchType) {
		case Constant.CATEGORY:
			trainees = traineeService.findTraineeByCategory(category, page - 1);
			break;
		case Constant.CLAZZ:
			trainees = traineeService.findTraineeByClazzName(clazzName, page - 1);
			break;
		case Constant.STATUS:
			trainees = traineeService.findTraineeByClazzName(clazzName, page - 1);
			break;
		case Constant.CATEGORY_CLAZZ:
			trainees = traineeService.findTraineeByCategoryAndClazz(category, clazzName, page - 1);
			break;
		case Constant.CATEGORY_STATUS:
			trainees = traineeService.findTraineeByCategoryAndStatus(category, status, page - 1);
			break;
		case Constant.CLAZZ_STATUS:
			trainees = traineeService.findTraineeByClazzAndStatus(clazzName, status, page - 1);
			break;
		case Constant.CATEGORY_CLAZZ_STATUS:
			trainees = traineeService.findTraineeByCategoryAndClazzAndStatus(category, clazzName, status, page - 1);
			break;
		default:
			trainees = new ArrayList<>();
			break;
		}
		model.addAttribute("index", page);
		model.addAttribute("totalPages", TraineeServiceImpl.numberOfPage);
		model.addAttribute("trainees", trainees);
		return "trainer-trainee-manage :: trainees";
	}

	/**
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@RequestMapping(value = "/grade/{id}")
	public String getScore(Model model, @PathVariable("id") Integer id) {
		List<TraineeScoreDto> scores = scoreService.findByIdUserId(id);
		model.addAttribute("scores", scores);
		return "trainer-trainee-manage :: scoreModal";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/review")
	public String saveReview(@RequestParam("feedback") String feedback, Model model) {
		reviewTraineeService.add(new ReviewTrainee());
		return "trainer-trainee-manage :: scoreModal";
	}

}

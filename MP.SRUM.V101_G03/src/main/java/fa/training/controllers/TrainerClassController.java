package fa.training.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fa.training.models.Clazz;
import fa.training.models.Subject;
import fa.training.models.User;
import fa.training.services.ClazzService;
import fa.training.services.SubjectService;
import fa.training.services.TraineeService;
import fa.training.services.implement.ClazzServiceImpl;
import fa.training.services.implement.TraineeServiceImpl;
import fa.training.utils.Constant;
import fa.training.utils.SearchType;

/**
 *
 * @author ToanNT18
 */

@Controller
@RequestMapping(value = "/trainer/clazz")
public class TrainerClassController {

	@Autowired
	private ClazzService clazzService;

	@Autowired
	private TraineeService traineeService;

	@Autowired
	private SubjectService subjectService;

	@RequestMapping(value = "/content")
	public String getClazzByNameOrCategory(
			@RequestParam(name = "clazz", defaultValue = Constant.CLAZZ_CONTENT_SEARCH_DEFAULT) String searchContent,
			@RequestParam(name = "status", defaultValue = Constant.CLAZZ_STATUS_DEFAULT) String status,
			@RequestParam(name = "page", defaultValue = Constant.CLAZZ_PAGE_DEFAULT) int page, Model model) {
		List<Clazz> clazzs;
		String searchType = SearchType.clazzSearchType(searchContent, status);
		switch (searchType) {
		case Constant.CLAZZ_SEARCH_BY_TRAINER_ID:
			clazzs = clazzService.findAllClazzByTrainerId(Constant.USER_ID_DEFAULT, page - 1);
			break;
		case Constant.STATUS:
			clazzs = clazzService.findClazzByStatus(Constant.USER_ID_DEFAULT, page, status);
			break;
		case Constant.CLAZZ_NAME_AND_CATEGORY:
			clazzs = clazzService.findClazzByNameOrCategory(Constant.USER_ID_DEFAULT, page, searchContent);
			break;
		case Constant.CLAZZ_NAME_AND_CATEGORY_AND_STATUS:
			clazzs = clazzService.findClazzByStatusAndContent(Constant.USER_ID_DEFAULT, page, status, searchContent);
			break;
		default:
			clazzs = new ArrayList<>();
			break;
		}
		model.addAttribute("totalPages", ClazzServiceImpl.totalPage);
		model.addAttribute("clazzs", clazzs);
		return "trainer-class-manage :: clazzs";
	}

	@RequestMapping(value = "/trainee")
	public String findTraineeByClazz(@RequestParam(name = "clazzId") int clazzId, Model model) {
		List<User> trainees = traineeService.findTraineeByClazzId(clazzId, Constant.FIRST_PAGE);
		List<Clazz> clazzs = clazzService.findAllClazzByTrainerId(Constant.USER_ID_DEFAULT, Constant.FIRST_PAGE);

		List<String> categories = clazzs.stream().map(clazz -> clazz.getCategory()).distinct()
				.collect(Collectors.toList());

		List<String> names = clazzs.stream().map(clazz -> clazz.getName()).distinct().collect(Collectors.toList());
		List<String> statuss = trainees.stream().map(trainee -> trainee.getStatus()).distinct()
				.collect(Collectors.toList());

		categories.add(0, Constant.TRAINEE_SEARCH_ALL);
		names.add(0, Constant.TRAINEE_SEARCH_ALL);
		statuss.add(0, Constant.TRAINEE_SEARCH_ALL);

		model.addAttribute("clazz", "clazz");
		model.addAttribute("totalPages", TraineeServiceImpl.numberOfPage);
		model.addAttribute("categories", categories);
		model.addAttribute("names", names);
		model.addAttribute("statuss", statuss);
		model.addAttribute("trainees", trainees);

		return "trainer-trainee-manage :: content-all";
	}

	@RequestMapping(value = "/subject")
	public String findSubjectByClazz(@RequestParam(name = "clazzId") int clazzId,
			@RequestParam(name = "page", defaultValue = Constant.FIRST_PAGE_STRING) int page, Model model) {
		List<Subject> subjects = subjectService.findSubjectByClazz(clazzId,
				PageRequest.of(page - 1, Constant.PAGE_SIZE));
		model.addAttribute("totalPages", TraineeServiceImpl.numberOfPage);
		model.addAttribute("subjects", subjects);
		return "trainer-subject-manage :: content-all";
	}
}

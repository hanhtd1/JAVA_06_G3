package fa.training.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.training.models.Clazz;
import fa.training.models.Subject;
import fa.training.models.User;
import fa.training.services.ClazzService;
import fa.training.services.ScoreService;
import fa.training.services.SubjectService;
import fa.training.services.TraineeService;
import fa.training.services.implement.ClazzServiceImpl;
import fa.training.services.implement.TraineeServiceImpl;
import fa.training.utils.Constant;

@Controller
@RequestMapping("trainer")
public class TrainerMainController {

	@Autowired
	private TraineeService traineeService;

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private ClazzService clazzService;

	@GetMapping("grade-modal")
	public String gradeModal() {
		scoreService.findByIdUserId(1);
		return "guide";
	}

	@GetMapping("feedback-modal")
	public String feedbackModal() {
		return "guide";
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("home")
	public String classAdmin() {
		return "class-admin-dashboard";
	}

	@GetMapping("class-manage")
	public String classManage(Model model) {
		List<Clazz> clazzs = clazzService.findAllClazzByTrainerId(2, Constant.FIRST_PAGE);
		List<String> statuss = clazzs.stream().map(clazz -> clazz.getStatus()).distinct().collect(Collectors.toList());

		statuss.add(0, Constant.TRAINEE_SEARCH_ALL);

		model.addAttribute("totalPages", ClazzServiceImpl.totalPage);
		model.addAttribute("statuss", statuss);
		model.addAttribute("clazzs", clazzs);
		return "class-admin-class-manage";
	}

	@GetMapping("trainee-manage")
	public String getTraineeByUserId(Model model) {
		List<User> trainees = traineeService.findAllTrainee(Constant.FIRST_PAGE);
		List<Clazz> clazzs = clazzService.findAllClazzByTrainerId(2, Constant.FIRST_PAGE);

		List<String> categories = clazzs.stream().map(clazz -> clazz.getCategory()).distinct()
				.collect(Collectors.toList());

		List<String> names = clazzs.stream().map(clazz -> clazz.getName()).distinct().collect(Collectors.toList());
		List<String> statuss = trainees.stream().map(trainee -> trainee.getStatus()).distinct()
				.collect(Collectors.toList());

		categories.add(0, Constant.TRAINEE_SEARCH_ALL);
		names.add(0, Constant.TRAINEE_SEARCH_ALL);
		statuss.add(0, Constant.TRAINEE_SEARCH_ALL);
		System.out.println("------------------" + TraineeServiceImpl.numberOfPage + "------------------");
		model.addAttribute("totalPages", TraineeServiceImpl.numberOfPage);
		model.addAttribute("categories", categories);
		model.addAttribute("names", names);
		model.addAttribute("statuss", statuss);
		model.addAttribute("trainees", trainees);
		return "class-admin-trainee-manage";
	}

	@GetMapping("trainee-manage/{clazzName}")
	public String getTraineeByClazzId(@PathVariable("clazzName") String clazzName, Model model) {
		List<User> trainees = traineeService.findTraineeByClazzName(clazzName, Constant.FIRST_PAGE);
		List<Clazz> clazzs = clazzService.findAllClazzByTrainerId(Constant.USER_ID_DEFAULT, Constant.FIRST_PAGE);

		List<String> categories = clazzs.stream().map(clazz -> clazz.getCategory()).distinct()
				.collect(Collectors.toList());

		List<String> names = clazzs.stream().map(clazz -> clazz.getName()).distinct().collect(Collectors.toList());
		List<String> statuss = trainees.stream().map(trainee -> trainee.getStatus()).distinct()
				.collect(Collectors.toList());

		categories.add(0, Constant.TRAINEE_SEARCH_ALL);
		names.add(0, Constant.TRAINEE_SEARCH_ALL);
		statuss.add(0, Constant.TRAINEE_SEARCH_ALL);
		model.addAttribute("totalPages", TraineeServiceImpl.numberOfPage);
		model.addAttribute("categories", categories);
		model.addAttribute("names", names);
		model.addAttribute("statuss", statuss);
		model.addAttribute("trainees", trainees);
		return "class-admin-trainee-manage";
	}

	@GetMapping("subject-manage")
	public String subjectManage(Model model) {
		List<Subject> subjects = subjectService.findSubjectByUserId(Constant.USER_ID_DEFAULT - 1,
				PageRequest.of(Constant.FIRST_PAGE, Constant.PAGE_SIZE));
		model.addAttribute("subjects", subjects);
		return "class-admin-subject-manage";
	}

	@GetMapping("trainer-manage")
	public String trainerManage() {
		return "class-admin-trainer-manage";
	}

	@GetMapping("file-manage")
	public String fileManage() {
		return "import-export";
	}

	@GetMapping("guide")
	public String guide() {
		return "guide";
	}

}

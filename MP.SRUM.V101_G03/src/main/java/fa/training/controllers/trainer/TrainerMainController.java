package fa.training.controllers.trainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.training.services.ClazzService;
import fa.training.services.ScoreService;
import fa.training.services.SubjectService;
import fa.training.services.TraineeService;

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
}

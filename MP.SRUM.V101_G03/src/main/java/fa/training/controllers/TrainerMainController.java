package fa.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.training.services.ScoreService;

@Controller
@RequestMapping("trainer")
public class TrainerMainController {

	@Autowired
	private ScoreService scoreService;

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

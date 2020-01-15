package fa.training.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fa.training.models.Feedback;
import fa.training.services.FeedbackService;

/**
 *
 * @author ToanNT18
 */
@Controller
@RequestMapping(value = "/trainer")
public class TrainerSubjectController {

	@Autowired
	private FeedbackService feedbackService;

	@RequestMapping(value = "/feedback")
	public String getFeedbackSubject(@RequestParam(name = "subjectId") int subjectId, Model model) {
		List<Feedback> feedbacks = feedbackService.findFeedbackByfindBySubjecId(subjectId);
		model.addAttribute("feedbacks", feedbacks);
		return "class-admin-subject-manage :: feedback";
	}

}
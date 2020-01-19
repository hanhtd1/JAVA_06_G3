package fa.training.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import fa.training.models.Feedback;
import fa.training.models.Subject;
import fa.training.models.User;
import fa.training.services.FeedbackService;
import fa.training.services.SubjectService;
import fa.training.utils.Constant;

/**
 *
 * @author ToanNT18
 */
@Controller
@RequestMapping(value = "/trainer")
public class TrainerSubjectController {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private FeedbackService feedbackService;

	@RequestMapping(value = "/subject")
	public String getFeedbackSubject(@SessionAttribute("user") User user,
			@RequestParam(name = "subjectId") int subjectId, Model model) {
		List<Subject> subjects = subjectService.findSubjectByUserId(user.getId(),
				PageRequest.of(Constant.FIRST_PAGE, Constant.PAGE_SIZE));
		model.addAttribute("subjects", subjects);
		return "trainer-subject-manage :: content-all";
	}

	@RequestMapping(value = "/subject/feedback")
	public String getFeedbackBySubjectId(@RequestParam("subjectId") int subjectId, Model model) {
		List<Feedback> feedbacks = feedbackService.findFeedbackBySubjecId(subjectId);
		model.addAttribute("feedbacks", feedbacks);
		return "trainer-subject-manage :: view-feedback";
	}

}
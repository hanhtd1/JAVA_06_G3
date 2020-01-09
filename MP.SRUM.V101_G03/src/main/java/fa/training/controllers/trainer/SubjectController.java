package fa.training.controllers.trainer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.models.Feedback;
import fa.training.models.Subject;
import fa.training.service.FeedbackService;
import fa.training.service.SubjectService;

/**
 *
 * @author ToanNT18
 */
@RestController
@RequestMapping(value = "/trainer/subject")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private FeedbackService feedbackService;

	/**
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/userId")
	public String getHandler(Model model) {
		List<Subject> subjects = subjectService.findSubjectByUserId(1);
		subjects.forEach(System.out::println);
		return "<h1>Success</h1>";
	}

	/**
	 * @param model
	 * @param clazzId
	 * @return
	 */
	@RequestMapping(value = "/clazzId")
	public String findAll(Model model) {
		List<Subject> subjects = subjectService.findSubjectByClazz(1);
		subjects.forEach(System.out::println);
		return "<h1>Success</h1>";
	}

	@RequestMapping(value = "/feedback")
	public String getFeedbackSubject(Model model) {
		List<Feedback> feedbacks = feedbackService.findFeedbackByfindBySubjecId(1);
		feedbacks.forEach(System.out::println);
		return "<h1>Success</h1>";
	}

	@RequestMapping(value = "/clazz/subject")
	public String getFeedbackClassSubject(Model model) {
		List<Feedback> feedbacks = feedbackService.findFeedbackBySubjectIdAndClazzId(1, 1);
		feedbacks.forEach(System.out::println);
		return "<h1>Success</h1>";
	}
}
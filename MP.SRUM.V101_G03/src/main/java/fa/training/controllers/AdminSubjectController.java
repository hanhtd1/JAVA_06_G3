package fa.training.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fa.training.dtos.ApiObject;
import fa.training.models.Clazz;
import fa.training.models.Feedback;
import fa.training.models.Subject;
import fa.training.models.User;
import fa.training.services.ClazzService;
import fa.training.services.FeedbackService;
import fa.training.services.SubjectService;
import fa.training.utils.Constant;

/**
 * @author HoangLV7
 *
 */
@Controller
@RequestMapping("admin")
public class AdminSubjectController {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	public ClazzService clazzService;

	@Autowired
	private FeedbackService iFeedbackService;

	@RequestMapping(path = "add-subject", method = RequestMethod.POST)
	public @ResponseBody ApiObject<Subject> addSubject(@RequestParam String subjectName,
			@RequestParam("subjectCode") String subjectCode, @RequestParam Float subjectDuration) {
		Subject subject = new Subject(subjectName, subjectCode, subjectDuration, Constant.SUBJECT_ACTIVE_STATUS);
		return subjectService.save(subject);
	}

	@RequestMapping(path = "load-subject", method = RequestMethod.GET)
	public @ResponseBody Subject loadSubject(@RequestParam Integer subjectId) {
		Subject subject = subjectService.findSubjectById(subjectId);
		return subject;
	}

	@RequestMapping(path = "load-class", method = RequestMethod.GET)
	public String loadClass(Model model, @RequestParam Integer subjectId) {
		List<Clazz> classes = clazzService.findBySubject(subjectId);
		Map<String, Set<User>> classDetails = classes.stream()
				.collect(Collectors.toMap(Clazz::getName, Clazz::getUserList));
		model.addAttribute("usersByClass", classDetails);
		return "class-admin-subject-details";
	}

	@GetMapping("/view-feedback")
	public @ResponseBody String viewFeedback(@RequestParam("userId") int userId,
			@RequestParam("subjectId") int subjectId) {
		Feedback feedback = iFeedbackService.getFeedback(userId, subjectId);
		return feedback == null ? Constant.NOT_FOUND_MESSAGE : feedback.getContent();
	}

	@RequestMapping(path = "edit-subject", method = RequestMethod.POST)
	public @ResponseBody ApiObject<Subject> updateSubject(@RequestParam Integer subjectId,
			@RequestParam String subjectName, @RequestParam("subjectCode") String subjectCode,
			@RequestParam Float subjectDuration, @RequestParam String subjectStatus) {
		Subject subject = new Subject(subjectId, subjectName, subjectCode, subjectDuration, subjectStatus);
		return subjectService.update(subject);
	}

	@RequestMapping(path = "search", method = RequestMethod.GET)
	public String seachSubject(Model model, @RequestParam("subjectStatus") String subjectStatus) {
		List<Subject> subjects = subjectService.findByStatus(subjectStatus);
		model.addAttribute("subjects", subjects);
		return "class-admin-subject-list";
	}

	@RequestMapping(path = "del-subject", method = RequestMethod.DELETE)
	public @ResponseBody Subject delSubject(@RequestParam Integer subjectId) {
		Subject subject = subjectService.delSubject(subjectId);
		return subject;
	}
}

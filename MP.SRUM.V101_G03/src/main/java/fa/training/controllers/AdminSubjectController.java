package fa.training.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fa.training.dto.ApiObject;
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
		ApiObject<Subject> apiObject = new ApiObject<Subject>();
		boolean checkExisted = subjectService.checkSubjectExisted(subjectCode);
		if (checkExisted) {
			apiObject.setMessage(Constant.CREATE_FAIL_MESSAGE);
		} else {
			Subject subject = new Subject(subjectName, subjectCode, subjectDuration, Constant.SUBJECT_DEFAULT_STATUS);
			subject = subjectService.save(subject);
			apiObject.setMessage(Constant.CREATE_SUCCESS_MESSAGE);
			apiObject.setT(subject);
		}
		return apiObject;
	}

	@RequestMapping(path = "class-by-subject", method = RequestMethod.GET)
	public String getSubjectDetails(Model model, @RequestParam Integer subjectId) {
		List<Clazz> classes = clazzService.findBySubject(subjectId);
		Subject subject = subjectService.findSubjectById(subjectId);
		Map<String, List<User>> usersByClass = classes.stream()
				.collect(Collectors.toMap(Clazz::getName, Clazz::getUserList));
		model.addAttribute("usersByClass", usersByClass);
		model.addAttribute("subject", subject);
		return "class-admin-subject-details";
	}
	
	@GetMapping("/view-feedback")
	public @ResponseBody String viewFeedback(@RequestParam("userId") int userId,
			@RequestParam("subjectId") int subjectId) {
		Feedback feedback = iFeedbackService.getAllFeedback(userId, subjectId);
		return feedback == null ? "You didn't commit feedback!" : feedback.getContent();
	}
	
	@RequestMapping(path = "edit-subject",  method = RequestMethod.POST)
	public @ResponseBody ApiObject<Subject> updateSubject(@RequestParam Integer subjectId, @RequestParam String subjectName,
			@RequestParam("subjectCode") String subjectCode, @RequestParam Float subjectDuration) {
		ApiObject<Subject> apiObject = new ApiObject<Subject>();
		Subject subject = new Subject(subjectId, subjectName, subjectCode, subjectDuration);
		subject = subjectService.save(subject);
		apiObject.setT(subject);
		apiObject.setMessage(Constant.UPDATE_SUCCESS_MESSAGE);
		return apiObject;
	}
	
	@RequestMapping(path = "del-subject", method = RequestMethod.DELETE)
	public @ResponseBody List<Subject> delSubject(@RequestParam Integer subjectId){
		List<Subject> subjects = new ArrayList<Subject>();
		//TODO something
		return subjects;
	}
}

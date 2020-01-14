package fa.training.controllers;

import java.util.ArrayList;
import java.util.HashMap;
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

import fa.training.dto.APIObject;
import fa.training.models.Clazz;
import fa.training.models.Feedback;
import fa.training.models.Subject;
import fa.training.models.User;
import fa.training.services.ClazzService;
import fa.training.services.IFeedbackService;
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
	private IFeedbackService iFeedbackService;

	@RequestMapping(path = "add-subject", method = RequestMethod.POST)
	public @ResponseBody APIObject<Subject> addSubject(@RequestParam("subjectName") String subjectName,
			@RequestParam("subjectCode") String subjectCode, @RequestParam("subjectDuration") float subjectDuration) {
		APIObject<Subject> apiObject = new APIObject<Subject>();
		boolean checkExisted = subjectService.checkSubjectExisted(subjectCode);
		if (checkExisted) {
			apiObject.setMessage(Constant.MESSAGE_OBJECT_EXISTED);
		} else {
			Subject subject = new Subject(subjectName, subjectCode, subjectDuration, Constant.SUBJECT_STATUS_DEFAULT);
			subject = subjectService.save(subject);
			apiObject.setMessage(Constant.MESSAGE_OBJECT_COMMITTED);
			apiObject.setT(subject);
		}
		return apiObject;
	}

	@RequestMapping(path = "class-by-subject", method = RequestMethod.GET)
	public String getSubjectDetails(Model model, @RequestParam("subjectId") int subjectId) {
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
	public @ResponseBody APIObject<Subject> updateSubject(@RequestParam("subjectId") int subjectId, @RequestParam("subjectName") String subjectName,
			@RequestParam("subjectCode") String subjectCode, @RequestParam("subjectDuration") float subjectDuration) {
		APIObject<Subject> apiObject = new APIObject<Subject>();
		Subject subject = new Subject(subjectId, subjectName, subjectCode, subjectDuration);
		subject = subjectService.save(subject);
		apiObject.setT(subject);
		apiObject.setMessage(Constant.MESSAGE_OBJECT_UPDATE_SUCCESS);
		return apiObject;
	}
	
	@RequestMapping(path = "del-subject", method = RequestMethod.DELETE)
	public @ResponseBody List<Subject> delSubject(@RequestParam("subjectId") int subjectId){
		List<Subject> subjects = new ArrayList<Subject>();
		//Todo something
		return subjects;
	}
}

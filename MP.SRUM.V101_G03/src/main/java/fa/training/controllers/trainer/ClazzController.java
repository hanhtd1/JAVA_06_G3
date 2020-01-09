package fa.training.controllers.trainer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.models.Clazz;
import fa.training.service.ClazzService;

/**
 *
 * @author ToanNT18
 */

@RestController
@RequestMapping(value = "/trainer/clazz")
public class ClazzController {
	
	@Autowired
	private ClazzService clazzService;

	@GetMapping
	public String getClazz(Model model) {
		List<Clazz> clazzs = clazzService.findAllClazzByTrainerId(1, 1);
		clazzs.forEach(System.out::println);
		return "<h1>Success</h1>";
	}

	@RequestMapping(value = "/category")
	public String getClazzByCategory(Model model) {
		List<Clazz> clazzs = clazzService.findClazzByNameOrCategory(1, 1, "Java");
		clazzs.forEach(System.out::println);
		return "<h1>Success</h1>";
	}

	@RequestMapping(value = "/status")
	public String getClazzByStatus(Model model) {
		List<Clazz> clazzs = clazzService.findClazzByStatus(1, 1, "Active");
		clazzs.forEach(System.out::println);
		return "<h1>Success</h1>";
	}

	@RequestMapping(value = "/status/**")
	public String getClazzByStatusAndCategory(Model model) {
		List<Clazz> clazzs = clazzService.findClazzByStatusAndContent(1, 1, "Active", "Java");
		clazzs.forEach(System.out::println);
		return "<h1>Success</h1>";
	}
}

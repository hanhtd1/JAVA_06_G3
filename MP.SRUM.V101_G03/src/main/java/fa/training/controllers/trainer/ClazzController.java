package fa.training.controllers.trainer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.models.Clazz;
import fa.training.services.ClazzService;

/**
 *
 * @author ToanNT18
 */

@RestController
@RequestMapping(value = "/trainer")
public class ClazzController {

	@Autowired
	private ClazzService clazzService;

	@GetMapping
	public String getClazz(Model model) {
		List<Clazz> clazzs = clazzService.findAllClazzByTrainerId(2, 0);
		System.out.println(clazzs.size());
		clazzs.forEach(System.out::println);
		return "<h1>Success</h1>";
	}

	@RequestMapping(value = "/category")
	public String getClazzByCategory(Model model) {
		List<Clazz> clazzs = clazzService.findClazzByCategory(2, 0, "Java");
		System.out.println(clazzs.size());
		clazzs.forEach(System.out::println);
		return "<h1>Success</h1>";
	}

	@RequestMapping(value = "/content")
	public String getClazzByNameOrCategory(Model model) {
		List<Clazz> clazzs = clazzService.findClazzByNameOrCategory(2, 0, "Java");
		System.out.println(clazzs.size());
		clazzs.forEach(System.out::println);
		return "<h1>Success</h1>";
	}

	@RequestMapping(value = "/status")
	public String getClazzByStatus(Model model) {
		List<Clazz> clazzs = clazzService.findClazzByStatus(2, 0, "Active");
		System.out.println(clazzs.size());
		clazzs.forEach(System.out::println);
		return "<h1>Success</h1>";
	}

	@RequestMapping(value = "/status/**")
	public String getClazzByStatusAndCategory(Model model) {
		List<Clazz> clazzs = clazzService.findClazzByStatusAndContent(2, 0, "Active", "Java");
		System.out.println(clazzs.size());
		clazzs.forEach(System.out::println);
		return "<h1>Success</h1>";
	}
}

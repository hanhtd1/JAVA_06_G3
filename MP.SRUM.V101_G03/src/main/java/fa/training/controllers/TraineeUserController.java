package fa.training.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.training.models.User;
import fa.training.services.IUserService;

/**
 * @author HoangLV7
 *
 */
@Controller
@RequestMapping("trainee")
public class TraineeUserController {

	@Autowired
	private IUserService iUserService;

	@GetMapping("/")
	public String index(Model model) {
		String account = "hoanglv7";
		String password = "123456";
		User trainee = iUserService.getUser(account, password);
		List<User> users = iUserService.getMembers(trainee);
		model.addAttribute("users", users);
		model.addAttribute("trainee", trainee);
		return "trainee-ui";
	}

}

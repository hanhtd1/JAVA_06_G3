package fa.training.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author HoangLV7
 *
 */
@Controller
@RequestMapping("trainee")
public class TraineeUserController {

	@GetMapping("/")
	  public String index() {
	    return "trainee-ui";
	  }
	
}

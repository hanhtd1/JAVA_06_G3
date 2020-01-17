package fa.training.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TrainerAdviceController {
	@ExceptionHandler(Exception.class)
	public String exceptionHandler() {
		return "403";
	}
}

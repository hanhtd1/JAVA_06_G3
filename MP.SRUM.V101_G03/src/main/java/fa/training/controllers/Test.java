package fa.training.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fa.training.models.Clazz;
import fa.training.models.User;

public class Test {

	public static void main(String[] args) {
		List<Clazz> classes = new ArrayList<Clazz>();
		classes.add(new Clazz(1));
		List<User> users = new ArrayList<User>();
		users.add(new User("A1", "A", "1", "A", "A", "A", LocalDate.parse("2012-12-12"), "ROLE_TRAINEE", "A", "Active"));
		users.add(new User("A2", "A", "1", "A", "A", "A", LocalDate.parse("2012-12-12"), "ROLE_TRAINEE", "A", "Active"));
		users.add(new User("A3", "A", "1", "A", "A", "A", LocalDate.parse("2012-12-12"), "ROLE_TRAINEE", "A", "Active"));
		users.add(new User("A4", "A", "1", "A", "A", "A", LocalDate.parse("2012-12-12"), "ROLE_TRAINEE", "A", "Active"));
		users.add(new User("A", "A", "1", "A", "A", "A", LocalDate.parse("2012-12-12"), "ROLE_TRAINER", "A", "Active"));
		classes.get(0).setUserList(users);
		Map<String, List<User>> usersByClass = classes.stream()
				.collect(Collectors.toMap(Clazz::getName, Clazz::getUserList));

	}

}

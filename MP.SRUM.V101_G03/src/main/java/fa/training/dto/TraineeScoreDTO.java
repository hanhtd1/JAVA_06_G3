package fa.training.dto;

public interface TraineeScoreDTO {
	String getName();

	Float getPractice();

	Float getTheory();

	default void display() {
		System.out.println("name : " + getName() + ", pratice : " + getPractice() + ", theory : " + getTheory());
	}
}

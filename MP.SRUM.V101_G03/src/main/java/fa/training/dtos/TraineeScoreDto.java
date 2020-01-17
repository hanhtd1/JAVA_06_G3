package fa.training.dtos;

public interface TraineeScoreDto {
	String getName();

	Float getPractice();

	Float getTheory();

	default void display() {
		System.out.println("name : " + getName() + ", pratice : " + getPractice() + ", theory : " + getTheory());
	}

}

package fa.training.dtos;

public interface BestTraineeDto {
	String getName();

	Float getScore();

	Float getClazz();

	default void display() {
		System.out.println("Name : " + getName() + ", score : " + getScore() + ", clazz : " + getClazz());
	}
}

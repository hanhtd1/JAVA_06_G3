package fa.training.dto;

public class TraineeScoreDto {
	private String subjectName;
	private Float theory;
	private Float practice;
	public TraineeScoreDto() {
	}

	public TraineeScoreDto(String subjectName, Float theory, Float practice) {
		super();
		this.subjectName = subjectName;
		this.theory = theory;
		this.practice = practice;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Float getTheory() {
		return theory;
	}

	public void setTheory(Float theory) {
		this.theory = theory;
	}

	public Float getPractice() {
		return practice;
	}

	public void setPractice(Float practice) {
		this.practice = practice;
	}

}

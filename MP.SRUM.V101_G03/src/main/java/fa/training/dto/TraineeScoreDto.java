package fa.training.dto;

public class TraineeScoreDto {
	private String subjectName;

	private Integer theory;

	private Integer practice;

	public TraineeScoreDto() {
	}

	public TraineeScoreDto(String subjectName, Integer theory, Integer practice) {
		super();
		this.subjectName = subjectName;
		this.theory = theory;
		this.practice = practice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((practice == null) ? 0 : practice.hashCode());
		result = prime * result + ((subjectName == null) ? 0 : subjectName.hashCode());
		result = prime * result + ((theory == null) ? 0 : theory.hashCode());
		return result;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getTheory() {
		return theory;
	}

	public void setTheory(Integer theory) {
		this.theory = theory;
	}

	public Integer getPractice() {
		return practice;
	}

	public void setPractice(Integer practice) {
		this.practice = practice;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TraineeScoreDto other = (TraineeScoreDto) obj;
		if (practice == null) {
			if (other.practice != null)
				return false;
		} else if (!practice.equals(other.practice))
			return false;
		if (subjectName == null) {
			if (other.subjectName != null)
				return false;
		} else if (!subjectName.equals(other.subjectName))
			return false;
		if (theory == null) {
			if (other.theory != null)
				return false;
		} else if (!theory.equals(other.theory))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TraineeScoreDTO [subjectName=" + subjectName + ", theory=" + theory + ", practice=" + practice + "]";
	}

}

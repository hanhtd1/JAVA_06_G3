package fa.training.dtos;

import fa.training.models.Score;

/**
 * @author TrangDM2
 *
 */
public class ScoreDto {

	private Integer traineeId;
	private Integer subjectId;
	private String subjectName;
	private Float theory;
	private Float practice;
	private String status;

	public ScoreDto(Score score) {
		super();
		this.traineeId = score.getScorePK().getUserId();
		this.subjectId = score.getSubject().getId();
		this.subjectName = score.getSubject().getName();
		this.theory = score.getTheory();
		this.practice = score.getPractice();
		this.status = score.getTheory() == null ? score.getPractice() == null ? "In Coming" : "In Progress"
				: score.getPractice() == null ? "In Progress" : "Done";
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

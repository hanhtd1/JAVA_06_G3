package fa.training.dto;

import fa.training.models.Score;

public class ScoreDto {
  private Integer id;
  private Integer traineeId;
  private Integer subjectId;
  private String subjectName;
  private String name;
  public Integer getSubjectId() {
    return subjectId;
  }
  public void setSubjectId(Integer subjectId) {
    this.subjectId = subjectId;
  }
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
    this.status = score.getTheory()!=null&&score.getPractice()!=null? "Done":"In progress";
  }
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
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
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
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

package fa.training.dtos;

public class AdminScoreDto {
  private Integer subjectId;
  private Integer userId;
  private Float theory;
  private Float practice;
  
  public AdminScoreDto(Integer subjectId, Integer userId, Float theory, Float practice) {
    super();
    this.subjectId = subjectId;
    this.userId = userId;
    this.theory = theory;
    this.practice = practice;
  }
  public Integer getSubjectId() {
    return subjectId;
  }
  public void setSubjectId(Integer subjectId) {
    this.subjectId = subjectId;
  }
  public Integer getUserId() {
    return userId;
  }
  public void setUserId(Integer userId) {
    this.userId = userId;
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

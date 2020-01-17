package fa.training.dtos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

/**
 * @author TrangDM2
 *
 */
public class AdminScoreDto {
  
  @NotNull
  private Integer subjectId;
  
  private Integer userId;

  @Range(max = 10)
  private Float theory;
  
  @Range(max = 10)
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

  @Override
  public String toString() {
    return "AdminScoreDto [subjectId=" + subjectId + ", userId=" + userId + ", theory=" + theory + ", practice="
        + practice + "]";
  }

}

package fa.training.dto;

import java.time.LocalDate;

/**
 * @author TrangDM2
 *
 */
public class AttendanceDto {
  private Integer id;
  private LocalDate date;
  private String type;
  private String note;
  private Integer UserId;
  
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  
  public LocalDate getDate() {
    return date;
  }
  public void setDate(LocalDate date) {
    this.date = date;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getNote() {
    return note;
  }
  public void setNote(String note) {
    this.note = note;
  }
  public Integer getUserId() {
    return UserId;
  }
  public void setUserId(Integer userId) {
    UserId = userId;
  }

  public AttendanceDto(LocalDate date, String type, String note, Integer userId) {
    super();
    this.date = date;
    this.type = type;
    this.note = note;
    UserId = userId;
  }
  public AttendanceDto() {
    super();
  }
  @Override
  public String toString() {
    return "AttendanceDto [id=" + id + ", date=" + date + ", type=" + type + ", note=" + note + ", UserId=" + UserId
        + "]";
  }
  
}

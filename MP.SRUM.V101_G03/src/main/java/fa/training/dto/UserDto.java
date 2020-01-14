package fa.training.dto;

import java.time.LocalDate;

import fa.training.models.Clazz;
import fa.training.models.User;

/**
 * @author TrangDM2
 *
 */
public class UserDto {
  private int id;
  private String name;
  private String email;
  private String account;
  private String phone;
  private LocalDate birthDay;
  private String className;
  private int classId;

  public UserDto(User user, Clazz clazz) {
    super();
    this.id = user.getId();
    this.name = user.getFirstName() + " " + user.getLastName();
    this.email = user.getEmail();
    this.account = user.getAccount();
    this.phone = user.getPhone();
    this.birthDay = user.getBirthDay();
    if(clazz!=null) {
      this.classId = clazz.getId();
      this.className = clazz.getName();
    }
  }

  public LocalDate getBirthDay() {
    return birthDay;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setBirthDay(LocalDate birthDay) {
    this.birthDay = birthDay;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public int getClassId() {
    return classId;
  }

  public void setClassId(int classId) {
    this.classId = classId;
  }

}

package fa.training.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fa.training.utils.Constant;

/**
 *
 * @author TrangDM2
 */
@Entity
@Table(name = "Uzer")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Id")
  private Integer id;

  @Pattern(regexp = Constant.REGEX_NAME, message = Constant.VALID_NAME_MESSAGE)
  @Column(name = "FirstName", nullable = false)
  private String firstName;

  @Pattern(regexp = Constant.REGEX_NAME, message = Constant.VALID_NAME_MESSAGE)
  @Column(name = "LastName", nullable = false)
  private String lastName;

  @NotBlank
  @Pattern(regexp = Constant.REGEX_PHONE_NUMBER, message = Constant.VALID_PHONE_MESSAGE)
  @Column(name = "Phone", nullable = false)
  private String phone;

  @NotBlank
  @Email
  @Column(name = "Email", unique = true, nullable = false)
  private String email;

  @Column(name = "Password", nullable = false)
  private String password;

  @NotBlank
  @Column(name = "Account", unique = true, nullable = false)
  private String account;

  @Column(name = "BirthDay", nullable = false)
  private LocalDate birthDay;

  @Column(name = "Role", nullable = false)
  private String role;

  @Column(name = "Gender", nullable = false)
  private String gender;

  @Column(name = "Status", nullable = false)
  private String status;

  @Column(name = "LastLogin", nullable = true)
  private LocalDateTime lastLogin;

  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private Set<Attendance> attendanceList;

  @JsonIgnore
  @ManyToMany(mappedBy = "userList", cascade = CascadeType.MERGE)
  private Set<Clazz> clazzList;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.MERGE, mappedBy = "user")
  private List<Score> scoreList;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.MERGE, mappedBy = "user")
  private List<Feedback> feedbackList;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.MERGE, mappedBy = "trainer")
  private List<ReviewTrainee> reviewTraineeList;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.MERGE, mappedBy = "trainee")
  private List<ReviewTrainee> reviewTraineeList1;

  public User(String firstName, String lastName, String phone, String email, String password, String account,
      LocalDate birthDay, String role, String gender, String status) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.email = email;
    this.password = password;
    this.account = account;
    this.birthDay = birthDay;
    this.role = role;
    this.gender = gender;
    this.status = status;
  }

  public User() {
  }

  public User(Integer id) {
    this.id = id;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public LocalDate getBirthDay() {
    return birthDay;
  }

  public void setBirthDay(LocalDate birthDay) {
    this.birthDay = birthDay;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public LocalDateTime getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(LocalDateTime lastLogin) {
    this.lastLogin = lastLogin;
  }

  public Set<Attendance> getAttendanceList() {
    return attendanceList;
  }

  public void setAttendanceList(Set<Attendance> attendanceList) {
    this.attendanceList = attendanceList;
  }

  public Set<Clazz> getClazzList() {
    return clazzList;
  }

  public void setClazzList(Set<Clazz> clazzList) {
    this.clazzList = clazzList;
  }

  public List<Score> getScoreList() {
    return scoreList;
  }

  public void setScoreList(List<Score> scoreList) {
    this.scoreList = scoreList;
  }

  public List<Feedback> getFeedbackList() {
    return feedbackList;
  }

  public void setFeedbackList(List<Feedback> feedbackList) {
    this.feedbackList = feedbackList;
  }

  public List<ReviewTrainee> getReviewTraineeList() {
    return reviewTraineeList;
  }

  public void setReviewTraineeList(List<ReviewTrainee> reviewTraineeList) {
    this.reviewTraineeList = reviewTraineeList;
  }

  public List<ReviewTrainee> getReviewTraineeList1() {
    return reviewTraineeList1;
  }

  public void setReviewTraineeList1(List<ReviewTrainee> reviewTraineeList1) {
    this.reviewTraineeList1 = reviewTraineeList1;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof User)) {
      return false;
    }
    User other = (User) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", email="
        + email + ", password=" + password + ", account=" + account + ", birthDay=" + birthDay + ", role=" + role
        + ", gender=" + gender + ", status=" + status + "]";
  }
}

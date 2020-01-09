package fa.training.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "Phone")
	private String phone;

	@Column(name = "Email")
	private String email;

	@Column(name = "Password")
	private String password;

	@Column(name = "Account")
	private String account;

	@Column(name = "BirthDay")
	private LocalDate birthDay;

	@Column(name = "Role")
	private String role;

	@Column(name = "Gender")
	private String gender;

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

	@Column(name = "Status")
	private String status;

	@JsonIgnore
	@ManyToMany(mappedBy = "userList")
	private List<Attendance> attendanceList;

	@JsonIgnore
	@ManyToMany(mappedBy = "userList", fetch = FetchType.EAGER)
	private List<Clazz> clazzList;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Score> scoreList;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Feedback> feedbackList;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "trainer")
	private List<ReviewTrainee> reviewTraineeList;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "trainee")
	private List<ReviewTrainee> reviewTraineeList1;

	public User() {
	}

	public User(Integer id) {
		this.id = id;
	}

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

	public List<Attendance> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(List<Attendance> attendanceList) {
		this.attendanceList = attendanceList;
	}

	public List<Clazz> getClazzList() {
		return clazzList;
	}

	public void setClazzList(List<Clazz> clazzList) {
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
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ ", email=" + email + ", password=" + password + ", account=" + account + ", birthDay=" + birthDay
				+ ", role=" + role + ", gender=" + gender + ", status=" + status + "]";
	}
}

package fa.training.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author TrangDM2
 */
@Entity
@Table(name = "Subject")
public class Subject implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id")
	private Integer id;

	@Column(name = "Name")
	private String name;

	@Column(name = "Code")
	private String code;

	@Column(name = "Duration")
	private float duration;

	@Column(name = "Status")
	private String status;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
	private List<Score> scoreList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
	private List<Feedback> feedbackList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
	private List<ClazzSubject> clazzSubjectList;

	public Subject() {
	}

	public Subject(Integer id) {
		this.id = id;
	}

	public Subject(Integer id, String name, String code, float duration) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.duration = duration;
	}

	public Subject(String name, String code, float duration) {
		super();
		this.name = name;
		this.code = code;
		this.duration = duration;
	}

	public Subject(String name, String code, float duration, String status) {
		super();
		this.name = name;
		this.code = code;
		this.duration = duration;
		this.status = status;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
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

	public List<ClazzSubject> getClazzSubjectList() {
		return clazzSubjectList;
	}

	public void setClazzSubjectList(List<ClazzSubject> clazzSubjectList) {
		this.clazzSubjectList = clazzSubjectList;
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
		if (!(object instanceof Subject)) {
			return false;
		}
		Subject other = (Subject) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.Subject[ id=" + id + " ]";
	}

}

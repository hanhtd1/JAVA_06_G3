package fa.training.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author TrangDM2
 */
@Entity
@Table(name = "Score")
@NamedQueries({ @NamedQuery(name = "Score.findAll", query = "SELECT s FROM Score s") })
public class Score implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected ScorePK scorePK;

	@Column(name = "Theory")
	private Float theory;

	@Column(name = "Practice")
	private Float practice;

	@JsonIgnore
	@JoinColumn(name = "SubjectId", referencedColumnName = "Id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Subject subject;

	@JsonIgnore
	@JoinColumn(name = "UserId", referencedColumnName = "Id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private User user;

	public Score() {
	}

	public Score(ScorePK scorePK) {
		this.scorePK = scorePK;
	}

	public Score(ScorePK scorePK, Float theory, Float practice) {
		this.scorePK = scorePK;
		this.theory = theory;
		this.practice = practice;
	}

	public Score(int subjectId, int userId) {
		this.scorePK = new ScorePK(subjectId, userId);
	}

	public ScorePK getScorePK() {
		return scorePK;
	}

	public void setScorePK(ScorePK scorePK) {
		this.scorePK = scorePK;
	}

	public Float getTheory() {
		return theory;
	}

	public void setTheory(float value) {
		this.theory = value;
	}

	public Float getPractice() {
		return practice;
	}

	public void setPractice(float value) {
		this.practice = value;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (scorePK != null ? scorePK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Score)) {
			return false;
		}
		Score other = (Score) object;
		if ((this.scorePK == null && other.scorePK != null)
				|| (this.scorePK != null && !this.scorePK.equals(other.scorePK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Score [scorePK=" + scorePK + ", name=" + theory + ", value=" + practice + "]";
	}

}

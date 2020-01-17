package fa.training.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author TrangDM2
 */
@Entity
@Table(name = "Feedback")
public class Feedback implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected FeedbackPK feedbackPK;

	@Column(name = "Content")
	private String content;

	@JoinColumn(name = "SubjectId", referencedColumnName = "Id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Subject subject;

	@JoinColumn(name = "UserId", referencedColumnName = "Id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private User user;

	public Feedback() {
	}

	public Feedback(FeedbackPK feedbackPK) {
		this.feedbackPK = feedbackPK;
	}

	public Feedback(FeedbackPK feedbackPK, String content) {
		this.feedbackPK = feedbackPK;
		this.content = content;
	}

	public Feedback(int subjectId, int userId) {
		this.feedbackPK = new FeedbackPK(subjectId, userId);
	}

	public FeedbackPK getFeedbackPK() {
		return feedbackPK;
	}

	public void setFeedbackPK(FeedbackPK feedbackPK) {
		this.feedbackPK = feedbackPK;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		hash += (feedbackPK != null ? feedbackPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Feedback)) {
			return false;
		}
		Feedback other = (Feedback) object;
		if ((this.feedbackPK == null && other.feedbackPK != null)
				|| (this.feedbackPK != null && !this.feedbackPK.equals(other.feedbackPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Feedback [content=" + content + "]";
	}

}

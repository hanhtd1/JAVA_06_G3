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
@Table(name = "ClazzSubject")
public class ClazzSubject implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected ClazzSubjectPK clazzSubjectPK;

	@Column(name = "Status")
	private String status;

	@JoinColumn(name = "ClazzId", referencedColumnName = "Id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Clazz clazz;

	@JoinColumn(name = "SubjectId", referencedColumnName = "Id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Subject subject;

	public ClazzSubject() {
	}

	public ClazzSubject(ClazzSubjectPK clazzSubjectPK) {
		this.clazzSubjectPK = clazzSubjectPK;
	}

	public ClazzSubject(ClazzSubjectPK clazzSubjectPK, String status) {
		this.clazzSubjectPK = clazzSubjectPK;
		this.status = status;
	}

	public ClazzSubject(int clazzId, int subjectId) {
		this.clazzSubjectPK = new ClazzSubjectPK(clazzId, subjectId);
	}

	public ClazzSubjectPK getClazzSubjectPK() {
		return clazzSubjectPK;
	}

	public void setClazzSubjectPK(ClazzSubjectPK clazzSubjectPK) {
		this.clazzSubjectPK = clazzSubjectPK;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (clazzSubjectPK != null ? clazzSubjectPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ClazzSubject)) {
			return false;
		}
		ClazzSubject other = (ClazzSubject) object;
		if ((this.clazzSubjectPK == null && other.clazzSubjectPK != null)
				|| (this.clazzSubjectPK != null && !this.clazzSubjectPK.equals(other.clazzSubjectPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ClazzSubject[ clazzSubjectPK=" + clazzSubjectPK + " ]";
	}

}

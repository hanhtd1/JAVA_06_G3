package fa.training.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author TrangDM2
 */
@Embeddable
public class ClazzSubjectPK implements Serializable {

	/**
	  * 
	  */
	private static final long serialVersionUID = 1L;

	@Column(name = "ClazzId")
	private Integer clazzId;

	@Column(name = "SubjectId")
	private Integer subjectId;

	public ClazzSubjectPK() {
	}

	public ClazzSubjectPK(Integer clazzId, Integer subjectId) {
		this.clazzId = clazzId;
		this.subjectId = subjectId;
	}

	public Integer getClazzId() {
		return clazzId;
	}

	public void setClazzId(Integer clazzId) {
		this.clazzId = clazzId;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) clazzId;
		hash += (int) subjectId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ClazzSubjectPK)) {
			return false;
		}
		ClazzSubjectPK other = (ClazzSubjectPK) object;
		if (this.clazzId != other.clazzId) {
			return false;
		}
		if (this.subjectId != other.subjectId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ClazzSubjectPK[ clazzId=" + clazzId + ", subjectId=" + subjectId + " ]";
	}
}

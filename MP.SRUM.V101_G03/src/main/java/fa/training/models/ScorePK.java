package fa.training.models;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author TrangDM2
 */
@Embeddable
public class ScorePK implements Serializable{

    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    @Column(name = "Id")
    private int id;

    @Column(name = "SubjectId")
    private int subjectId;

    @Column(name = "UserId")
    private int userId;

    public ScorePK() {
    }

    public ScorePK(int id, int subjectId, int userId) {
        this.id = id;
        this.subjectId = subjectId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) subjectId;
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScorePK)) {
            return false;
        }
        ScorePK other = (ScorePK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.subjectId != other.subjectId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ScorePK[ id=" + id + ", subjectId=" + subjectId + ", userId=" + userId + " ]";
    }
    
}

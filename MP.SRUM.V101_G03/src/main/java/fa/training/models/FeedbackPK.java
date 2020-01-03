package fa.training.models;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author TrangDM2
 */
@Embeddable
public class FeedbackPK implements Serializable{

    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    @Column(name = "Id")
    private Integer id;

    @Column(name = "SubjectId")
    private Integer subjectId;

    @Column(name = "UserId")
    private Integer userId;

    public FeedbackPK() {
    }

    public FeedbackPK(Integer id, Integer subjectId, Integer userId) {
        this.id = id;
        this.subjectId = subjectId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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
        if (!(object instanceof FeedbackPK)) {
            return false;
        }
        FeedbackPK other = (FeedbackPK) object;
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
        return "com.FeedbackPK[ id=" + id + ", subjectId=" + subjectId + ", userId=" + userId + " ]";
    }
    
}

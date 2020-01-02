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
@Table(name = "ReviewTrainee")
public class ReviewTrainee implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReviewTraineePK reviewTraineePK;

    @Column(name = "Type")
    private String type;

    @Column(name = "Content")
    private String content;
    
    @JoinColumn(name = "TrainerId", referencedColumnName = "Id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User trainer;
    
    @JoinColumn(name = "TraineeId", referencedColumnName = "Id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User trainee;

    public ReviewTrainee() {
    }

    public ReviewTrainee(ReviewTraineePK reviewTraineePK) {
        this.reviewTraineePK = reviewTraineePK;
    }

    public ReviewTrainee(ReviewTraineePK reviewTraineePK, String type, String content) {
        this.reviewTraineePK = reviewTraineePK;
        this.type = type;
        this.content = content;
    }

    public ReviewTrainee(int trainerId, int traineeId) {
        this.reviewTraineePK = new ReviewTraineePK(trainerId, traineeId);
    }

    public ReviewTraineePK getReviewTraineePK() {
        return reviewTraineePK;
    }

    public void setReviewTraineePK(ReviewTraineePK reviewTraineePK) {
        this.reviewTraineePK = reviewTraineePK;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return trainer;
    }

    public void setUser(User user) {
        this.trainer = user;
    }

    public User getUser1() {
        return trainee;
    }

    public void setUser1(User user1) {
        this.trainee = user1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewTraineePK != null ? reviewTraineePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReviewTrainee)) {
            return false;
        }
        ReviewTrainee other = (ReviewTrainee) object;
        if ((this.reviewTraineePK == null && other.reviewTraineePK != null) || (this.reviewTraineePK != null && !this.reviewTraineePK.equals(other.reviewTraineePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ReviewTrainee[ reviewTraineePK=" + reviewTraineePK + " ]";
    }
    
}

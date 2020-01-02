package fa.training.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author TrangDM2
 */
@Embeddable
public class ReviewTraineePK implements Serializable{

    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    @Column(name = "TrainerId")
    private int trainerId;

    @Column(name = "TraineeId")
    private int traineeId;

    public ReviewTraineePK() {
    }

    public ReviewTraineePK(int trainerId, int traineeId) {
        this.trainerId = trainerId;
        this.traineeId = traineeId;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public int getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(int traineeId) {
        this.traineeId = traineeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) trainerId;
        hash += (int) traineeId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReviewTraineePK)) {
            return false;
        }
        ReviewTraineePK other = (ReviewTraineePK) object;
        if (this.trainerId != other.trainerId) {
            return false;
        }
        if (this.traineeId != other.traineeId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ReviewTraineePK[ trainerId=" + trainerId + ", traineeId=" + traineeId + " ]";
    }
    
}

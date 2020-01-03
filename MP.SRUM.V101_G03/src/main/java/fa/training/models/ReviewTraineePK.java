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
    private Integer trainerId;

    @Column(name = "TraineeId")
    private Integer traineeId;

    public ReviewTraineePK() {
    }

    public ReviewTraineePK(Integer trainerId, Integer traineeId) {
        this.trainerId = trainerId;
        this.traineeId = traineeId;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public Integer getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(Integer traineeId) {
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

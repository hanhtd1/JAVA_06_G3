package fa.training.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author TrangDM2
 */
@Entity
@Table(name = "Clazz")
public class Clazz implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "Name", unique = true)
  private String name;

  @Column(name = "openDate")
  private LocalDate openDate;

  @Column(name = "Note")
  private String note;

  @Column(name = "Category")
  private String category;

  @Column(name = "Status")
  private String status;
  @JoinTable(name = "UserClazz", joinColumns = {
      @JoinColumn(name = "ClazzId", referencedColumnName = "Id") }, inverseJoinColumns = {
          @JoinColumn(name = "UserId", referencedColumnName = "Id") })
  @ManyToMany(fetch = FetchType.EAGER)
  private List<User> userList;
  @OneToMany(mappedBy = "clazz")
  private List<ClazzSubject> clazzSubjectList;

  public Clazz() {
  }

  public Clazz(Integer id) {
    this.id = id;
  }

  public Clazz(Integer id, String name, LocalDate openLocalDate, String note, String category, String status) {
    this.id = id;
    this.name = name;
    this.openDate = openLocalDate;
    this.note = note;
    this.category = category;
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

  public LocalDate getOpenDate() {
    return openDate;
  }

  public void setOpenDate(LocalDate openDate) {
    this.openDate = openDate;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<User> getUserList() {
    return userList;
  }

  public void setUserList(List<User> userList) {
    this.userList = userList;
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
    if (!(object instanceof Clazz)) {
      return false;
    }
    Clazz other = (Clazz) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Clazz [id=" + id + ", name=" + name + ", openDate=" + openDate + ", note=" + note + ", category=" + category
        + ", status=" + status + "]";
  }

}

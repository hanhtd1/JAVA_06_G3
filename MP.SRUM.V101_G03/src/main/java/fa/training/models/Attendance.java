/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fa.training.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author TrangDM2
 */
@Entity
@Table(name = "Attendance")
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @Column(name = "Type")
    private String type;
    
    @Column(name = "Note")
    private String note;
    
    @JoinTable(name = "AttendanceUser", joinColumns = {
        @JoinColumn(name = "AttendanceDate", referencedColumnName = "Date")}, inverseJoinColumns = {
        @JoinColumn(name = "UserId", referencedColumnName = "Id")})
    @ManyToMany
    private List<User> userList;

    public Attendance() {
    }

    public Attendance(Date date) {
        this.date = date;
    }

    public Attendance(Date date, String type, String note) {
        this.date = date;
        this.type = type;
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (date != null ? date.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attendance)) {
            return false;
        }
        Attendance other = (Attendance) object;
        if ((this.date == null && other.date != null) || (this.date != null && !this.date.equals(other.date))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Attendance[ date=" + date + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fa.training.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author TrangDM2
 */
@Entity
@Table(name = "Attendance")
public class Attendance implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "Date")
	private LocalDate date;

	@Column(name = "Type")
	private String type;

	@Column(name = "Note")
	private String note;

	@JoinColumn(name = "UserId", referencedColumnName = "Id")
  @ManyToOne(optional = false)
	private User user;

	public Attendance() {
	}

	public Attendance(Integer id) {
		super();
		this.id = id;
	}

	public Attendance(LocalDate date, String type, String note) {
		this.date = date;
		this.type = type;
		this.note = note;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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

	public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "Attendance [id=" + id + ", date=" + date + ", type=" + type + ", note=" + note + ", user=" + user + "]";
  }

}

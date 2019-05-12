package com.social.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "time_sheet")
public class TimeSheet extends AuditModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "task_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long task_id;

	@Column(name = "id")
	private String id;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe")
	private Date startDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe")
	private Date endDate;

	@Basic
	@Temporal(TemporalType.DATE)
	private java.util.Date utilDate;

	@Basic
	@Temporal(TemporalType.TIME)
	private java.util.Date utilTime;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date utilTimestamp;

	@Column(name = "hours")
	private String hours;

	@Column(name = "duration")
	private String duration;

	@Column(name = "minutes")
	private String minutes;

	@Column(name = "days")
	private String days;
	
	@Column(name = "profile")
	private String profile;

	public long getTask_id() {
		return task_id;
	}

	public void setTask_id(long task_id) {
		this.task_id = task_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public java.util.Date getUtilDate() {
		return utilDate;
	}

	public void setUtilDate(java.util.Date utilDate) {
		this.utilDate = utilDate;
	}

	public java.util.Date getUtilTime() {
		return utilTime;
	}

	public void setUtilTime(java.util.Date utilTime) {
		this.utilTime = utilTime;
	}

	public java.util.Date getUtilTimestamp() {
		return utilTimestamp;
	}

	public void setUtilTimestamp(java.util.Date utilTimestamp) {
		this.utilTimestamp = utilTimestamp;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getMinutes() {
		return minutes;
	}

	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}
	 
	
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public TimeSheet(){}
	public TimeSheet(String id, Date startDate, Date endDate, Date utilDate,
			Date utilTime, Date utilTimestamp, String hours, String duration,
			String minutes, String days,String profile) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.utilDate = utilDate;
		this.utilTime = utilTime;
		this.utilTimestamp = utilTimestamp;
		this.hours = hours;
		this.duration = duration;
		this.minutes = minutes;
		this.days = days;
		this.profile = profile;
	}
	

}
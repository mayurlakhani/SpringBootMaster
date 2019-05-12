package com.social.entities;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "orders")
public class Order implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long order_id;
	
	@Column(name = "id")
	private String id;
	
	@Column(name = "client_name")
	private String client_name;

	@Column(name = "project_type")
	private String project_type;

	@Column(name = "purchase_order")
	private String purchase_order;

	@Column(name = "project_number")
	private String project_number;

	@Column(name = "budget")
	private float budget;

	@Column(name = "rate")
	private float rate;

	@Column(name = "start_date")
	private String start_date;

	@Column(name = "end_date")
	private String end_date;
	
	@Column(name = "profile")
	private String profile;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
	private Clients client;

	public Order(){}
	
	public Order(String id, String client_name, String project_type,
			String purchase_order, String project_number, float budget,
			float rate, String start_date, String end_date, String profile,
			Clients client) {
		super();
		this.id = id;
		this.client_name = client_name;
		this.project_type = project_type;
		this.purchase_order = purchase_order;
		this.project_number = project_number;
		this.budget = budget;
		this.rate = rate;
		this.start_date = start_date;
		this.end_date = end_date;
		this.profile = profile;
		this.client = client;
	}

	public long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getProject_type() {
		return project_type;
	}

	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}

	public String getPurchase_order() {
		return purchase_order;
	}

	public void setPurchase_order(String purchase_order) {
		this.purchase_order = purchase_order;
	}

	public String getProject_number() {
		return project_number;
	}

	public void setProject_number(String project_number) {
		this.project_number = project_number;
	}

	public float getBudget() {
		return budget;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public Clients getClient() {
		return client;
	}

	public void setClient(Clients client) {
		this.client = client;
	}


}

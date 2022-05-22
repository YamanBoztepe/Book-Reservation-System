package com.yeditepe.bookrez.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable {
	private Integer r_id;
	private Integer b_id;
	private Integer u_id;
	private LocalDate start_date;
	private LocalDate end_date;
	
	public Reservation() {
		super();
	}
	
	

	public Reservation(Integer r_id, Integer b_id, Integer u_id, LocalDate start_date, LocalDate end_date) {
		super();
		this.r_id = r_id;
		this.b_id = b_id;
		this.u_id = u_id;
		this.start_date = start_date;
		this.end_date = end_date;
	}



	public Integer getR_id() {
		return r_id;
	}

	public void setR_id(Integer r_id) {
		this.r_id = r_id;
	}

	public Integer getB_id() {
		return b_id;
	}

	public void setB_id(Integer b_id) {
		this.b_id = b_id;
	}

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}
	
	
}

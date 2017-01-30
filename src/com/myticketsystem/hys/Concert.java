package com.myticketsystem.hys;

import java.util.Date;

public class Concert {
	private int Id;
	private String Title;
	private String Description;
	private Date cDate;
	private Double Price;
	private String Venue;
	
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double price) {
		Price = price;
	}

	public String getVenue() {
		return Venue;
	}

	public void setVenue(String venue) {
		Venue = venue;
	}
	
	public Concert() {

	}

	public Concert(Concert c) {
		this.Title = c.Title;
		this.Description = c.Description;
		this.Price = c.Price;
		this.cDate = c.cDate;
		this.Venue = c.Venue;
		this.Id = c.Id;
	}

	public Concert(String title, String description, Double price, Date cdate, String venue, int id) {
		this.Title = title;
		this.Description = description;
		this.Price = price;
		this.cDate = cdate;
		this.Venue = venue;
		this.Id = id;
	}
}

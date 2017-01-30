package com.myticketsystem.hys;

public class Transaction {

	private String customerName;
	private String concertDate;
	private String concertName;
	private String concertSeat;
	private String concertVenue;
	private Double ticketPrice;
	private String ticketType;
	private String transactionDate; // could be Date, but who cares, we can format the string to it
	private String transactionId;
	
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getConcertDate() {
		return concertDate;
	}
	public void setConcertDate(String concertDate) {
		this.concertDate = concertDate;
	}
	public String getConcertName() {
		return concertName;
	}
	public void setConcertName(String concertName) {
		this.concertName = concertName;
	}
	public String getConcertSeat() {
		return concertSeat;
	}
	public void setConcertSeat(String concertSeat) {
		this.concertSeat = concertSeat;
	}
	public String getConcertVenue() {
		return concertVenue;
	}
	public void setConcertVenue(String concertVenue) {
		this.concertVenue = concertVenue;
	}
	public Double getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public String getTicketType() {
		return ticketType;
	}
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Transaction(String customerName, String concertDate, String concertName, String concertSeat,
			String concertVenue, Double ticketPrice, String ticketType, String transactionDate, String transactionId) {
		this.customerName = customerName;
		this.concertDate = concertDate;
		this.concertName = concertName;
		this.concertSeat = concertSeat;
		this.concertVenue = concertVenue;
		this.ticketPrice = ticketPrice;
		this.ticketType = ticketType;
		this.transactionDate = transactionDate;
		this.transactionId = transactionId;
	}
	
	public Transaction(){
		
	}

}

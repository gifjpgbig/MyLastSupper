package com.projectdemo.order.bean;

public class NotificationRequest {
	private Note note;
	private String token;
	
	
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}

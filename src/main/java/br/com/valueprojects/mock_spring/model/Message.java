package br.com.valueprojects.mock_spring.model;

public class Message {
	private String text;
	
	public Message(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}

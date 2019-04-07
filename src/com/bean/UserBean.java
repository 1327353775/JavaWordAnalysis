package com.bean;

public class UserBean {
	private String name;
	private String sentence;
	public String getSentence() {
		return sentence;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	private String content;
	private String word;
	private String category;
}

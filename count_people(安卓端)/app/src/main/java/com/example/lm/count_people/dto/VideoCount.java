package com.example.lm.count_people.dto;

public class VideoCount {

	private Integer id;
    private Integer currentPeople;
    private String currentTime;
    
    
    
	public VideoCount() {
		super();
	}
	
	public VideoCount(Integer id, Integer currentPeople, String currentTime) {
		super();
		this.id = id;
		this.currentPeople = currentPeople;
		this.currentTime = currentTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCurrentPeople() {
		return currentPeople;
	}

	public void setCurrentPeople(Integer currentPeople) {
		this.currentPeople = currentPeople;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	
	
	@Override
	public String toString() {
		return "VideoCount [id=" + id + ", currentPeople=" + currentPeople + ", currentTime=" + currentTime + "]";
	}
    
    
	
    
    
}

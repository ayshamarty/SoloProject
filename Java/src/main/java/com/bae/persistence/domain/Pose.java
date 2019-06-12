package com.bae.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pose {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long poseID;
	private String poseName;
	private String poseDifficulty;
	
	public Pose() {
		super();
	}
	
	public Pose(long poseID, String poseName, String poseDifficulty) {
		super();
		this.poseID = poseID;
		this.poseName = poseName;
		this.poseDifficulty = poseDifficulty;
	}
	//getters and setters
	public long getPoseID() {
		return poseID;
	}
	public void setPoseID(long poseID) {
		this.poseID = poseID;
	}
	public String getPoseName() {
		return poseName;
	}
	public void setPoseName(String poseName) {
		this.poseName = poseName;
	}
	public String getPoseDifficulty() {
		return poseDifficulty;
	}
	public void setPoseDifficulty(String poseDifficulty) {
		this.poseDifficulty = poseDifficulty;
	}
	

}
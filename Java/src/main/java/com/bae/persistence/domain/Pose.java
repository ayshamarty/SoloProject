package com.bae.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pose {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int poseID;
	private String poseName;
	private String poseDifficulty;
	

	public Pose() {
		super();
	}

	public Pose(int poseID, String poseName, String poseDifficulty) {
		super();
		this.poseID = poseID;
		this.poseName = poseName;
		this.poseDifficulty = poseDifficulty;
	}

	// getters and setters
	public int getPoseID() {
		return poseID;
	}

	public void setPoseID(int poseID) {
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

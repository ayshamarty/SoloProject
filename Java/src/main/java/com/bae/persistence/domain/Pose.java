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
	private String poseInfo;
	private String poseIMG;
	

	public Pose() {
		super();
	}

	public Pose(int poseID, String poseName, String poseDifficulty) {
		super();
		this.poseID = poseID;
		this.poseName = poseName;
		this.poseDifficulty = poseDifficulty;
	}
	
	public Pose(int poseID, String poseName, String poseDifficulty, String poseInfo) {
		super();
		this.poseID = poseID;
		this.poseName = poseName;
		this.poseDifficulty = poseDifficulty;
		this.poseInfo = poseInfo;
	}
	
	public Pose(int poseID, String poseName, String poseDifficulty, String poseInfo, String poseIMG) {
		super();
		this.poseID = poseID;
		this.poseName = poseName;
		this.poseDifficulty = poseDifficulty;
		this.poseInfo = poseInfo;
		this.poseIMG = poseIMG;
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

	public String getPoseInfo() {
		return poseInfo;
	}

	public void setPoseInfo(String poseInfo) {
		this.poseInfo = poseInfo;
	}

}

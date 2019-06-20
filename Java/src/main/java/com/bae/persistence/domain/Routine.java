package com.bae.persistence.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Routine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int routineID;
	private String routineName;
	private String routineType;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "routine_poses", joinColumns = @JoinColumn(name = "routine_id"), inverseJoinColumns = @JoinColumn(name = "pose_id"))
	private Set<Pose> poseSet = new HashSet<>();

	public Routine() {
		super();
	}

	public Routine(int routineID, String routineName, String routineType) {
		super();
		this.routineID = routineID;
		this.routineName = routineName;
		this.routineType = routineType;
	}

	public int getRoutineID() {
		return routineID;
	}

	public void setRoutineID(int routineID) {
		this.routineID = routineID;
	}

	public Set<Pose> getPoseSet() {
		return poseSet;
	}

	public void setPoseSet(Set<Pose> poseSet) {
		this.poseSet = poseSet;
	}

	public String getRoutineName() {
		return routineName;
	}

	public void setRoutineName(String routineName) {
		this.routineName = routineName;
	}

	public String getRoutineType() {
		return routineType;
	}

	public void setRoutineType(String routineType) {
		this.routineType = routineType;
	}

}

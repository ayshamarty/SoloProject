package com.bae.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Routine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int routineID;
	private String routineName;
	private String routineType;
	// @OneToMany(cascade=CascadeType.PERSIST)
	// @JoinColumn(name = "RoutineID")
	// private Set<Pose> poseSet = new HashSet<>();

	public Routine() {
		super();
	}

	public int getRoutineID() {
		return routineID;
	}

	public void setRoutineID(int routineID) {
		this.routineID = routineID;
	}

	// public Set<Pose> getPoseSet() {
	// return poseSet;
	// }
	//
	// public void setPoseSet(Set<Pose> poseSet) {
	// this.poseSet = poseSet;
	// }

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

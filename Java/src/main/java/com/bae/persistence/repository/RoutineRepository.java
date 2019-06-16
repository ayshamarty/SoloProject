package com.bae.persistence.repository;

public interface RoutineRepository {
	String getAllRoutines();

	String getARoutine(int routineID);

	String createRoutine(String routine);

	String deleteRoutine(int poseID);

	String updateRoutine(int poseID, String pose);
	
	String addToRoutine();
	
	String removeFromRoutine();

}

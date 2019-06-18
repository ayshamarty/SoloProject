package com.bae.persistence.repository;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;
import com.bae.persistence.domain.Routine;
import com.bae.util.Constants;
import com.bae.util.JSONUtil;

@Alternative
public class RoutineMapRepository implements RoutineRepository {
	private Map<Integer, Routine> routineMap = new HashMap<>();
	private JSONUtil json = new JSONUtil();

	
	
	@Override
	public String getAllRoutines() {
		return json.getJSONForObject(routineMap);
	}

	@Override
	public String getARoutine(int routineID) {
		Routine routineToGet =routineMap.get(routineID);
		return json.getJSONForObject(routineToGet);
	}

	@Override
	public String createRoutine(String routine) {
		Routine routineToAdd = json.getObjectForJSON(routine, Routine.class);
		routineMap.put(routineToAdd.getRoutineID(), routineToAdd);
		return Constants.SUCCESSMESSAGE;
	}

	@Override
	public String deleteRoutine(int routineID) {
		routineMap.remove(routineID);
		return Constants.SUCCESSMESSAGE;

	}

	@Override
	public String updateRoutine(int routineID, String routine) {
		Routine routineToUpdate = json.getObjectForJSON(routine, Routine.class);
		routineMap.replace(routineID, routineToUpdate);
		return Constants.SUCCESSMESSAGE;
		} 
	

	@Override
	public String addToRoutine(int routineID, String poseID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeFromRoutine(int routineID, String poseID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	// getters and setters
	public Map<Integer, Routine> getRoutineMap() {
		return routineMap;
	}

	public void setRoutineMap(Map<Integer, Routine> routineMap) {
		this.routineMap = routineMap;
	}
}

package com.bae.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.bae.persistence.domain.Routine;
import com.bae.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class RoutineDatabaseRepository implements RoutineRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil json;

	// Read
	@Override
	public String getAllRoutines() {
		Query query = manager.createQuery("Select a FROM Routine a");
		Collection<Routine> routines = (Collection<Routine>) query.getResultList();
		return json.getJSONForObject(routines);
	}

	@Override
	public String getARoutine(int routineID) {
		return json.getJSONForObject(manager.find(Routine.class, routineID));
	}

	// Create
	@Override
	@Transactional(REQUIRED)
	public String createRoutine(String routine) {
		Routine routineToAdd = json.getObjectForJSON(routine, Routine.class);
		manager.persist(routineToAdd);
		return "{\"message\": \"routine successfully added\"}";
	}

	// Delete
	@Override
	@Transactional(REQUIRED)
	public String deleteRoutine(int routineID) {
		if (manager.contains(manager.find(Routine.class, routineID))) {
			manager.remove(manager.find(Routine.class, routineID));
			return "{\"message\": \"routine successfully deleted\"}";
		} else {
			return "{\"message\": \"routine does not exist\"}";
		}

	}

	// Update
	@Override
	@Transactional(REQUIRED)
	public String updateRoutine(int routineID, String routine) {

		Routine routineToUpdate = manager.find(Routine.class, routineID);
		Routine updatedRoutine = json.getObjectForJSON(routine, Routine.class);

		if (routineToUpdate != null) {
			routineToUpdate.setRoutineName(updatedRoutine.getRoutineName());
			routineToUpdate.setRoutineType(updatedRoutine.getRoutineType());
			manager.persist(routineToUpdate);
			return "{\"message\": \"routine successfully updated\"}";
		} else {
			return "{\"message\": \"routine does not exist\"}";
		}

	}

	// Getters and setters
	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public JSONUtil getJson() {
		return json;
	}

	public void setJson(JSONUtil json) {
		this.json = json;
	}

	// @Override
	// public String addToRoutine(int routineID, int poseID) {
	// Routine poseToAdd = manager.find(Pose.class, poseID);
	// Routine routineToEdit = manager.find(Routine.class, routineID);
	// routineToEdit.getRoutineSet().add(poseToAdd);
	// manager.persist(routineToEdit);
	// return "{\"message\": \"pose successfully added to routine\"}";
	// }
	//
	// @Override
	// public String removeFromRoutine(int routineID, int poseID) {
	// Routine poseToRemove = manager.find(Pose.class, poseID);
	// Routine routineToEdit = manager.find(Routine.class, routineID);
	// routineToEdit.getRoutineSet().remove(poseToRemove);
	// manager.persist(routineToEdit);
	// return "{\"message\": \"pose successfully removed from routine\"}";
	// }

}

package com.bae.business;

import javax.inject.Inject;

import com.bae.persistence.repository.RoutineRepository;

public class RoutineServiceImplementation implements RoutineService {

	@Inject
	RoutineRepository routineRepo;

	@Override
	public String getAllRoutines() {
		return routineRepo.getAllRoutines();
	}

	@Override
	public String getARoutine(int routineID) {
		return routineRepo.getARoutine(routineID);
	}

	@Override
	public String createRoutine(String routine) {
		return routineRepo.createRoutine(routine);
	}

	@Override
	public String deleteRoutine(int routineID) {
		return routineRepo.deleteRoutine(routineID);
	}

	@Override
	public String updateRoutine(int routineID, String routine) {
		return routineRepo.updateRoutine(routineID, routine);
	}

	@Override
	public String addToRoutine(int routineID, int poseID) {
		return routineRepo.addToRoutine(routineID, poseID);
	}

	@Override
	public String removeFromRoutine(int routineID, int poseID) {
		return routineRepo.removeFromRoutine(routineID, poseID);
	}

}

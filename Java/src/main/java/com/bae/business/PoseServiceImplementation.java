package com.bae.business;

import javax.inject.Inject;

import com.bae.persistence.repository.PoseRepository;

public class PoseServiceImplementation implements PoseService {

	@Inject
	PoseRepository poseRepo;

	@Override
	public String getAllPoses() {
		return poseRepo.getAllPoses();
	}

	@Override
	public String getAPose(int poseID) {
		return poseRepo.getAPose(poseID);
	}

	@Override
	public String createPose(String pose) {
		return poseRepo.createPose(pose);
	}

	@Override
	public String deletePose(int poseID) {
		return poseRepo.deletePose(poseID);
	}

	@Override
	public String updatePose(int poseID, String pose) {
		return poseRepo.updatePose(poseID, pose);
	}

}

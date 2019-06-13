package com.bae.persistence.repository;

public interface PoseRepository {
	String getAllPoses();

	String getAPose(int poseID);

	String createPose(String pose);

	String deletePose(int poseID);

	String updatePose(int poseID, String pose);

}

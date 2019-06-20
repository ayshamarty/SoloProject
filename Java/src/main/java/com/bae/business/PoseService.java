package com.bae.business;

public interface PoseService {

	String getAllPoses();

	String getAPose(int poseID);

	String createPose(String pose);

	String deletePose(int poseID);

	String updatePose(int poseID, String pose);

}

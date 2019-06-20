package com.bae.persistence.repository;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;

import com.bae.persistence.domain.Pose;
import com.bae.util.JSONUtil;

@Alternative
public class PoseMapRepository implements PoseRepository {
	private Map<Integer, Pose> poseMap = new HashMap<>();
	private JSONUtil json = new JSONUtil();

	// implemented behaviours

	public String getAllPoses() {

		return json.getJSONForObject(poseMap);
	}

	public String getAPose(int poseID) {
		Pose poseToGet = poseMap.get(poseID);
		return json.getJSONForObject(poseToGet);
	}

	@Override
	public String createPose(String pose) {
		Pose poseToAdd = json.getObjectForJSON(pose, Pose.class);
		poseMap.put(poseToAdd.getPoseID(), poseToAdd);
		return "pose successfully created";
	}

	@Override
	public String deletePose(int poseID) {
		poseMap.remove(poseID);
		return "pose successfully removed";

	}

	@Override
	public String updatePose(int poseID, String pose) {
		Pose poseToUpdate = json.getObjectForJSON(pose, Pose.class);
		poseMap.replace(poseID, poseToUpdate);
		return "pose successfully updated";
	}

	// getters and setters
	public Map<Integer, Pose> getPoseMap() {
		return poseMap;
	}

	public void setPoseMap(Map<Integer, Pose> poseMap) {
		this.poseMap = poseMap;
	}

}

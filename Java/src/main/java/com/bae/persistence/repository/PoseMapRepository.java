package com.bae.persistence.repository;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;

import com.bae.persistence.domain.Pose;
import com.bae.util.JSONUtil;

@Alternative
public class PoseMapRepository implements PoseRepository {
	private Map<Integer, Pose> poseMap = new HashMap<Integer, Pose>();
	private JSONUtil json = new JSONUtil();

	// implemented behaviours

	public String getAllPoses() {

		return json.getJSONForObject(poseMap);
	}

	public String getAPose(int poseID) {
		Pose poseToGet = poseMap.get(poseID);
		String poseString = json.getJSONForObject(poseToGet);
		return poseString;
	}

	// getters and setters
	public Map<Integer, Pose> getPoseMap() {
		return poseMap;
	}

	public void setPoseMap(Map<Integer, Pose> poseMap) {
		this.poseMap = poseMap;
	}

}

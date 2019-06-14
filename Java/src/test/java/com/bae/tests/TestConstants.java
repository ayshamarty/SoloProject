package com.bae.tests;

import com.bae.persistence.domain.Pose;
import com.bae.util.JSONUtil;

public class TestConstants {

	private Pose testPose1;
	private Pose testPose2;
	private JSONUtil json;
	private String successMessage = "Pose successfuly created";

	public Pose getTestPose1() {
		return testPose1;
	}

	public void setTestPose1(Pose testPose1) {
		this.testPose1 = testPose1;
	}

	public Pose getTestPose2() {
		return testPose2;
	}

	public void setTestPose2(Pose testPose2) {
		this.testPose2 = testPose2;
	}

	public JSONUtil getJson() {
		return json;
	}

	public void setJson(JSONUtil json) {
		this.json = json;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

}

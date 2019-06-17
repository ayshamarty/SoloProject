package com.bae.tests;

import com.bae.persistence.domain.Pose;
import com.bae.util.JSONUtil;

public class TestConstants {
	public static final JSONUtil JSON = new JSONUtil();

	public static final Pose TESTPOSE1 = new Pose(1, "Downward Dog", "Beginner");
	public static final String TESTPOSE1STR = JSON.getJSONForObject(TESTPOSE1);
	public static final Pose TESTPOSE2 = new Pose(2, "Crow", "Difficult");
	public static final String TESTPOSE2STR = JSON.getJSONForObject(TESTPOSE2);
	public static final String SUCCESSMESSAGE = "pose successfully created";
	

}
package com.bae.tests.util;

import com.bae.persistence.domain.Pose;

public class PoseDBTestConstants {
	public static final Pose TESTPOSE1 = new Pose(1, "Downward Dog", "Beginner");
	public static final String TESTPOSE1STR = "{\"poseID\":1,\"poseName\":\"Downward Dog\",\"poseDifficulty\":\"Beginner\"}";
	public static final Pose TESTPOSE2 = new Pose(2, "Crow", "Advanced");
	public static final String TESTPOSE2STR = "{\"poseID\":2,\"poseName\":\"Crow\",\"poseDifficulty\":\"Advanced\"}";
	public static final String FAILMESSAGE = "{\"message\": \"pose does not exist\"}";
	public static final String TESTPOSEUPDATESTR = "{\"poseID\":1,\"poseName\":\"Downward Dog\",\"poseDifficulty\":\"Intermediate\"}";

}

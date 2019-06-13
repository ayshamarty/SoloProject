package com.bae.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.bae.persistence.domain.Pose;
import com.bae.persistence.repository.PoseMapRepository;
import com.bae.util.JSONUtil;

public class PoseMapTest {
	private PoseMapRepository poseMapRepo = new PoseMapRepository();
	private Pose pose1;
	private Pose pose2;
	private JSONUtil json;

	@Before
	public void setup() {
		poseMapRepo = new PoseMapRepository();
		pose1 = new Pose(1, "Downward Dog", "Beginner");
		pose2 = new Pose(2, "Crow", "Difficult");
		json = new JSONUtil();
	}

	@Test
	public void getAllPosesEmptyTest() {
		assertEquals("{}", poseMapRepo.getAllPoses());

	}

	@Test
	public void getAllPoses1PoseTest() {
		poseMapRepo.getPoseMap().put(1, pose1);
		assertEquals("{\"1\":{\"poseID\":1,\"poseName\":\"Downward Dog\",\"poseDifficulty\":\"Beginner\"}}",
				poseMapRepo.getAllPoses());

	}

	@Test
	public void getAllPoses2PosesTest() {
		poseMapRepo.getPoseMap().put(1, pose1);
		poseMapRepo.getPoseMap().put(2, pose2);
		assertEquals(
				"{\"1\":{\"poseID\":1,\"poseName\":\"Downward Dog\",\"poseDifficulty\":\"Beginner\"},\"2\":{\"poseID\":2,\"poseName\":\"Crow\",\"poseDifficulty\":\"Difficult\"}}",
				poseMapRepo.getAllPoses());

	}

	@Test
	public void getPoseTest() {
		poseMapRepo.getPoseMap().put(1, pose1);
		poseMapRepo.getPoseMap().put(2, pose2);
		System.out.println(poseMapRepo.getAPose(1));
		assertEquals("{\"poseID\":2,\"poseName\":\"Crow\",\"poseDifficulty\":\"Difficult\"}", poseMapRepo.getAPose(2));
	}

	@Test
	public void getPoseEmptyMapTest() {
		System.out.println(poseMapRepo.getAPose(1));
		assertEquals("null", poseMapRepo.getAPose(2));
	}

}

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

	@Test
	public void createPoseTest() {
		String poseToAdd = json.getJSONForObject(pose1);
		assertEquals(poseMapRepo.createPose(poseToAdd), "Pose successfuly created");
		assertEquals(1, poseMapRepo.getPoseMap().size());

	}

	@Test
	public void createTwoPosesTest() {
		String poseToAdd1 = json.getJSONForObject(pose1);
		String poseToAdd2 = json.getJSONForObject(pose2);
		assertEquals(poseMapRepo.createPose(poseToAdd1), "Pose successfuly created");
		assertEquals(poseMapRepo.createPose(poseToAdd2), "Pose successfuly created");
		assertEquals(2, poseMapRepo.getPoseMap().size());

	}

	@Test
	public void deleteOnePoseTest() {

		poseMapRepo.getPoseMap().put(1, pose1);
		poseMapRepo.getPoseMap().put(2, pose2);

		poseMapRepo.deletePose(2);
		assertEquals(false, poseMapRepo.getPoseMap().containsKey(2));
		assertEquals(1, poseMapRepo.getPoseMap().size());

	}

	@Test
	public void deleteAllPosesTest() {

		poseMapRepo.getPoseMap().put(1, pose1);
		poseMapRepo.getPoseMap().put(2, pose2);

		poseMapRepo.deletePose(1);
		assertEquals(false, poseMapRepo.getPoseMap().containsKey(1));
		assertEquals(true, poseMapRepo.getPoseMap().containsKey(2));
		poseMapRepo.deletePose(2);
		assertEquals(false, poseMapRepo.getPoseMap().containsKey(2));
		assertEquals(0, poseMapRepo.getPoseMap().size());

	}

	@Test
	public void updatePose() {
		poseMapRepo.getPoseMap().put(1, pose1);
		poseMapRepo.updatePose(1, "{\"poseID\":1,\"poseDifficulty\":\"Intermediate\"}");
		System.out.println(poseMapRepo.getPoseMap().get(1).getPoseDifficulty());
		assertEquals("Intermediate", poseMapRepo.getPoseMap().get(1).getPoseDifficulty());
	}

}

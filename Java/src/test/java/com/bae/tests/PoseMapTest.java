package com.bae.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.bae.persistence.domain.Pose;
import com.bae.persistence.repository.PoseMapRepository;
import com.bae.util.JSONUtil;

public class PoseMapTest {
	private PoseMapRepository poseMapRepo = new PoseMapRepository();
	private Pose testPose1;
	private Pose testPose2;
	private JSONUtil json;
	private String successMessage = "Pose successfuly created";

	@Before
	public void setup() {
		poseMapRepo = new PoseMapRepository();
		testPose1 = new Pose(1, "Downward Dog", "Beginner");
		testPose2 = new Pose(2, "Crow", "Difficult");
		json = new JSONUtil();
	}

	@Test
	public void getAllPosesEmptyTest() {
		assertEquals("{}", poseMapRepo.getAllPoses());

	}

	@Test
	public void getAllPoses1PoseTest() {
		poseMapRepo.getPoseMap().put(1, testPose1);
		assertEquals("{\"1\":{\"poseID\":1,\"poseName\":\"Downward Dog\",\"poseDifficulty\":\"Beginner\"}}",
				poseMapRepo.getAllPoses());

	}

	@Test
	public void getAllPoses2PosesTest() {
		poseMapRepo.getPoseMap().put(1, testPose1);
		poseMapRepo.getPoseMap().put(2, testPose2);
		assertEquals(
				"{\"1\":{\"poseID\":1,\"poseName\":\"Downward Dog\",\"poseDifficulty\":\"Beginner\"},\"2\":{\"poseID\":2,\"poseName\":\"Crow\",\"poseDifficulty\":\"Difficult\"}}",
				poseMapRepo.getAllPoses());

	}

	@Test
	public void getPoseTest() {
		poseMapRepo.getPoseMap().put(1, testPose1);
		poseMapRepo.getPoseMap().put(2, testPose2);
		assertEquals("{\"poseID\":2,\"poseName\":\"Crow\",\"poseDifficulty\":\"Difficult\"}", poseMapRepo.getAPose(2));
	}

	@Test
	public void getPoseEmptyMapTest() {
		assertEquals("null", poseMapRepo.getAPose(2));
	}

	@Test
	public void createPoseTest() {
		String poseToAdd = json.getJSONForObject(testPose1);
		assertEquals(poseMapRepo.createPose(poseToAdd), successMessage);
		assertEquals(1, poseMapRepo.getPoseMap().size());

	}

	@Test
	public void createTwoPosesTest() {
		String poseToAdd1 = json.getJSONForObject(testPose1);
		String poseToAdd2 = json.getJSONForObject(testPose2);
		assertEquals(poseMapRepo.createPose(poseToAdd1), successMessage);
		assertEquals(poseMapRepo.createPose(poseToAdd2), successMessage);
		assertEquals(2, poseMapRepo.getPoseMap().size());

	}

	@Test
	public void deleteOnePoseTest() {

		poseMapRepo.getPoseMap().put(1, testPose1);
		poseMapRepo.getPoseMap().put(2, testPose2);

		poseMapRepo.deletePose(2);
		assertEquals(false, poseMapRepo.getPoseMap().containsKey(2));
		assertEquals(1, poseMapRepo.getPoseMap().size());

	}

	@Test
	public void deleteAllPosesTest() {

		poseMapRepo.getPoseMap().put(1, testPose1);
		poseMapRepo.getPoseMap().put(2, testPose2);

		poseMapRepo.deletePose(1);
		assertEquals(false, poseMapRepo.getPoseMap().containsKey(1));
		assertEquals(true, poseMapRepo.getPoseMap().containsKey(2));
		poseMapRepo.deletePose(2);
		assertEquals(false, poseMapRepo.getPoseMap().containsKey(2));
		assertEquals(0, poseMapRepo.getPoseMap().size());

	}

	@Test
	public void updatePose() {
		poseMapRepo.getPoseMap().put(1, testPose1);
		poseMapRepo.updatePose(1, "{\"poseID\":1,\"poseDifficulty\":\"Intermediate\"}");
		assertEquals("Intermediate", poseMapRepo.getPoseMap().get(1).getPoseDifficulty());
	}

}

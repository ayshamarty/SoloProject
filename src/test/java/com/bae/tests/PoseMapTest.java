package com.bae.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.bae.persistence.repository.PoseMapRepository;
import com.bae.tests.util.PoseMapTestConstants;

public class PoseMapTest {
	private PoseMapRepository poseMapRepo;

	@Before
	public void setup() {
		poseMapRepo = new PoseMapRepository();
	}

	@Test
	public void getAllPosesEmptyTest() {
		assertEquals("{}", poseMapRepo.getAllPoses());

	}

	@Test
	public void getAllPoses1PoseTest() {
		poseMapRepo.getPoseMap().put(1, PoseMapTestConstants.TESTPOSE1);
		assertEquals("{\"1\":" + PoseMapTestConstants.TESTPOSE1STR + "}", poseMapRepo.getAllPoses());

	}

	@Test
	public void getAllPoses2PosesTest() {
		poseMapRepo.getPoseMap().put(1, PoseMapTestConstants.TESTPOSE1);
		poseMapRepo.getPoseMap().put(2, PoseMapTestConstants.TESTPOSE2);
		assertEquals(
				"{\"1\":" + PoseMapTestConstants.TESTPOSE1STR + ",\"2\":" + PoseMapTestConstants.TESTPOSE2STR + "}",
				poseMapRepo.getAllPoses());
	}

	@Test
	public void getPoseTest() {
		poseMapRepo.getPoseMap().put(1, PoseMapTestConstants.TESTPOSE1);
		poseMapRepo.getPoseMap().put(2, PoseMapTestConstants.TESTPOSE2);
		assertEquals(PoseMapTestConstants.TESTPOSE2STR, poseMapRepo.getAPose(2));
	}

	@Test
	public void getPoseEmptyMapTest() {
		assertEquals("null", poseMapRepo.getAPose(2));
	}

	@Test
	public void createPoseTest() {
		assertEquals(poseMapRepo.createPose(PoseMapTestConstants.TESTPOSE1STR), PoseMapTestConstants.SUCCESSMESSAGE);
		assertEquals(1, poseMapRepo.getPoseMap().size());

	}

	@Test
	public void createTwoPosesTest() {
		assertEquals(poseMapRepo.createPose(PoseMapTestConstants.TESTPOSE1STR), PoseMapTestConstants.SUCCESSMESSAGE);
		assertEquals(poseMapRepo.createPose(PoseMapTestConstants.TESTPOSE2STR), PoseMapTestConstants.SUCCESSMESSAGE);
		assertEquals(2, poseMapRepo.getPoseMap().size());

	}

	@Test
	public void deleteOnePoseTest() {

		poseMapRepo.getPoseMap().put(1, PoseMapTestConstants.TESTPOSE1);
		poseMapRepo.getPoseMap().put(2, PoseMapTestConstants.TESTPOSE2);

		poseMapRepo.deletePose(2);
		assertEquals(false, poseMapRepo.getPoseMap().containsKey(2));
		assertEquals(1, poseMapRepo.getPoseMap().size());

	}

	@Test
	public void deleteAllPosesTest() {

		poseMapRepo.getPoseMap().put(1, PoseMapTestConstants.TESTPOSE1);
		poseMapRepo.getPoseMap().put(2, PoseMapTestConstants.TESTPOSE2);

		poseMapRepo.deletePose(1);
		assertEquals(false, poseMapRepo.getPoseMap().containsKey(1));
		assertEquals(true, poseMapRepo.getPoseMap().containsKey(2));
		poseMapRepo.deletePose(2);
		assertEquals(false, poseMapRepo.getPoseMap().containsKey(2));
		assertEquals(0, poseMapRepo.getPoseMap().size());

	}

	@Test
	public void updatePose() {
		poseMapRepo.getPoseMap().put(1, PoseMapTestConstants.TESTPOSE1);
		poseMapRepo.updatePose(1, PoseMapTestConstants.TESTPOSEUPDATESTR);
		assertEquals("Intermediate", poseMapRepo.getPoseMap().get(1).getPoseDifficulty());
	}

}

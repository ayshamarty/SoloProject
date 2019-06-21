package com.bae.tests.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bae.persistence.domain.Pose;

public class PoseTest {

	private Pose pose = new Pose(1, "testPose", "testDifficulty", "testInfo", "testIMG");

	@Test
	public void getPoseNameTest() {
		assertEquals("testPose", pose.getPoseName());
	}

	@Test
	public void getPoseIdTest() {
		assertEquals(1, pose.getPoseID());
	}

	@Test
	public void getPoseDifficultyTest() {
		assertEquals("testDifficulty", pose.getPoseDifficulty());
	}

	@Test
	public void getPoseInfoTest() {
		assertEquals("testInfo", pose.getPoseInfo());
	}

	@Test
	public void getPoseIMGTest() {
		assertEquals("testIMG", pose.getPoseIMG());
	}

}

package com.bae.tests;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.persistence.domain.Pose;
import com.bae.persistence.repository.PoseDatabaseRepository;
import com.bae.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class PoseDatabaseTest {

	@InjectMocks
	PoseDatabaseRepository repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil json;

	private List<Pose> poses;

	private Pose testPose1;
	private String testPose1Str;
	private Pose testPose2;
	private String testPose2Str;
	private String failedMessage;
	private String testPoseUpdateStr;

	@Before
	public void setup() {
		repo.setManager(manager);
		json = new JSONUtil();
		repo.setJson(json);
		poses = new ArrayList<>();
		testPose1 = new Pose(1, "Downward Dog", "Beginner");
		testPose1Str = "{\"poseID\":1,\"poseName\":\"Downward Dog\",\"poseDifficulty\":\"Beginner\"}";
		testPose2 = new Pose(2, "Crow", "Advanced");
		testPose2Str = "{\"poseID\":2,\"poseName\":\"Crow\",\"poseDifficulty\":\"Advanced\"}";
		failedMessage = "{\"message\": \"pose does not exist\"}";
		testPoseUpdateStr = "{\"poseID\":1,\"poseName\":\"Downward Dog\",\"poseDifficulty\":\"Intermediate\"}";

	}

	@Test
	public void testGetAllPoses() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		poses.add(testPose1);
		Mockito.when(query.getResultList()).thenReturn(poses);
		Assert.assertEquals("[" + testPose1Str + "]", repo.getAllPoses());
	}

	@Test
	public void testGetAPose() {
		Mockito.when(manager.find(Pose.class, 2)).thenReturn(testPose2);
		Assert.assertEquals(testPose2Str, repo.getAPose(2));
	}

	@Test
	public void testCreatePose() {
		String reply = repo.createPose(testPose1Str);
		Assert.assertEquals(reply, "{\"message\": \"pose successfully added\"}");
	}

	@Test
	public void testDeletePoseDoesNotExist() {
		String reply = repo.deletePose(1);
		Assert.assertEquals(reply, failedMessage);
	}

	@Test
	public void testDeletePoseExists() {

		Pose tempPose = new Pose();
		Mockito.when(manager.find(Pose.class, 1)).thenReturn(tempPose);
		Mockito.when(manager.contains(tempPose)).thenReturn(true);
		String reply = repo.deletePose(1);
		Assert.assertEquals(reply, "{\"message\": \"pose successfully deleted\"}");
	}

	@Test
	public void testUpdatePoseDoesntExist() {

		String reply = repo.updatePose(1, testPoseUpdateStr);
		Assert.assertEquals(reply, failedMessage);

	}

	@Test
	public void testUpdatePoseDoesExist() {

		Mockito.when(manager.find(Pose.class, 1)).thenReturn(testPose1);
		String reply = repo.updatePose(1, testPoseUpdateStr);
		Assert.assertEquals(reply, "{\"message\": \"pose successfully updated\"}");
	}

}

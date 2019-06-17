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
import com.bae.tests.util.PoseDBTestConstants;
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

	@Before
	public void setup() {
		repo.setManager(manager);
		json = new JSONUtil();
		repo.setJson(json);
		poses = new ArrayList<>();
	}

	@Test
	public void testGetAllPoses() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		poses.add(PoseDBTestConstants.TESTPOSE2);
		Mockito.when(query.getResultList()).thenReturn(poses);
		Assert.assertEquals("[" + PoseDBTestConstants.TESTPOSE2STR + "]", repo.getAllPoses());
	}

	@Test
	public void testGetAPose() {
		Mockito.when(manager.find(Pose.class, 2)).thenReturn(PoseDBTestConstants.TESTPOSE2);
		Assert.assertEquals(PoseDBTestConstants.TESTPOSE2STR, repo.getAPose(2));
	}

	@Test
	public void testCreatePose() {
		String reply = repo.createPose(PoseDBTestConstants.TESTPOSE1STR);
		Assert.assertEquals(reply, "{\"message\": \"pose successfully added\"}");
	}

	@Test
	public void testDeletePoseDoesNotExist() {
		String reply = repo.deletePose(1);
		Assert.assertEquals(reply, PoseDBTestConstants.FAILMESSAGE);
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

		String reply = repo.updatePose(1, PoseDBTestConstants.TESTPOSEUPDATESTR);
		Assert.assertEquals(reply, PoseDBTestConstants.FAILMESSAGE);

	}

	@Test
	public void testUpdatePoseDoesExist() {

		Mockito.when(manager.find(Pose.class, 1)).thenReturn(PoseDBTestConstants.TESTPOSE1);
		String reply = repo.updatePose(1, PoseDBTestConstants.TESTPOSEUPDATESTR);
		Assert.assertEquals(reply, "{\"message\": \"pose successfully updated\"}");
	}

}

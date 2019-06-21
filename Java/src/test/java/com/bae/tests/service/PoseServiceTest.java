package com.bae.tests.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.PoseServiceImplementation;
import com.bae.persistence.repository.PoseRepository;

@RunWith(MockitoJUnitRunner.class)
public class PoseServiceTest {

	private String mockValue;
	private String mockValue2;

	@InjectMocks
	private PoseServiceImplementation service;

	@Mock
	private PoseRepository repo;

	@Before
	public void setup() {
		mockValue = "testValue";
		mockValue2 = "testValue2";
	}

	@Test
	public void testGetAllPoses() {
		Mockito.when(repo.getAllPoses()).thenReturn(mockValue);
		Assert.assertEquals(mockValue, service.getAllPoses());
	}

	@Test
	public void testCreatePose() {
		Mockito.when(repo.createPose(mockValue2)).thenReturn(mockValue);
		Assert.assertEquals(mockValue, service.createPose(mockValue2));
		Mockito.verify(repo).createPose(mockValue2);
	}

	@Test
	public void testDeletePose() {
		Mockito.when(repo.deletePose(1)).thenReturn(mockValue);
		Assert.assertEquals(mockValue, service.deletePose(1));
		Mockito.verify(repo).deletePose(1);
	}

}
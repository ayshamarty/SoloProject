package com.bae.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.PoseService;

import come.bae.rest.PoseController;

@RunWith(MockitoJUnitRunner.class)
public class PoseControllerTest {

	private String mockValue;
	private String mockValue2;

	@InjectMocks
	private PoseController controller;
	// Class under test

	@Mock
	private PoseService service;

	@Before
	public void setup() {
		controller.setService(service);
		mockValue = "testValue";
	}

	@Test
	public void testGetAllPoses() {
		Mockito.when(service.getAllPoses()).thenReturn(mockValue);
		Assert.assertEquals(mockValue, controller.getAllPoses());
	}

	@Test
	public void testCreatePose() {
		Mockito.when(service.createPose(mockValue2)).thenReturn(mockValue);
		Assert.assertEquals(mockValue, controller.createPose(mockValue2));
		Mockito.verify(service).createPose(mockValue2);
	}

	@Test
	public void testDeletePose() {
		Mockito.when(service.deletePose(1)).thenReturn(mockValue);
		Assert.assertEquals(mockValue, controller.deletePose(1));
		Mockito.verify(service).deletePose(1);
	}

}

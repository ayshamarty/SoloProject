package com.bae.tests.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.RoutineServiceImplementation;
import com.bae.persistence.repository.RoutineRepository;

@RunWith(MockitoJUnitRunner.class)
public class RoutineServiceTest {

	private String mockValue;
	private String mockValue2;

	@InjectMocks
	private RoutineServiceImplementation service;

	@Mock
	private RoutineRepository repo;

	@Before
	public void setup() {
		mockValue = "testValue";
		mockValue2 = "testValue2";
	}

	@Test
	public void testGetAllRoutines() {
		Mockito.when(repo.getAllRoutines()).thenReturn(mockValue);
		Assert.assertEquals(mockValue, service.getAllRoutines());
	}

	@Test
	public void testCreateRoutine() {
		Mockito.when(repo.createRoutine(mockValue2)).thenReturn(mockValue);
		Assert.assertEquals(mockValue, service.createRoutine(mockValue2));
		Mockito.verify(repo).createRoutine(mockValue2);
	}

	@Test
	public void testDeleteRoutine() {
		Mockito.when(repo.deleteRoutine(1)).thenReturn(mockValue);
		Assert.assertEquals(mockValue, service.deleteRoutine(1));
		Mockito.verify(repo).deleteRoutine(1);
	}

}
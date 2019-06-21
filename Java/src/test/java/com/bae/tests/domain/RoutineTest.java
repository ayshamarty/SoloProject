package com.bae.tests.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bae.persistence.domain.Routine;

public class RoutineTest {

	private Routine routine = new Routine(1, "testRoutine", "testType");

	@Test
	public void getRoutineNameTest() {
		assertEquals("testRoutine", routine.getRoutineName());
	}

	@Test
	public void getRoutineIdTest() {
		assertEquals(1, routine.getRoutineID());
	}

	@Test
	public void getRoutineTypeTest() {
		assertEquals("testType", routine.getRoutineType());
	}
}

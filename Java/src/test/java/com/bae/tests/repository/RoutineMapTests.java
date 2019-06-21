package com.bae.tests.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.bae.persistence.repository.RoutineMapRepository;
import com.bae.tests.util.RoutineMapTestConstants;
import com.bae.util.Constants;

public class RoutineMapTests {
	private RoutineMapRepository routineMapRepo;

	@Before
	public void setup() {
		routineMapRepo = new RoutineMapRepository();
	}

	@Test
	public void getAllRoutinesEmptyTest() {
		assertEquals("{}", routineMapRepo.getAllRoutines());

	}

	@Ignore
	@Test
	public void getAllRoutines1RoutineTest() {
		routineMapRepo.getRoutineMap().put(1, RoutineMapTestConstants.TESTROUTINE1);
		assertEquals("{\"1\":" + RoutineMapTestConstants.TESTROUTINE1STR + "}", routineMapRepo.getAllRoutines());

	}

	@Ignore
	@Test
	public void getAllRoutines2RoutinesTest() {
		routineMapRepo.getRoutineMap().put(1, RoutineMapTestConstants.TESTROUTINE1);
		routineMapRepo.getRoutineMap().put(2, RoutineMapTestConstants.TESTROUTINE2);
		assertEquals("{\"1\":" + RoutineMapTestConstants.TESTROUTINE1STR + ",\"2\":"
				+ RoutineMapTestConstants.TESTROUTINE2STR + "}", routineMapRepo.getAllRoutines());
	}

	@Ignore
	@Test
	public void getRoutineTest() {
		routineMapRepo.getRoutineMap().put(1, RoutineMapTestConstants.TESTROUTINE1);
		routineMapRepo.getRoutineMap().put(2, RoutineMapTestConstants.TESTROUTINE2);
		assertEquals(RoutineMapTestConstants.TESTROUTINE2STR, routineMapRepo.getARoutine(2));
	}

	@Test
	public void getRoutineEmptyMapTest() {
		assertEquals("null", routineMapRepo.getARoutine(2));
	}

	@Test
	public void createRoutineTest() {
		assertEquals(routineMapRepo.createRoutine(RoutineMapTestConstants.TESTROUTINE1STR), Constants.SUCCESSMESSAGE);
		assertEquals(1, routineMapRepo.getRoutineMap().size());

	}

	@Test
	public void createTwoRoutinesTest() {
		assertEquals(routineMapRepo.createRoutine(RoutineMapTestConstants.TESTROUTINE1STR), Constants.SUCCESSMESSAGE);
		assertEquals(routineMapRepo.createRoutine(RoutineMapTestConstants.TESTROUTINE2STR), Constants.SUCCESSMESSAGE);
		assertEquals(2, routineMapRepo.getRoutineMap().size());

	}

	@Test
	public void deleteOneRoutineTest() {

		routineMapRepo.getRoutineMap().put(1, RoutineMapTestConstants.TESTROUTINE1);
		routineMapRepo.getRoutineMap().put(2, RoutineMapTestConstants.TESTROUTINE2);

		routineMapRepo.deleteRoutine(2);
		assertEquals(false, routineMapRepo.getRoutineMap().containsKey(2));
		assertEquals(1, routineMapRepo.getRoutineMap().size());

	}

	@Test
	public void deleteAllRoutinesTest() {

		routineMapRepo.getRoutineMap().put(1, RoutineMapTestConstants.TESTROUTINE1);
		routineMapRepo.getRoutineMap().put(2, RoutineMapTestConstants.TESTROUTINE2);

		routineMapRepo.deleteRoutine(1);
		assertEquals(false, routineMapRepo.getRoutineMap().containsKey(1));
		assertEquals(true, routineMapRepo.getRoutineMap().containsKey(2));
		routineMapRepo.deleteRoutine(2);
		assertEquals(false, routineMapRepo.getRoutineMap().containsKey(2));
		assertEquals(0, routineMapRepo.getRoutineMap().size());

	}

	@Test
	public void updateRoutinetest() {
		routineMapRepo.getRoutineMap().put(1, RoutineMapTestConstants.TESTROUTINE1);
		routineMapRepo.updateRoutine(1, RoutineMapTestConstants.TESTROUTINEUPDATESTR);
		assertEquals("Wind Down", routineMapRepo.getRoutineMap().get(1).getRoutineType());
	}

	@Test
	public void addToRoutineTest() {
		assertEquals(null, routineMapRepo.addToRoutine(1, 1));
	}

	@Test
	public void removeFromRoutineTest() {
		assertEquals(null, routineMapRepo.removeFromRoutine(1, 1));
	}

}

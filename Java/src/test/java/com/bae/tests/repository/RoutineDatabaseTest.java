package com.bae.tests.repository;

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

import com.bae.persistence.domain.Routine;
import com.bae.persistence.repository.RoutineDatabaseRepository;
import com.bae.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class RoutineDatabaseTest {

	@InjectMocks
	RoutineDatabaseRepository repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	@Mock
	private JSONUtil json;

	private List<Routine> routines;

	private Routine testRoutine1;
	private String testRoutine1Str;
	private Routine testRoutine2;
	private String testRoutine2Str;
	private String failedMessage;
	private String testRoutineUpdateStr;

	@Before
	public void setup() {
		repo.setManager(manager);
		json = new JSONUtil();
		repo.setJson(json);
		routines = new ArrayList<>();
		testRoutine1 = new Routine(1, "Sun Salutation", "Warm Up");
		testRoutine1Str = "[{\"routineID\":1,\"routineName\":\"Sun Salutation\",\"routineType\":\"Warm Up\",\"poseSet\":[]}]";
		testRoutine2 = new Routine(2, "Moon Salutation", "Cool Down");
		testRoutine2Str = "{\"routineID\":2,\"routineName\":\"Moon Salutation\",\"routineType\":\"Cool Down\",\"poseSet\":[]}";
		failedMessage = "{\"message\": \"routine does not exist\"}";
		testRoutineUpdateStr = "{\"routineID\":1,\"routineName\":\"Moon Salutation\",\"routineType\":\"Wind Down\"}";

	}

	@Test
	public void testGetAllRoutines() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		routines.add(testRoutine1);
		Mockito.when(query.getResultList()).thenReturn(routines);
		Assert.assertEquals(testRoutine1Str, repo.getAllRoutines());
	}

	@Test
	public void testGetARoutine() {
		Mockito.when(manager.find(Routine.class, 2)).thenReturn(testRoutine2);
		Assert.assertEquals(testRoutine2Str, repo.getARoutine(2));
	}

	@Test
	public void testCreateRoutine() {
		String reply = repo.createRoutine(testRoutineUpdateStr);
		Assert.assertEquals("{\"message\": \"routine successfully added\"}", reply);
	}

	@Test
	public void testDeleteRoutineDoesNotExist() {
		String reply = repo.deleteRoutine(1);
		Assert.assertEquals(failedMessage, reply);
	}

	@Test
	public void testDeleteRoutineExists() {

		Routine tempRoutine = new Routine();
		Mockito.when(manager.find(Routine.class, 1)).thenReturn(tempRoutine);
		Mockito.when(manager.contains(tempRoutine)).thenReturn(true);
		String reply = repo.deleteRoutine(1);
		Assert.assertEquals("{\"message\": \"routine successfully deleted\"}", reply);
	}

	@Test
	public void testUpdateRoutineDoesntExist() {

		String reply = repo.updateRoutine(1, testRoutineUpdateStr);
		Assert.assertEquals(failedMessage, reply);

	}

	@Test
	public void testUpdateRoutineDoesExist() {

		Mockito.when(manager.find(Routine.class, 1)).thenReturn(testRoutine1);
		String reply = repo.updateRoutine(1, testRoutineUpdateStr);
		Assert.assertEquals("{\"message\": \"routine successfully updated\"}", reply);
	}

	@Test
	public void testAddToRoutine() {
		Mockito.when(manager.find(Routine.class, 1)).thenReturn(testRoutine1);
		String reply = repo.addToRoutine(1, 1);
		Assert.assertEquals("{\"message\": \"pose successfully added to routine\"}", reply);
	}

	@Test
	public void testDeleteFromRoutine() {
		Mockito.when(manager.find(Routine.class, 1)).thenReturn(testRoutine1);
		String reply = repo.removeFromRoutine(1, 1);
		Assert.assertEquals("{\"message\": \"pose successfully removed from routine\"}", reply);
	}

}

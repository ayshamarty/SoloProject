package com.bae.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.bae.business.RoutineService;

@Path("routine")
public class RoutineController {

	@Inject
	RoutineService service;

	@Path("/getAllRoutines")
	@GET
	@Produces({ "application/json" })
	public String getAllRoutines() {
		return service.getAllRoutines();
	}

	@Path("/getARoutine/{routineID}")
	@GET
	@Produces({ "application/json" })
	public String getARoutine(@PathParam("routineID") int routineID) {
		return service.getARoutine(routineID);
	}

	@Path("/createRoutine")
	@POST
	@Produces({ "application/json" })
	public String createRoutine(String routine) {
		return service.createRoutine(routine);
	}

	@Path("/deleteRoutine/{routineID}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteRoutine(@PathParam("routineID") int routineID) {
		return service.deleteRoutine(routineID);
	}

	@Path("/updateRoutine/{routineID}")
	@PUT
	@Produces({ "application/json" })
	public String updateRoutine(@PathParam("routineID") int routineID, String routine) {
		return service.updateRoutine(routineID, routine);

	}

	public void setService(RoutineService service) {
		this.service = service;
	}

	@Path("/addToRoutine/{routineID}/{poseID}")
	@POST
	@Produces({ "application/json" })
	public String addToRoutine(@PathParam("routineID") int routineID, @PathParam("poseID") int poseID) {
		return service.addToRoutine(routineID, poseID);

	}

	@Path("/removeFromRoutine/{routineID}/{poseID}")
	@DELETE
	@Produces({ "application/json" })
	public String removeFromRoutine(@PathParam("routineID") int routineID, @PathParam("poseID") int poseID) {
		return service.removeFromRoutine(routineID, poseID);

	}
}

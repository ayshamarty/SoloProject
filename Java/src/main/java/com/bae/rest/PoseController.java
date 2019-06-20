package com.bae.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.bae.business.PoseService;

@Path("pose")
public class PoseController {
	@Inject
	PoseService service;

	@Path("/getAllPoses")
	@GET
	@Produces({ "application/json" })
	public String getAllPoses() {
		return service.getAllPoses();
	}

	@Path("/getAPose/{poseID}")
	@GET
	@Produces({ "application/json" })
	public String getAPose(@PathParam("poseID") int poseID) {
		return service.getAPose(poseID);
	}

	@Path("/createPose")
	@POST
	@Produces({ "application/json" })
	public String createPose(String pose) {
		return service.createPose(pose);
	}

	@Path("/deletePose/{poseID}")
	@DELETE
	@Produces({ "application/json" })
	public String deletePose(@PathParam("poseID") int poseID) {
		return service.deletePose(poseID);
	}

	@Path("/updatePose/{poseID}")
	@PUT
	@Produces({ "application/json" })
	public String updatePose(@PathParam("poseID") int poseID, String pose) {
		return service.updatePose(poseID, pose);

	}

	public void setService(PoseService service) {
		this.service = service;
	}

}

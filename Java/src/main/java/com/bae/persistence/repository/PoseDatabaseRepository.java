package com.bae.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.bae.persistence.domain.Pose;
import com.bae.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class PoseDatabaseRepository implements PoseRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil json;

	// Read
	@Override
	public String getAllPoses() {
		Query query = manager.createQuery("Select a FROM Pose a");
		Collection<Pose> poses = (Collection<Pose>) query.getResultList();

		return json.getJSONForObject(poses);
	}

	@Override
	public String getAPose(int poseId) {
		return json.getJSONForObject(manager.find(Pose.class, poseId));
	}

	// Create
	@Override
	@Transactional(REQUIRED)
	public String createPose(String pose) {
		Pose anAccount = json.getObjectForJSON(pose, Pose.class);
		manager.persist(anAccount);
		return "{\"message\": \"pose has been sucessfully added\"}";
	}

	// Delete
	@Override
	@Transactional(REQUIRED)
	public String deletePose(int poseID) {

		if (manager.contains(manager.find(Pose.class, poseID))) {

			manager.remove(manager.find(Pose.class, poseID));
		}
		return "{\"message\": \"pose successfully deleted\"}";
	}

	// Update
	@Override
	@Transactional(REQUIRED)
	public String updatePose(int poseID, String pose) {
		Pose poseToChange = json.getObjectForJSON(pose, Pose.class);
		Pose oldPose = manager.find(Pose.class, poseID);
		if (oldPose != null) {
			oldPose.setPoseName(poseToChange.getPoseName());
			oldPose.setPoseDifficulty(poseToChange.getPoseDifficulty());
			manager.persist(poseToChange);
		}
		return "{\"message\": \"pose successfully updated\"}";

	}

}

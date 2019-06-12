package com.bae.persistence.repository;

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

	@Override
	public String getAllPoses() {
		Query query = manager.createQuery("Select a FROM Pose a");
		Collection<Pose> accounts = (Collection<Pose>) query.getResultList();

		return json.getJSONForObject(accounts);
	}

	public String getAPose(long poseId) {
		return json.getJSONForObject(manager.find(Pose.class, poseId));
	}

}

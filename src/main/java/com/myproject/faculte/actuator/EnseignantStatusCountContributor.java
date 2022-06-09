package com.myproject.faculte.actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import com.myproject.faculte.service.EnseignantService;

@Component
public class EnseignantStatusCountContributor implements InfoContributor {
	
	@Autowired
	private EnseignantService enseignantService;

	@Override
	public void contribute(Builder builder) {
		Map<String,Long> enseignantStatusMap=new HashMap<>();
		enseignantStatusMap.put("Permanent", enseignantService.getEnseignantStatusCountByStatus("Permanent"));
		enseignantStatusMap.put("Contractuel", enseignantService.getEnseignantStatusCountByStatus("Contractuel"));
		enseignantStatusMap.put("Vacataire", enseignantService.getEnseignantStatusCountByStatus("Vacataire"));
		builder.withDetail("enseignantStatus", enseignantStatusMap);
		
	}

}

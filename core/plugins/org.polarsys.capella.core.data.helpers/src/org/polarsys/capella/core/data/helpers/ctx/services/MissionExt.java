/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.helpers.ctx.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionInvolvement;
import org.polarsys.capella.core.data.ctx.SystemComponent;

/**
 * Mission helpers
 * 
 */
public class MissionExt {

	/**
	 * This method retrieves the exploited capabilities.
	 * 
	 * @param mission
	 *            the mission whose exploited capability will be retrieved
	 * @return the exploited capabilities
	 */
	public static List<Capability> getExploitedCapabilities(Mission mission) {
		List<Capability> exploitedCapabilities = new ArrayList<>();
		List<CapabilityExploitation> exploitationSet = mission.getOwnedCapabilityExploitations();
		for (CapabilityExploitation exploit : exploitationSet) {
			Capability capability = exploit.getCapability();
			if(capability != null) {
			  exploitedCapabilities.add(capability);			  
			}
		}
		return exploitedCapabilities;
	}


	/**
	 * @param mission
	 *            The mission.
	 * @param systemComponent
	 *            The SystemComponent to remove.
	 */
  public static void removeInvolvedSystemComponent(Mission mission, SystemComponent component) {
    List<MissionInvolvement> missionInvolvementsToRemove = mission.getOwnedMissionInvolvements().stream()
        .filter(involvement -> involvement.getSystemComponent() == component).collect(Collectors.toList());
    for (MissionInvolvement involvement : missionInvolvementsToRemove) {
      involvement.destroy();
    }
  }

	/**
	 * @param mission
	 * @param useCases
	 */
	public static void removeExploitedCapabilities(Mission mission, List<Capability> useCases) {
		List<CapabilityExploitation> removedLinks = new ArrayList<>();

		for (Object capabilityExp : mission.getOwnedCapabilityExploitations()) {
			CapabilityExploitation capabilitExploitation = (CapabilityExploitation) capabilityExp;
			if (useCases.contains(capabilitExploitation.getCapability())) {
				removedLinks.add(capabilitExploitation);
			}
		}

		for (CapabilityExploitation capabilityExploitation : removedLinks) {
			capabilityExploitation.destroy();
		}
	}
}

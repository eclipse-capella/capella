/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.helpers.la.services.CapabilityRealizationPkgExt;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;

/**
 * CapabilityPkg helpers.
 * 
 */
public class CapabilityPkgExt {

	/**
	 * Get all the Capabilities in CapabilityPkg (and SUB PKGS) recursively
	 * 
	 * 
	 * @param capabilityPkg
	 *            the CapabilityPkg
	 * @return list of Capability
	 */
  public static List<Capability> getAllCapabilities(CapabilityPkg capabilityPkg) {
		List<Capability> result = new ArrayList<>(1);
		if (null != capabilityPkg) {
			result.addAll(capabilityPkg.getOwnedCapabilities());
			for (CapabilityPkg subPkg : capabilityPkg.getOwnedCapabilityPkgs()) {
				result.addAll(getAllCapabilities(subPkg));
			}
		}
		return result;
	}
	
	/**
   * Get all the AbstractCapability in AbstractCapabilityPkg (and SUB PKGS) recursively
   * 
   * 
   * @param abstractCapabilityPkg
   *            the AbstractCapabilityPkg
   * @return list of AbstractCapability
   */
  public static List<AbstractCapability> getAllAbstractCapabilities(AbstractCapabilityPkg abstractCapabilityPkg) {
    List<AbstractCapability> result = new ArrayList<>();

    // Case CapabilityPkg
    if (abstractCapabilityPkg instanceof CapabilityPkg) {
      result.addAll(getAllCapabilities((CapabilityPkg) abstractCapabilityPkg));
    }

    // Case CapabilityRealizationPkg
    if (abstractCapabilityPkg instanceof CapabilityRealizationPkg) {
      result.addAll(CapabilityRealizationPkgExt
          .getAllCapabilityRealization((CapabilityRealizationPkg) abstractCapabilityPkg));
    }

    // Case OperationalCapabilityPkg
    if (abstractCapabilityPkg instanceof OperationalCapabilityPkg) {
      OperationalCapabilityPkg operationalCapabilityPkg = (OperationalCapabilityPkg)abstractCapabilityPkg;
      result.addAll(operationalCapabilityPkg.getOwnedOperationalCapabilities());
      for (OperationalCapabilityPkg subPkg : operationalCapabilityPkg.getOwnedOperationalCapabilityPkgs()) {
        result.addAll(getAllAbstractCapabilities(subPkg));
      }
    }
    return result;
  }
}

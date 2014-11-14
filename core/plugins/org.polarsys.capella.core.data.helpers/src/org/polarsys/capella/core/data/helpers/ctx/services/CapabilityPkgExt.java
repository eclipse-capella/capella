/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.helpers.ctx.services;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;

/**
 * CapabilityPkg helpers.
 * 
 */
public class CapabilityPkgExt {

	/**
	 * Get all the Capabilities in CapabilityPkg (and SUB PKGS) recursively
	 * 
	 * 
	 * @param capabilityPkg_p
	 *            the CapabilityPkg
	 * @return list of Capability
	 */
	static public List<Capability> getAllCapabilities(CapabilityPkg capabilityPkg_p) {
		List<Capability> list = new ArrayList<Capability>(1);
		if (null != capabilityPkg_p) {
			list.addAll(capabilityPkg_p.getOwnedCapabilities());
			for (CapabilityPkg subPkg : capabilityPkg_p.getOwnedCapabilityPkgs()) {
				list.addAll(getAllCapabilities(subPkg));
			}
		}

		return list;
	}
}

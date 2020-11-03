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

package org.polarsys.capella.core.data.helpers.pa.services;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;

/**
 * PhysicalComponent helpers
 * 
 */
public class PhysicalComponentPkgExt {


	// the GetAll breaks when diagrams are presents.
	public static List<PhysicalComponent> getAllPhysicalComponents(PhysicalComponentPkg currentElement) {
		List<PhysicalComponent> components = new ArrayList<>(1);
		List<PhysicalComponentPkg> pkgs = currentElement.getOwnedPhysicalComponentPkgs();

		components.addAll(currentElement.getOwnedPhysicalComponents());

		for (PhysicalComponentPkg physicalComponentPkg : pkgs) {
			components.addAll(PhysicalComponentPkgExt.getAllPhysicalComponents(physicalComponentPkg));
		}

		return components;
	}

	public static boolean containsAssociation(PhysicalComponentPkg pcPkg, Part property, String targetPropertyName) {
		for (Association association : pcPkg.getOwnedAssociations()) {
			boolean flag = false;
			for (Property prop : association.getOwnedMembers()) {
				if (property.equals(prop)) {
					if (flag)
						return true;
					flag = true;
				}
				if (prop.getName().equals(targetPropertyName)) {
					if (flag)
						return true;
					flag = true;
				}
			}
		}
		return false;
	}

}

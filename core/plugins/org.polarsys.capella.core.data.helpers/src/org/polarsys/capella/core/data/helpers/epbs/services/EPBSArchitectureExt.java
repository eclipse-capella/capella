/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.epbs.services;

import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSArchitecturePkg;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;

/**
 * 
 */
public class EPBSArchitectureExt {
	/**
	 * Gets the parent system of a EPBS Architecture
	 * 
	 * @param currentEPBSArchitecture
	 * @return the parent {@link System}
	 */
	public static SystemEngineering getParentSystemEngineering(EPBSArchitecture currentEPBSArchitecture) {
		if (null != currentEPBSArchitecture) {
			Object container = currentEPBSArchitecture.eContainer();
			if (container instanceof EPBSArchitecturePkg) {
				container = ((EPBSArchitecturePkg) container).eContainer();
			}
			if (container instanceof SystemEngineering)
				return (SystemEngineering) container;
		}
		return null;
	}
}

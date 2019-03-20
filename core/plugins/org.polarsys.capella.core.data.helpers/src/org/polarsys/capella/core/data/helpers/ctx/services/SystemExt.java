/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.helpers.ctx.services;

import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement;

/**
 * System helpers
 * 
 */
public class SystemExt {

	/**
	 * This method checks whether the System interacts with the given
	 * CapabilityUseCase
	 * 
	 * @param system
	 *            the interacting System
	 * @param capabilityUseCase
	 *            the CapabilityUseCase
	 * @return true if the actor interacts with the CapabilityUseCase
	 */
  public static boolean hasInteraction(System system, Capability capabilityUseCase) {
		boolean isInteracting = false;

		for (SystemCapabilityInvolvement capabilitySupplierLink : system.getParticipationsInCapabilities()) {
			if (capabilitySupplierLink.getCapability().equals(capabilityUseCase)) {
				isInteracting = true;
				break;
			}
		}

		return isInteracting;
	}
}

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
package org.polarsys.capella.core.semantic.queries.basic.queries;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;

/**
 * Return outgoing physical links of current physical component and physical actor
 */
public class PhysicalComponent_OutgoingPhysicalLinks extends AbsAbstractPhysicalComponentPhysicalLink {

  /** 
	   * 
	   */
  public PhysicalComponent_OutgoingPhysicalLinks() {
    // do nothing
  }

  @Deprecated
  @Override
  public EObject getRequiredComponent(PhysicalLink physicalLink) {
    if (null != physicalLink) {
      return PhysicalLinkExt.getSourceComponent(physicalLink);
    }

    return null;
  }
}

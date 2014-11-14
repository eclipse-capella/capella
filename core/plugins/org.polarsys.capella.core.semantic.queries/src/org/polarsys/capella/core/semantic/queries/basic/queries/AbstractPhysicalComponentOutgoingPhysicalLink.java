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
package org.polarsys.capella.core.semantic.queries.basic.queries;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;

/**
 * Return outgoing physical links of current physical component and physical actor
 */
public class AbstractPhysicalComponentOutgoingPhysicalLink extends AbsAbstractPhysicalComponentPhysicalLink {

  /** 
	   * 
	   */
  public AbstractPhysicalComponentOutgoingPhysicalLink() {
    // do nothing
  }

  @Deprecated
  @Override
  public EObject getRequiredComponent(PhysicalLink physicalLink_p) {
    if (null != physicalLink_p) {
      return PhysicalLinkExt.getSourceComponent(physicalLink_p);
    }

    return null;
  }
}

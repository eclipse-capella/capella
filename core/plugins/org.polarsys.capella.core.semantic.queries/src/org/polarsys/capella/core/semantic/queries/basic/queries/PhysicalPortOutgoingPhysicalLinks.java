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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.information.Port;

/**
 * Return outgoing Physical Links of current Physical Port
 *
 */
public class PhysicalPortOutgoingPhysicalLinks extends  AbstractPhysicalPortPhysicalLinks{
	/**
	 * 
	 */
	public PhysicalPortOutgoingPhysicalLinks() {
    // do nothing
	}

  /**
   * @see org.polarsys.capella.core.semantic.queries.basic.queries.AbstractPhysicalPortPhysicalLinks#getSourcePortFromLink(org.polarsys.capella.core.data.pa.PhysicalLink)
   */
  @Override
  public Port getSourcePortFromLink(PhysicalLink link) {
    return PhysicalLinkExt.getTargetPort(link);
  }
}

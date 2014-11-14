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

import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * Return realized physicalPorts of current CI 
 * 
 */
public class CIRealizedPhysicalPorts extends AbstractCIRealizedPhysicalArtifacts {

	public CIRealizedPhysicalPorts() {
		// does nothing
	}

  /**
   * @see org.polarsys.capella.core.semantic.queries.basic.queries.AbstractCIRealizedPhysicalArtifacts#isValidElement(org.polarsys.capella.common.data.modellingcore.TraceableElement)
   */
  @Override
  public boolean isValidElement(TraceableElement element_p) {
    return element_p instanceof PhysicalPort;
  }
}

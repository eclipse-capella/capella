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
  public boolean isValidElement(TraceableElement element) {
    return element instanceof PhysicalPort;
  }
}

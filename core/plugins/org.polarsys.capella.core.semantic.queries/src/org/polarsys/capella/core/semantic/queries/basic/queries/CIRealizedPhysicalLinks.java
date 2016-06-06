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
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * Return realized PhysicalLinks of current CI 
 * 
 */
public class CIRealizedPhysicalLinks extends AbstractCIRealizedPhysicalArtifacts {

	public CIRealizedPhysicalLinks() {
		// does nothing
	}

  /**
   * @see org.polarsys.capella.core.semantic.queries.basic.queries.AbstractCIRealizedPhysicalArtifacts#isValidElement(org.polarsys.capella.common.data.modellingcore.TraceableElement)
   */
  @Override
  public boolean isValidElement(TraceableElement element) {
    return element instanceof PhysicalLink;
  }
}

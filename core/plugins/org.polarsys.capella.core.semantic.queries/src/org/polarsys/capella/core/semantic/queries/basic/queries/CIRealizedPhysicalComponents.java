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

import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * Return realized PhysicalComponents of current CI 
 * 
 */
public class CIRealizedPhysicalComponents extends AbstractCIRealizedPhysicalArtifacts {

	public CIRealizedPhysicalComponents() {
		// does nothing
	}

  /**
   * @see org.polarsys.capella.core.semantic.queries.basic.queries.AbstractCIRealizedPhysicalArtifacts#isValidElement(org.polarsys.capella.common.data.modellingcore.TraceableElement)
   */
  @Override
  public boolean isValidElement(TraceableElement element) {
    return element instanceof PhysicalComponent;
  }
}

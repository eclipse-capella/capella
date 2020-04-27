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

package org.polarsys.capella.core.data.helpers.cs.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathReference;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;

public class PhysicalPathReferenceHelper {
	private static PhysicalPathReferenceHelper instance;

	private PhysicalPathReferenceHelper() {
    // do nothing
	}

	public static PhysicalPathReferenceHelper getInstance(){
		if(instance == null)
			instance = new PhysicalPathReferenceHelper();
		return instance;
	}

	public Object doSwitch(PhysicalPathReference element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(CsPackage.Literals.PHYSICAL_PATH_REFERENCE__REFERENCED_PHYSICAL_PATH)) {
      ret = getReferencedPhysicalPath(element);
    }

		// no helper found... searching in super classes...
    if(null == ret) {
      ret = PhysicalPathInvolvementHelper.getInstance().doSwitch(element, feature);
    }

		return ret;
	}

  protected PhysicalPath getReferencedPhysicalPath(PhysicalPathReference element) {
    InvolvedElement elt = element.getInvolved();
    if (elt instanceof PhysicalPath) {
      return (PhysicalPath) elt;
    }
    return null;
  }
}

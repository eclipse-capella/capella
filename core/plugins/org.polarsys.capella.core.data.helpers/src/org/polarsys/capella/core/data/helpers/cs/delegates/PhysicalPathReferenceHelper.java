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

	public Object doSwitch(PhysicalPathReference element_p, EStructuralFeature feature_p) {
		Object ret = null;

    if (feature_p.equals(CsPackage.Literals.PHYSICAL_PATH_REFERENCE__REFERENCED_PHYSICAL_PATH)) {
      ret = getReferencedPhysicalPath(element_p);
    }

		// no helper found... searching in super classes...
    if(null == ret) {
      ret = PhysicalPathInvolvementHelper.getInstance().doSwitch(element_p, feature_p);
    }

		return ret;
	}

  protected PhysicalPath getReferencedPhysicalPath(PhysicalPathReference element_p) {
    InvolvedElement elt = element_p.getInvolved();
    if (elt instanceof PhysicalPath) {
      return (PhysicalPath) elt;
    }
    return null;
  }
}

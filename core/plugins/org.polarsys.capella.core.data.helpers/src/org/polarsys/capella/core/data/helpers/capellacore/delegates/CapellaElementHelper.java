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
package org.polarsys.capella.core.data.helpers.capellacore.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.requirement.Requirement;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.TraceableElementHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class CapellaElementHelper {
	private static CapellaElementHelper instance;

	private CapellaElementHelper() {
	  // do nothing
	}

	public static CapellaElementHelper getInstance() {
		if (instance == null)
			instance = new CapellaElementHelper();
		return instance;
	}

	public Object doSwitch(CapellaElement element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_REQUIREMENTS)) {
      ret = getAppliedRequirements(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = TraceableElementHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
	}

  protected List<Requirement> getAppliedRequirements(CapellaElement element_p) {
    List<Requirement> ret = new ArrayList<Requirement>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
      if (trace instanceof RequirementsTrace) {
        TraceableElement elt = ((RequirementsTrace) trace).getTargetElement();
        if (elt instanceof Requirement) {
          ret.add((Requirement) elt);
        }
      }
    }
    return ret;
  }
}

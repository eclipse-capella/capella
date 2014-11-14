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
package org.polarsys.capella.core.data.helpers.requirement.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.TraceHelper;
import org.polarsys.capella.core.data.requirement.RequirementPackage;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class RequirementsTraceHelper {
	private static RequirementsTraceHelper instance;

	private RequirementsTraceHelper() {//
	}

	public static RequirementsTraceHelper getInstance() {
		if (instance == null)
			instance = new RequirementsTraceHelper();
		return instance;
	}

	public Object doSwitch(RequirementsTrace element_p, EStructuralFeature feature_p) {
		Object ret = null;

    if (feature_p.equals(RequirementPackage.Literals.REQUIREMENTS_TRACE__SOURCE)) {
      ret = getSource(element_p);
    }
    else if (feature_p.equals(RequirementPackage.Literals.REQUIREMENTS_TRACE__TARGET)) {
      ret = getTarget(element_p);
    }

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = TraceHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

  protected TraceableElement getSource(RequirementsTrace element_p) {
    return element_p.getSourceElement();
  }

  protected TraceableElement getTarget(RequirementsTrace element_p) {
    return element_p.getTargetElement();
  }
}

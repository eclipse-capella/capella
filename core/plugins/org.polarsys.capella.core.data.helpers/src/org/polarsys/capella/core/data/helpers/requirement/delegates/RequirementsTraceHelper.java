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

	public Object doSwitch(RequirementsTrace element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(RequirementPackage.Literals.REQUIREMENTS_TRACE__SOURCE)) {
      ret = getSource(element);
    }
    else if (feature.equals(RequirementPackage.Literals.REQUIREMENTS_TRACE__TARGET)) {
      ret = getTarget(element);
    }

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = TraceHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected TraceableElement getSource(RequirementsTrace element) {
    return element.getSourceElement();
  }

  protected TraceableElement getTarget(RequirementsTrace element) {
    return element.getTargetElement();
  }
}

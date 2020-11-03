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

package org.polarsys.capella.core.data.helpers.capellacommon.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.TraceHelper;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class GenericTraceHelper {
	private static GenericTraceHelper instance;

	private GenericTraceHelper() {
	  //
	}

	public static GenericTraceHelper getInstance() {
		if (instance == null)
			instance = new GenericTraceHelper();
		return instance;
	}

	public Object doSwitch(GenericTrace element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(CapellacommonPackage.Literals.GENERIC_TRACE__SOURCE)) {
      ret = getSource(element);
    }
    else if (feature.equals(CapellacommonPackage.Literals.GENERIC_TRACE__TARGET)) {
      ret = getTarget(element);
    }

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = TraceHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected TraceableElement getSource(GenericTrace element) {
    return element.getSourceElement();
  }

  protected TraceableElement getTarget(GenericTrace element) {
    return element.getTargetElement();
  }
}

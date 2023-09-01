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

package org.polarsys.capella.core.data.helpers.capellacore.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.Trace;

public class NamespaceHelper {
	private static NamespaceHelper instance;

	private NamespaceHelper() {//
	}

	public static NamespaceHelper getInstance() {
		if (instance == null)
			instance = new NamespaceHelper();
		return instance;
	}

	public Object doSwitch(Namespace element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(CapellacorePackage.Literals.NAMESPACE__CONTAINED_GENERIC_TRACES)) {
      ret = getContainedGenericTraces(element);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected List<GenericTrace> getContainedGenericTraces(Namespace element) {
    List<GenericTrace> traces = new ArrayList<>();
    for (Trace trace : element.getOwnedTraces()) {
      if (trace instanceof GenericTrace) {
        traces.add((GenericTrace) trace);
      }
    }
    return traces;
  }

}

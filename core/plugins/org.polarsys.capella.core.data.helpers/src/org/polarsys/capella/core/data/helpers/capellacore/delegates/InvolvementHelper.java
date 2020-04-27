/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;

public class InvolvementHelper {
	private static InvolvementHelper instance;

	private InvolvementHelper() {//
	}

	public static InvolvementHelper getInstance() {
		if (instance == null)
			instance = new InvolvementHelper();
		return instance;
	}

	public Object doSwitch(Involvement element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(CapellacorePackage.Literals.INVOLVEMENT__INVOLVER)) {
      ret = getInvolver(element);
    } 
		
		// no helper found... searching in super classes...
		if(null == ret) {
			ret = RelationshipHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  private InvolverElement getInvolver(Involvement element) {
    EObject parent = element.eContainer();
    if (parent instanceof InvolverElement) {
      return (InvolverElement) parent;
    }
    return null;
  }
}

/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.capellacore.NamedRelationship;

public class NamedRelationshipHelper {
	private static NamedRelationshipHelper instance;

	private NamedRelationshipHelper() {//
	}

	public static NamedRelationshipHelper getInstance() {
		if (instance == null) {
			instance = new NamedRelationshipHelper();
		}
		return instance;
	}

	public Object doSwitch(NamedRelationship element, EStructuralFeature feature) {
		// no helper found... searching in super classes...
		Object ret = RelationshipHelper.getInstance().doSwitch(element, feature);

		if (null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}
}

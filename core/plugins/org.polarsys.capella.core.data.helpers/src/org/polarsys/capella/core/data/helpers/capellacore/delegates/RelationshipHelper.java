/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.helpers.capellacore.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.capellacore.Relationship;

public class RelationshipHelper {
	private static RelationshipHelper instance;

	private RelationshipHelper() {//
	}

	public static RelationshipHelper getInstance() {
		if (instance == null) {
			instance = new RelationshipHelper();
		}
		return instance;
	}

	public Object doSwitch(Relationship element, EStructuralFeature feature) {
		// no helper found... searching in super classes...
		return CapellaElementHelper.getInstance().doSwitch(element, feature);
	}
}

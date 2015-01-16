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
package org.polarsys.capella.core.model.helpers.intermodelInconsistencyDetection;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/** 
 * @author Erwan Brottier
 */
public class InterModelCycle implements InterModelInconsistency {

	protected List<EObject> involvedObjects;
	
	public InterModelCycle(List<EObject> involvedObjects) {
		this.involvedObjects = involvedObjects;
	}

	@Override
	public String getTypeName() {
		return "inter-model cycle"; //$NON-NLS-1$
	}

	@Override
	public List<EObject> getInvolvedObjects() {
		return involvedObjects;
	}

}

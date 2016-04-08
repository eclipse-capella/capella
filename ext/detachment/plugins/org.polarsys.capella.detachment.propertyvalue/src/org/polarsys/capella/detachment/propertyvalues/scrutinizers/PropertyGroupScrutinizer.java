/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.detachment.propertyvalues.scrutinizers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.kitalpha.model.common.scrutiny.interfaces.IScrutinize;

public class PropertyGroupScrutinizer implements IScrutinize<Map<EObject, Boolean>, Object> {
	
	private static final String PROPERTY_VALUE_GROUP_CLASS_NAME = "PropertyValueGroup";//$NON-NLS-1$
	
	private final Map<EObject, Boolean> propertyGroups = new HashMap<EObject, Boolean>();

	public PropertyGroupScrutinizer() {
	}

	@Override
	public void findIn(EObject eObject) {
		
		if (PROPERTY_VALUE_GROUP_CLASS_NAME.equals(eObject.eClass().getName())){
			propertyGroups.put(eObject, false);
		}
		
	}

	@Override
	public void findIn(Resource resource) {

	}

	@Override
	public Map<EObject, Boolean> getAnalysisResult() {
		return propertyGroups;
	}

	@Override
	public Object getFeedbackAnalysisMessages() {
		return null;
	}

}

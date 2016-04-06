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
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.kitalpha.model.common.scrutiny.interfaces.IScrutinize;

public class PropertyValuePackageScrutinizer implements IScrutinize<Map<EObject, Boolean>, Object> {
	
	private final String PROPERTY_VALUE_PKG_NAME = "PropertyValuePkg";
	private final Map<EObject, Boolean> propertyPkgs = new HashMap<EObject, Boolean>();

	@Override
	public void findIn(EObject eObject) {
		if (eObject instanceof PropertyValuePkg){// PROPERTY_VALUE_PKG_NAME.equals(eObject.eClass().getName())){
			propertyPkgs.put(eObject, false);
		}
	}

	@Override
	public void findIn(Resource resource) {
	}

	@Override
	public Map<EObject, Boolean> getAnalysisResult() {
		return propertyPkgs;
	}

	@Override
	public Object getFeedbackAnalysisMessages() {
		return null;
	}

}

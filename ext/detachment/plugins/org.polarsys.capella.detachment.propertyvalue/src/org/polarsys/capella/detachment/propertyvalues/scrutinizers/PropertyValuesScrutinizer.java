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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.kitalpha.model.common.scrutiny.interfaces.IScrutinize;

public class PropertyValuesScrutinizer implements IScrutinize<Map<EObject, Boolean>, Object> {
	
	private static final String ABSTRACT_PROPERTY_VALUE_CLASS_NAME = "AbstractPropertyValue";//$NON-NLS-1$
	
	private final Map<EObject, Boolean> propertyValues = new HashMap<EObject, Boolean>(); 

	public PropertyValuesScrutinizer() {
	}

	@Override
	public void findIn(EObject eObject) {
		if (isAbstractPropertyValueSubClass(eObject)){
			propertyValues.put(eObject, false);
		}
	}

	@Override
	public void findIn(Resource resource) {

	}

	@Override
	public Map<EObject, Boolean> getAnalysisResult() {
		return propertyValues;
	}

	@Override
	public Object getFeedbackAnalysisMessages() {
		return null;
	}
	
	private boolean isAbstractPropertyValueSubClass(EObject eObject) {
		EList<EClass> superTypes = eObject.eClass().getESuperTypes();
		for (EClass eClass : superTypes) {
			if (ABSTRACT_PROPERTY_VALUE_CLASS_NAME.equals(eClass.getName()))
				return true;
		}
		return false;
	}

}

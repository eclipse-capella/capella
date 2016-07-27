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
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.kitalpha.model.common.scrutiny.interfaces.IScrutinize;

public class PropertyValuesScrutinizer implements IScrutinize<Map<EObject, Boolean>, Object> {
	
	private static final String ABSTRACT_PROPERTY_VALUE_CLASS_NAME = "AbstractPropertyValue";//$NON-NLS-1$
	private static final String ENUMERATION_PROPERTY_VALUE_TYPE = "EnumerationPropertyType"; //$NON-NLS-1$
	
	private final Map<EObject, Boolean> propertyValues = new HashMap<EObject, Boolean>(); 

	public PropertyValuesScrutinizer() {
	}

	@Override
	public void findIn(EObject eObject) {
		if (isAbstractPropertyValueSubClass(eObject) || eObject instanceof PropertyValueGroup || eObject instanceof PropertyValuePkg || ENUMERATION_PROPERTY_VALUE_TYPE.equals(eObject.eClass().getName())){
			if (canCollected(eObject))
			propertyValues.put(eObject, false);
		}
	}

	private boolean canCollected(EObject eObject) {
		return true;
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
			String name = eClass.getName();
			if (ABSTRACT_PROPERTY_VALUE_CLASS_NAME.equals(name))
				return true;
		}
		return false;
	}

}

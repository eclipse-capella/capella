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
package org.polarsys.capella.detachment.propertyvalues.scrutinizers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
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
		if (isAbstractPropertyValueSubClass(eObject) || isPropertyValueGroup(eObject) 
				|| isPropertyValuePkg(eObject) || isPropertyEnum(eObject)){
			if (canBeCollected(eObject))
				propertyValues.put(eObject, false);
		}
	}

	private boolean isPropertyEnum(EObject eObject) {
		return !eObject.eIsProxy() && ENUMERATION_PROPERTY_VALUE_TYPE.equals(eObject.eClass().getName()) 
				&& !((EnumerationPropertyType)eObject).getName().equals("ProgressStatus"); //$NON-NLS-1$
	}

	private boolean isPropertyValuePkg(EObject eObject) {
		return eObject instanceof PropertyValuePkg;
	}

	private boolean isPropertyValueGroup(EObject eObject) {
		return eObject instanceof PropertyValueGroup;
	}

	private boolean canBeCollected(EObject eObject) {
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

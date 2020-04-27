/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.detachment.propertyvalues.ui.page;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;

public class PropertyValueContentProvider extends AdapterFactoryContentProvider {

	public PropertyValueContentProvider() {
		super(CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Collection){
			return (((Collection)inputElement)).toArray();
		}
		return null;
	}

	
	@Override
	public Object[] getChildren(Object parentElement) {
		Collection<Object> elt = new HashSet<Object>();
		
		if (parentElement instanceof EnumerationPropertyLiteral){
			elt.addAll(((EnumerationPropertyLiteral)parentElement).getOwnedEnumerationPropertyTypes());
			elt.addAll(((EnumerationPropertyLiteral)parentElement).getOwnedPropertyValueGroups());
			elt.addAll(((EnumerationPropertyLiteral)parentElement).getOwnedPropertyValues());
		}
		
		if (parentElement instanceof EnumerationPropertyType){
			elt.addAll(((EnumerationPropertyType)parentElement).getOwnedEnumerationPropertyTypes());
			elt.addAll(((EnumerationPropertyType)parentElement).getOwnedLiterals());
			elt.addAll(((EnumerationPropertyType)parentElement).getOwnedPropertyValueGroups());
			elt.addAll(((EnumerationPropertyType)parentElement).getOwnedPropertyValues());
		}
		
		if (parentElement instanceof PropertyValuePkg){
			elt.addAll(((PropertyValuePkg)parentElement).getOwnedPropertyValuePkgs());
			elt.addAll(((PropertyValuePkg)parentElement).getOwnedPropertyValueGroups());
			elt.addAll(((PropertyValuePkg)parentElement).getOwnedPropertyValues());
			elt.addAll(((PropertyValuePkg)parentElement).getOwnedEnumerationPropertyTypes());
		}
		
		if (parentElement instanceof PropertyValueGroup){
			elt.addAll(((PropertyValueGroup)parentElement).getOwnedPropertyValueGroups());
			elt.addAll(((PropertyValueGroup)parentElement).getOwnedPropertyValues());
			elt.addAll(((PropertyValueGroup)parentElement).getOwnedEnumerationPropertyTypes());
		}
		
		if (parentElement instanceof AbstractPropertyValue){
			elt.addAll(((AbstractPropertyValue)parentElement).getOwnedPropertyValueGroups());
			elt.addAll(((AbstractPropertyValue)parentElement).getOwnedPropertyValues());
			elt.addAll(((AbstractPropertyValue)parentElement).getOwnedEnumerationPropertyTypes());
		}
		return elt.toArray();
	}
}
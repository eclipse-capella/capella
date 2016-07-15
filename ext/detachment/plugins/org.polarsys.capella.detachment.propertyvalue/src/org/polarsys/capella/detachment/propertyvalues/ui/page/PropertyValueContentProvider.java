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
package org.polarsys.capella.detachment.propertyvalues.ui.page;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;

public class PropertyValueContentProvider implements ITreeContentProvider {

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}
	
	@Override
	public void dispose() {
		
	}
	
	@Override
	public boolean hasChildren(Object element) {
		return  ((element instanceof PropertyValuePkg) 				&& ((!((PropertyValuePkg)element).getOwnedPropertyValueGroups().isEmpty()) 				|| (!((PropertyValuePkg)element).getOwnedPropertyValuePkgs().isEmpty()) 		|| !((PropertyValuePkg)element).getOwnedPropertyValues().isEmpty() || !((PropertyValuePkg)element).getOwnedEnumerationPropertyTypes().isEmpty())) 					||
				((element instanceof PropertyValueGroup) 			&& ((!((PropertyValueGroup)element).getOwnedPropertyValueGroups().isEmpty()) 			|| !((PropertyValueGroup)element).getOwnedPropertyValues().isEmpty() 			|| !((PropertyValueGroup)element).getOwnedEnumerationPropertyTypes().isEmpty())) 			||
				((element instanceof AbstractPropertyValue) 		&& ((!((AbstractPropertyValue)element).getOwnedPropertyValueGroups().isEmpty()) 		|| !((AbstractPropertyValue)element).getOwnedPropertyValues().isEmpty() 		|| !((AbstractPropertyValue)element).getOwnedEnumerationPropertyTypes().isEmpty())) 		||
				((element instanceof EnumerationPropertyType)		&& ((!((EnumerationPropertyType)element).getOwnedPropertyValueGroups().isEmpty()) 		|| (!((EnumerationPropertyType)element).getOwnedPropertyValues().isEmpty()) 	|| !((EnumerationPropertyType)element).getOwnedEnumerationPropertyTypes().isEmpty()		|| !((EnumerationPropertyType)element).getOwnedLiterals().isEmpty())) 	||
				((element instanceof EnumerationPropertyLiteral)	&& ((!((EnumerationPropertyLiteral)element).getOwnedPropertyValueGroups().isEmpty()) 	|| (!((EnumerationPropertyLiteral)element).getOwnedPropertyValues().isEmpty()) 	|| !((EnumerationPropertyLiteral)element).getOwnedEnumerationPropertyTypes().isEmpty()));
		
	}
	
	@Override
	public Object getParent(Object element) {
		if ((element instanceof EObject) && (((EObject)element).eContainer() instanceof PropertyValuePkg) 
				|| (((EObject)element).eContainer() instanceof PropertyValueGroup) 
				|| (((EObject)element).eContainer() instanceof AbstractPropertyValue)
				|| (((EObject)element).eContainer() instanceof EnumerationPropertyType)
				|| (((EObject)element).eContainer() instanceof EnumerationPropertyLiteral))
			return ((EObject)element).eContainer();
		return null;
	}
	
	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Collection){
			return filterCollection(((Collection)inputElement)).toArray();
		}
		return null;
	}
	

	//TODO if we don't want to get duplicated element in the tree. Now, display all.
	@SuppressWarnings("unchecked")
	private Collection<Object> filterCollection(Collection inputElement) {
		/*
		List<Object> filtredList = new ArrayList<Object>();
		
		for (Object pv : inputElement) {
			if (getParent(pv) == null){
				filtredList.add(pv);
			}
		}
		
		return filtredList;
		*/
		return inputElement;
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

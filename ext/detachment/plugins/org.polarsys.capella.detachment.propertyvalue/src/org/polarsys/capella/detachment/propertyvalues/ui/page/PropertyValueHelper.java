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
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.detachment.propertyvalue.Activator;
import org.polarsys.capella.detachment.propertyvalue.messages.Messages;
import org.polarsys.kitalpha.model.common.scrutiny.analyzer.ModelScrutinyException;
import org.polarsys.kitalpha.model.common.scrutiny.analyzer.Scrutineer;
import org.polarsys.kitalpha.model.common.scrutiny.interfaces.IScrutinize;
import org.polarsys.kitalpha.model.common.scrutiny.registry.ModelScrutinyRegistry.RegistryElement;

public class PropertyValueHelper {
	
	/**
	 * Get a value of an String attribute type by calling its getter
	 * @param eObject from which invoke the getter
	 * @param getterName the getter to call
	 * @return
	 */
	public static String getStringAttribute(Object eObject, String getterName) {
		try {
			String name = (String) eObject.getClass().getMethod(getterName).invoke(eObject); //$NON-NLS-1$
			return getName(name); //$NON-NLS-1$
		} catch (Exception e) {
			throw new IllegalStateException(Messages.bind(Messages.Error_UnableToInvokeMethod, getterName, eObject.toString()), e);
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	public static Collection<IScrutinize> getScrutinizers(String id){
		try {
			RegistryElement regElt = Scrutineer.getRegistryElement(id);	
			return regElt.getFinders();
		} catch (ModelScrutinyException e) {
			Status status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
			Activator.getDefault().getLog().log(status);
		}
		return Collections.emptySet();
	}
	
	private static String getName(String name) {
		return name == null || name.isEmpty()? Constants.PROPERTY_EMPTY_NAME : name;
	}
	
	/**
	 * Lookup if the hierarchy of the element has the same type (e.ginterface)
	 * @param element
	 * @param clazzes interface to lookup
	 * @return
	 */
	public static boolean lookupSuperHierarchy(Object element, Class<?>... clazzes) {
		if (element instanceof EObject){
			EObject root = EcoreUtil.getRootContainer((EObject) element);
			EObject eContainer = ((EObject)element).eContainer();
			if (root != null && eContainer != null && !root.equals(eContainer)){
				Class<?>[] interfaces = eContainer.getClass().getInterfaces();
				boolean isRightClass = isRightType(interfaces, clazzes);
				if (!isRightClass){
					lookupSuperHierarchy(eContainer, clazzes);
				} else {
					return isRightClass;
				}
			} else {
				return false;
			}
			
		}
		return false;
	}

	private static boolean isRightType(Class<?>[] interfaces, Class<?>... clazzes) {
		if (interfaces != null && interfaces.length != 0){
			for (Class<?> c : interfaces) {
				for (Class<?> c1 : clazzes) {
					if (c1.equals(c)){
						return true;
					}
				}
			}
		}
		return false;
	}


	public static boolean lookupOwnedPropertyValues(Object element) {
		if (element instanceof EnumerationPropertyLiteral){
			EnumerationPropertyLiteral literal = ((EnumerationPropertyLiteral)element);
			if (!literal.getOwnedPropertyValues().isEmpty()) {
				return true;
			} else {
				Collection<Object> elt = new HashSet<>();
				elt.addAll((literal).getOwnedEnumerationPropertyTypes());
				elt.addAll((literal).getOwnedPropertyValueGroups());
				return lookupOwnedPropertyValues(elt);
			}
		}
		
		if (element instanceof EnumerationPropertyType){
			EnumerationPropertyType type = ((EnumerationPropertyType)element);
			if (!type.getOwnedPropertyValues().isEmpty()) {
				return true;
			} else {
				Collection<Object> elt = new HashSet<>();
				elt.addAll((type).getOwnedEnumerationPropertyTypes());
				elt.addAll((type).getOwnedLiterals());
				elt.addAll((type).getOwnedPropertyValueGroups());
				return lookupOwnedPropertyValues(elt);
			}
		}
		
		if (element instanceof PropertyValuePkg){
			PropertyValuePkg pkg = ((PropertyValuePkg)element);
			if (!pkg.getOwnedPropertyValues().isEmpty()) {
				return true;
			} else {
				Collection<Object> elt = new HashSet<>();
				elt.addAll((pkg).getOwnedPropertyValuePkgs());
				elt.addAll((pkg).getOwnedPropertyValueGroups());
				elt.addAll((pkg).getOwnedEnumerationPropertyTypes());
				return lookupOwnedPropertyValues(elt);
			}
		}
		if (element instanceof PropertyValueGroup){
			PropertyValueGroup grp = ((PropertyValueGroup)element);
			if (!grp.getOwnedPropertyValues().isEmpty()) {
				return true;
			} else {
				Collection<Object> elt = new HashSet<>();
				elt.addAll(((PropertyValueGroup)grp).getOwnedPropertyValueGroups());
				elt.addAll(((PropertyValueGroup)grp).getOwnedEnumerationPropertyTypes());
				return lookupOwnedPropertyValues(elt);
			}
		}
		return false;
	}


	private static boolean lookupOwnedPropertyValues(Collection<Object> elt) {
		boolean result = false;
		for (Object object : elt) {
			result = lookupOwnedPropertyValues(object);
			if (result)
				return result;
		}
		
		return false;
	}


	public static boolean lookupOwnedPropertyValuesGroup(Object element) {
		if (element instanceof EnumerationPropertyLiteral){
			EnumerationPropertyLiteral literal = ((EnumerationPropertyLiteral)element);
			if (!literal.getOwnedPropertyValueGroups().isEmpty()) {
				return true;
			} else {
				Collection<Object> elt = new HashSet<>();
				elt.addAll((literal).getOwnedEnumerationPropertyTypes());
				elt.addAll((literal).getOwnedPropertyValueGroups());
				return lookupOwnedPropertyValuesGroup(elt);
			}
		}
		
		if (element instanceof EnumerationPropertyType){
			EnumerationPropertyType type = ((EnumerationPropertyType)element);
			if (!type.getOwnedPropertyValueGroups().isEmpty()) {
				return true;
			} else {
				Collection<Object> elt = new HashSet<>();
				elt.addAll((type).getOwnedEnumerationPropertyTypes());
				elt.addAll((type).getOwnedLiterals());
				elt.addAll((type).getOwnedPropertyValueGroups());
				return lookupOwnedPropertyValuesGroup(elt);
			}
		}
		
		if (element instanceof PropertyValuePkg){
			PropertyValuePkg pkg = ((PropertyValuePkg)element);
			if (!pkg.getOwnedPropertyValueGroups().isEmpty()) {
				return true;
			} else {
				Collection<Object> elt = new HashSet<>();
				elt.addAll((pkg).getOwnedPropertyValuePkgs());
				elt.addAll((pkg).getOwnedPropertyValueGroups());
				elt.addAll((pkg).getOwnedEnumerationPropertyTypes());
				return lookupOwnedPropertyValuesGroup(elt);
			}
		}
		
		if (element instanceof AbstractPropertyValue){
			AbstractPropertyValue pv = ((AbstractPropertyValue)element);
			if (!pv.getOwnedPropertyValueGroups().isEmpty()){
				return true;
			} else {
				Collection<Object> elt = new HashSet<>();
				elt.addAll((pv).getOwnedPropertyValues());
				elt.addAll((pv).getOwnedEnumerationPropertyTypes());
				return lookupOwnedPropertyValuesGroup(elt);
			}
		}
		return false;
	}
	
	private static boolean lookupOwnedPropertyValuesGroup(Collection<Object> elt) {
		boolean result = false;
		for (Object object : elt) {
			result = lookupOwnedPropertyValuesGroup(object);
			if (result)
				return result;
		}
		
		return false;
	}
	
	public static boolean lookupOwnedPropertyValuesEnum(Object element) {
		if (element instanceof EnumerationPropertyLiteral){
			EnumerationPropertyLiteral literal = ((EnumerationPropertyLiteral)element);
			if (!literal.getOwnedEnumerationPropertyTypes().isEmpty()) {
				return true;
			} else {
				Collection<Object> elt = new HashSet<>();
				elt.addAll((literal).getOwnedPropertyValueGroups());
				elt.addAll((literal).getOwnedPropertyValueGroups());
				return lookupOwnedPropertyValuesEnum(elt);
			}
		}
		
		if (element instanceof PropertyValueGroup){
			PropertyValueGroup grp = ((PropertyValueGroup)element);
			if (!grp.getOwnedEnumerationPropertyTypes().isEmpty()) {
				return true;
			} else {
				Collection<Object> elt = new HashSet<>();
				elt.addAll((grp).getOwnedPropertyValues());
				elt.addAll((grp).getOwnedPropertyValueGroups());
				return lookupOwnedPropertyValuesEnum(elt);
			}
		}
		
		if (element instanceof PropertyValuePkg){
			PropertyValuePkg pkg = ((PropertyValuePkg)element);
			if (!pkg.getOwnedEnumerationPropertyTypes().isEmpty()) {
				return true;
			} else {
				Collection<Object> elt = new HashSet<>();
				elt.addAll((pkg).getOwnedPropertyValuePkgs());
				elt.addAll((pkg).getOwnedPropertyValueGroups());
				elt.addAll((pkg).getOwnedPropertyValues());
				return lookupOwnedPropertyValuesEnum(elt);
			}
		}
		
		if (element instanceof AbstractPropertyValue){
			AbstractPropertyValue pv = ((AbstractPropertyValue)element);
			if (!pv.getOwnedEnumerationPropertyTypes().isEmpty()){
				return true;
			} else {
				Collection<Object> elt = new HashSet<>();
				elt.addAll((pv).getOwnedPropertyValues());
				elt.addAll((pv).getOwnedPropertyValueGroups());
				return lookupOwnedPropertyValuesEnum(elt);
			}
		}
		return false;
	}
	
	private static boolean lookupOwnedPropertyValuesEnum(Collection<Object> elt) {
		boolean result = false;
		for (Object object : elt) {
			result = lookupOwnedPropertyValuesEnum(object);
			if (result)
				return result;
		}
		
		return false;
	}
	
}

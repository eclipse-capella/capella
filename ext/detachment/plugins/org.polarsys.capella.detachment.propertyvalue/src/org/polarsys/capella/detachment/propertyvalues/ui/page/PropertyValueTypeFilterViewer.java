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

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;

public class PropertyValueTypeFilterViewer extends ViewerFilter {

	private int filterType = -1;
	
	public int getFilterType() {
		return filterType;
	}
	
	public void setFilterType(int filterType) {
		this.filterType = filterType;
	}
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		switch (getFilterType()) {
		case Constants.PV:
		{
			return element instanceof AbstractPropertyValue || PropertyValueHelper.lookupSuperHierarchy(element, AbstractPropertyValue.class) || PropertyValueHelper.lookupOwnedPropertyValues(element);
		}
		case Constants.PVG:
		{
			return element instanceof PropertyValueGroup || PropertyValueHelper.lookupSuperHierarchy(element, PropertyValueGroup.class) || PropertyValueHelper.lookupOwnedPropertyValuesGroup(element);
		}
		case Constants.PVP:
		{
			return element instanceof PropertyValuePkg || PropertyValueHelper.lookupSuperHierarchy(element, PropertyValuePkg.class);
		}
		case Constants.ET:
		{
			return element instanceof EnumerationPropertyType || PropertyValueHelper.lookupSuperHierarchy(element, EnumerationPropertyType.class) || PropertyValueHelper.lookupOwnedPropertyValuesEnum(element);
		}
		case Constants.PV_PVG:
		{
			return element instanceof AbstractPropertyValue || element instanceof PropertyValueGroup 
					|| PropertyValueHelper.lookupSuperHierarchy(element, AbstractPropertyValue.class, PropertyValueGroup.class) ||
					PropertyValueHelper.lookupOwnedPropertyValues(element) || PropertyValueHelper.lookupOwnedPropertyValuesGroup(element);
		}
		case Constants.PV_PVP:
		{
			return element instanceof AbstractPropertyValue || element instanceof PropertyValuePkg 
					|| PropertyValueHelper.lookupSuperHierarchy(element, AbstractPropertyValue.class, PropertyValuePkg.class) || PropertyValueHelper.lookupOwnedPropertyValues(element);
		}
		case Constants.PVG_PVP:
		{
			return element instanceof PropertyValueGroup || element instanceof PropertyValuePkg 
					|| PropertyValueHelper.lookupSuperHierarchy(element, PropertyValueGroup.class, PropertyValuePkg.class) || PropertyValueHelper.lookupOwnedPropertyValuesGroup(element);
		}
		case Constants.PV_ET:
		{
			return element instanceof AbstractPropertyValue || element instanceof EnumerationPropertyType 
					|| PropertyValueHelper.lookupSuperHierarchy(element, AbstractPropertyValue.class, EnumerationPropertyType.class) 
					|| PropertyValueHelper.lookupOwnedPropertyValues(element) || PropertyValueHelper.lookupOwnedPropertyValuesEnum(element);
		}
		case Constants.PVG_ET:
		{
			return element instanceof PropertyValueGroup || element instanceof EnumerationPropertyType 
					|| PropertyValueHelper.lookupSuperHierarchy(element, PropertyValueGroup.class, EnumerationPropertyType.class)
					|| PropertyValueHelper.lookupOwnedPropertyValuesGroup(element) || PropertyValueHelper.lookupOwnedPropertyValuesEnum(element);
		}
		case Constants.PVP_ET:
		{
			return element instanceof PropertyValuePkg || element instanceof EnumerationPropertyType 
					|| PropertyValueHelper.lookupSuperHierarchy(element, PropertyValuePkg.class, EnumerationPropertyType.class) || PropertyValueHelper.lookupOwnedPropertyValuesEnum(element);
		}
		case Constants.PV_PVG_PVP:
		{
			return element instanceof AbstractPropertyValue || element instanceof PropertyValueGroup || element instanceof PropertyValuePkg 
					|| PropertyValueHelper.lookupSuperHierarchy(element, AbstractPropertyValue.class, PropertyValueGroup.class, PropertyValuePkg.class)
					|| PropertyValueHelper.lookupOwnedPropertyValuesGroup(element)|| PropertyValueHelper.lookupOwnedPropertyValues(element);
		}
		case Constants.PV_PVP_ET:
		{
			return element instanceof AbstractPropertyValue || element instanceof PropertyValuePkg || element instanceof EnumerationPropertyType 
					|| PropertyValueHelper.lookupSuperHierarchy(element, AbstractPropertyValue.class, PropertyValuePkg.class, EnumerationPropertyType.class)
					|| PropertyValueHelper.lookupOwnedPropertyValues(element) || PropertyValueHelper.lookupOwnedPropertyValuesEnum(element);
		}
		case Constants.PV_PVG_PVP_ET:
		{
			return element instanceof AbstractPropertyValue || element instanceof PropertyValueGroup || element instanceof PropertyValuePkg || element instanceof EnumerationPropertyType
					|| PropertyValueHelper.lookupSuperHierarchy(element, AbstractPropertyValue.class, PropertyValueGroup.class, PropertyValuePkg.class, EnumerationPropertyType.class)
					|| PropertyValueHelper.lookupOwnedPropertyValues(element) || PropertyValueHelper.lookupOwnedPropertyValuesEnum(element) || PropertyValueHelper.lookupOwnedPropertyValuesGroup(element);
		}
		case Constants.PVG_PVP_ET:
		{
			return element instanceof PropertyValueGroup || element instanceof PropertyValuePkg || element instanceof EnumerationPropertyType
					|| PropertyValueHelper.lookupSuperHierarchy(element, PropertyValueGroup.class, PropertyValuePkg.class, EnumerationPropertyType.class)
					|| PropertyValueHelper.lookupOwnedPropertyValuesGroup(element) || PropertyValueHelper.lookupOwnedPropertyValuesEnum(element);
		}
		default:
			break;
		}
		return true;
	}
}

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
			return element instanceof AbstractPropertyValue;
		}
		case Constants.PVG:
		{
			return element instanceof PropertyValueGroup;
		}
		case Constants.PVP:
		{
			return element instanceof PropertyValuePkg;
		}
		case Constants.ET:
		{
			return element instanceof EnumerationPropertyType;
		}
		case Constants.PV_PVG:
		{
			return element instanceof AbstractPropertyValue || element instanceof PropertyValueGroup;
		}
		case Constants.PV_PVP:
		{
			return element instanceof AbstractPropertyValue || element instanceof PropertyValuePkg;
		}
		case Constants.PVG_PVP:
		{
			return element instanceof PropertyValueGroup || element instanceof PropertyValuePkg;
		}
		case Constants.PV_ET:
		{
			return element instanceof AbstractPropertyValue || element instanceof EnumerationPropertyType;
		}
		case Constants.PVG_ET:
		{
			return element instanceof PropertyValueGroup || element instanceof EnumerationPropertyType;
		}
		case Constants.PVP_ET:
		{
			return element instanceof PropertyValuePkg || element instanceof EnumerationPropertyType;
		}
		case Constants.PV_PVG_PVP:
		{
			return element instanceof AbstractPropertyValue || element instanceof PropertyValueGroup || element instanceof PropertyValuePkg;
		}
		case Constants.PV_PVP_ET:
		{
			return element instanceof AbstractPropertyValue || element instanceof PropertyValuePkg || element instanceof EnumerationPropertyType;
		}
		case Constants.PV_PVG_PVP_ET:
		{
			return element instanceof AbstractPropertyValue || element instanceof PropertyValueGroup || element instanceof PropertyValuePkg || element instanceof EnumerationPropertyType;
		}
		case Constants.PVG_PVP_ET:
		{
			return element instanceof PropertyValueGroup || element instanceof PropertyValuePkg || element instanceof EnumerationPropertyType;
		}
		default:
			break;
		}
		
		return true;
	}

}

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

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;

public class PropertyValueLabelProvider implements ITableLabelProvider {

	@Override
	public void removeListener(ILabelProviderListener listener) {
		
	}
	
	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}
	
	@Override
	public void dispose() {
		
	}
	
	@Override
	public void addListener(ILabelProviderListener listener) {
		
	}
	
	@Override
	public String getColumnText(Object element, int columnIndex) {
		
		switch (columnIndex){
		case 0:
		{
			return PropertyValueHelper.getStringAttribute(element, "getName"); //$NON-NLS-1$
		}
		case 1:
		{
			return PropertyValueHelper.getStringAttribute(element, "getFullLabel"); //$NON-NLS-1$
		}
		default:
		{
			//do nothing
		}
		}
		
		return null;
	}


	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		switch (columnIndex) {
		case 0:

			if (element instanceof EnumerationPropertyLiteral){
				return Constants.getEnumLiteralIcon();
			}

			if (element instanceof EnumerationPropertyType){
				return Constants.getEnumTypeIcon();
			}

			if (element instanceof AbstractPropertyValue){
				return Constants.getPropertyValueIcon();
			}

			if (element instanceof PropertyValueGroup){
				return Constants.getPropertyGroupIcon();
			}

			if (element instanceof PropertyValuePkg){
				return Constants.getPropertyPackageIcon();
			}

		default:
			break;
		}
		return null;
	}
}

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
package org.polarsys.capella.detachment.propertyvalues.ui.page;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.polarsys.capella.detachment.propertyvalue.Activator;

public final class Constants {
	
	public static final int ALL_PROPERTY_VALUES			= 0;
	public static final int PROPERTY_VALUES 			= 1 << 0;
	public static final int PROPERTY_VALUES_GROUP		= 1 << 1;
	public static final int PROPERTY_VALUES_PACKAGE		= 1 << 2;
	public static final int PROPERTY_ENUMERATION_TYPE 	= 1 << 3;
	
	public static final int PV 					= PROPERTY_VALUES;
	public static final int PVG 				= PROPERTY_VALUES_GROUP;
	public static final int PVP					= PROPERTY_VALUES_PACKAGE;
	public static final int ET					= PROPERTY_ENUMERATION_TYPE;
	public static final int PV_PVG				= PV | PVG;
	public static final int PV_PVP				= PV | PVP;
	public static final int PV_ET				= PV | ET;
	public static final int PVG_ET 				= PVG | ET;
	public static final int PVP_ET				= PVP | ET;
	public static final int PV_PVG_PVP			= PV | PVG | PVP;
	public static final int PV_PVP_ET			= PV_PVP | ET;
	public static final int PVG_PVP				= PVG | PVP;
	public static final int PV_PVG_PVP_ET 		= PV_PVG_PVP | ET;
	public static final int PVG_PVP_ET	  		= PVG_PVP | ET;
	
	//icons
	private static final Image pvIcon;
	private static final Image pgIcon;
	private static final Image pkgIcon;
	private static final Image enumTypeIcon;
	private static final Image enumLiteralIcon;
	private static final Image collapsAllIcon;
	private static final Image expandAllIcon;
	private static final Image checkAllIcon;
	private static final Image uncheckAllIcon;

	static {
		final String BUNDLE_ID = "org.polarsys.capella.core.data.res.edit"; //$NON-NLS-1$
		final Bundle ICON_BUNDLE = Platform.getBundle(BUNDLE_ID);
		
		final String PV_ICON = "icons/full/obj16/BooleanPropertyValue.gif"; //$NON-NLS-1$
		final String PG_ICON = "icons/full/obj16/PropertyValueGroup.gif"; //$NON-NLS-1$
		final String PK_ICON = "icons/full/obj16/PropertyValuePkg.gif"; //$NON-NLS-1$
		final String ET_ICON = "icons/full/obj16/EnumerationPropertyType.gif"; //$NON-NLS-1$
		final String EL_ICON = "icons/full/obj16/EnumerationPropertyLiteral.gif"; //$NON-NLS-1$
		
		
		URL url = FileLocator.find(ICON_BUNDLE, new Path(PV_ICON), null);
		pvIcon = ImageDescriptor.createFromURL(url).createImage();

		url = FileLocator.find(ICON_BUNDLE, new Path(PG_ICON), null);
		pgIcon = ImageDescriptor.createFromURL(url).createImage(); 

		url = FileLocator.find(ICON_BUNDLE, new Path(PK_ICON), null);
		pkgIcon = ImageDescriptor.createFromURL(url).createImage(); 

		url = FileLocator.find(ICON_BUNDLE, new Path(ET_ICON), null);
		enumTypeIcon = ImageDescriptor.createFromURL(url).createImage();

		url = FileLocator.find(ICON_BUNDLE, new Path(EL_ICON), null);
		enumLiteralIcon = ImageDescriptor.createFromURL(url).createImage();
		
		url = FileLocator.find(FrameworkUtil.getBundle(Activator.class), new Path("icons/full/collapseall.gif"), null); //$NON-NLS-1$
		collapsAllIcon = ImageDescriptor.createFromURL(url).createImage();
		
		url = FileLocator.find(FrameworkUtil.getBundle(Activator.class), new Path("icons/full/expandall.gif"), null); //$NON-NLS-1$
		expandAllIcon = ImageDescriptor.createFromURL(url).createImage();
		
		url = FileLocator.find(FrameworkUtil.getBundle(Activator.class), new Path("icons/full/checked.gif"), null); //$NON-NLS-1$
		checkAllIcon = ImageDescriptor.createFromURL(url).createImage();
		
		url = FileLocator.find(FrameworkUtil.getBundle(Activator.class), new Path("icons/full/unchecked.gif"), null); //$NON-NLS-1$
		uncheckAllIcon = ImageDescriptor.createFromURL(url).createImage();
	}

	public static final Image getPropertyValueIcon() {
		return pvIcon;
	}

	public static final Image getPropertyGroupIcon() {
		return pgIcon;
	}

	public static final Image getPropertyPackageIcon() {
		return pkgIcon;
	}

	public static final Image getEnumTypeIcon() {
		return enumTypeIcon;
	}

	public static final Image getEnumLiteralIcon() {
		return enumLiteralIcon;
	}
	
	
	
	public static final Image getCollapsAllIcon() {
		return collapsAllIcon;
	}

	public static final Image getExpandAllIcon() {
		return expandAllIcon;
	}

	public static final Image getCheckAllIcon() {
		return checkAllIcon;
	}

	public static final Image getUncheckAllIcon() {
		return uncheckAllIcon;
	}

}
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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
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
}

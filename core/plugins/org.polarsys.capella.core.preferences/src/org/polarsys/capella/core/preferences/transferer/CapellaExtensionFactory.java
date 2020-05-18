/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.preferences.transferer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.ExtensionFactory;
import org.eclipse.ui.PlatformUI;

/**
 *
 */
public class CapellaExtensionFactory extends ExtensionFactory implements IExecutableExtensionFactory,
IExecutableExtension {
	
	
	private IConfigurationElement config;
	private String propertyName;
	private String id;

	
	 /**
	 * 
	 */
	public CapellaExtensionFactory() {
		super();
	}
	/**
	 * Creates the object referenced by the factory id obtained from the
	 * extension data.
	 */
	@Override
	public Object create() throws CoreException {
		if (PREFERENCES_EXPORT_WIZARD.equals(id)) {
			return configure(new CapellaPreferencesExportWizard());
		}
		
		else
			super.create();

		throw new CoreException(new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID,
				0, "Unknown id in data argument for " + getClass(), null)); //$NON-NLS-1$
	}
	
	
	private Object configure(Object obj) throws CoreException {
		if (obj instanceof IExecutableExtension) {
			((IExecutableExtension) obj).setInitializationData(config,
					propertyName, null);
		}
		return obj;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement,
	 *      java.lang.String, java.lang.Object)
	 */
	public void setInitializationData(IConfigurationElement config,	String propertyName, Object data) throws CoreException {
		
		super.setInitializationData(config,propertyName,data);
		if (data instanceof String) {
			id = (String) data;
		} else {
			throw new CoreException(new Status(IStatus.ERROR,
					PlatformUI.PLUGIN_ID, 0,
					"Data argument must be a String for " + getClass(), null)); //$NON-NLS-1$
		}
		this.config = config;
		this.propertyName = propertyName;
	}

}

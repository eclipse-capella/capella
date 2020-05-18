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
package org.polarsys.capella.core.commands.preferences.properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * The abstract parent of all service providers. Implements basic support for
 * thread-safe provider change notification.
 * <p>
 * Service implementers are expected to expose service specific provider classes
 * extending this class.
 * </p>
 * <p>
 * Service provider implementers never need to extend this class directly, they
 * instead derive their provider implementation from the service specific
 * provider class extending this interface.
 * </p>
 */
public abstract class AbstractProvider
	implements IProvider {

	/**
	 * The provider change listeners.
	 */
	private final List listeners = Collections
		.synchronizedList(new ArrayList());

	/**
	 * Constructs a new abstract provider.
	 */
	protected AbstractProvider() {
		super();
	}

	/**
	 * Retrieves the value of the <code>listeners</code> instance variable.
	 * 
	 * @return The value of the <code>listeners</code> instance varible.
	 */
	private List getListeners() {
		return listeners;
	}

	/**
	 * Adds the specified listener to the list of provider change listeners for
	 * this abstract provider.
	 * 
	 * @param listener
	 *            The listener to be added.
	 * 
	 * @see IProvider#addProviderChangeListener(IProviderChangeListener)
	 */
	public void addProviderChangeListener(IProviderChangeListener listener) {
	
		assert null != listener : "addProviderChangeListener received NULL listener as argument"; //$NON-NLS-1$
		
		getListeners().add(listener);
	}

	/**
	 * Removes the specified listener from the list of provider change listeners
	 * for this abstract provider.
	 * 
	 * @param listener
	 *            The listener to be removed.
	 * 
	 * @see IProvider#removeProviderChangeListener(IProviderChangeListener)
	 */
	public void removeProviderChangeListener(IProviderChangeListener listener) {
		
		assert null != listener : "removeProviderChangeListener received NULL listener as argument"; //$NON-NLS-1$
		
		getListeners().remove(listener);
	}

	/**
	 * Notifies the listeners for this abstract provider that the specified
	 * event has occurred.
	 * 
	 * @param event
	 *            The provider change event to be fired.
	 */
	protected void fireProviderChange(ProviderChangeEvent event) {
		
		assert null != event : "fireProviderChange received NULL event as argument"; //$NON-NLS-1$
		
		List targets = null;
		synchronized (getListeners()) {
			targets = new ArrayList(getListeners());
		}

		for (Iterator i = targets.iterator(); i.hasNext();) {
			((IProviderChangeListener) i.next()).providerChanged(event);
		}
	}

}

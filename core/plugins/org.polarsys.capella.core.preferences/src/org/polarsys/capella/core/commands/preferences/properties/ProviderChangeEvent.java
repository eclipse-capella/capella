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

import java.util.EventObject;

/**
 * An event that indicates when a provider changes.
 * 
 * Service implementers are expected to expose service specific sub-classes to
 * help listeners understand the exact nature of the state change. They are not
 * expected to instantiate these events.
 * <p>
 * </p>
 * <p>
 * </p>
 * Service provider implementers never need to subclass this interface or its
 * subclasses, they instead instantiate the service specific subclass in their
 * provider implementation when they need to notify listeners of a state change.
 * 
 * @see AbstractProvider#addProviderChangeListener(IProviderChangeListener)
 * @see IProviderChangeListener#providerChanged(ProviderChangeEvent)
 * @see IProvider#addProviderChangeListener(IProviderChangeListener)
 * @see IProvider#removeProviderChangeListener(IProviderChangeListener)
 */
public class ProviderChangeEvent
	extends EventObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new provider change event for the specified provider.
	 * 
	 * @param source
	 *            The provider that changed.
	 */
	public ProviderChangeEvent(IProvider source) {
		super(source);
	}

	/**
	 * Sets the <code>source</code> instance variable to the specified value.
	 * 
	 * This method is reserved for internal use.
	 * 
	 * @param source
	 *            The new value for the <code>source</code> instance variable.
	 */
	public void setSource(IProvider source) {
		assert null != source : "setSource received NULL provider as argument"; //$NON-NLS-1$
		
		this.source = source;
	}

}

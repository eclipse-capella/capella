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

import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.jface.action.IAction;

/**
 * The interface for all service providers. Specifies a part of the contract
 * between a service and its providers:
 * <ul>
 * <li>A service can listen to its provider
 * <li>A service can request a provider whether it wants to support a given
 * request
 * </ul>
 * <p>
 * Service implementers are expected to expose service specific classes
 * implementing this interface.
 * </p>
 * <p>
 * Service provider implementers never need to implement this interface
 * directly, they instead derive their provider implementation from the service
 * specific implementation of this interface.
 * </p>
 */
public interface IProvider {

	/**
	 * Adds the specified listener to the list of provider change listeners for
	 * this provider.
	 * 
	 * @param listener
	 *            The listener to be added.
	 */
	public void addProviderChangeListener(IProviderChangeListener listener);

	/**
	 * Indicates whether this provider provides the specified operation.
	 * <p>
	 * </p>
	 * <p>
	 * </p>
	 * Providers generally cast the operation to a service specific
	 * {@link IOperation}-derived class in order to determine whether they
	 * support the request.
	 * 
	 * @return <code>true</code> if this provider provides the operation;
	 *         <code>false</code> otherwise.
	 * @param operation
	 *            The operation in question.
	 */
	public boolean provides(IAction operation);

	/**
	 * Removes the specified listener from the list of provider change listeners
	 * for this provider.
	 * 
	 * @param listener
	 *            The listener to be removed.
	 */
	public void removeProviderChangeListener(IProviderChangeListener listener);

}

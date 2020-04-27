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

package org.polarsys.capella.core.data.helpers.fa.services;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * 
 */
public abstract class BusPattern implements Pattern {

	/**
	 * The Bus EClass defaults to (EObject).
	 */
	protected EClass _busEClass = EcorePackage.Literals.EOBJECT;

	/**
	 * The Bus Patterns UIName ("Generic Bus Pattern").
	 */
	protected String _UIName = "Generic Bus Pattern"; //$NON-NLS-1$

	/**
	 * The map of ends as seen from this bus. This is a Map of busName (as seen
	 * from end), endName (as seen from bus) by end's EClass.
	 */
	protected Map<EClass, EndDescription> _endsMap;

	/**
	 * The Helper Class associated to this Pattern.
	 */
	protected Class<?> helper;

	/**
	 * @return our Bus EClass.
	 */
	public EClass getBusEClass() {
		return _busEClass;
	}

	/**
	 * @return Our Dedicated Helper.
	 */
	public Class<?> getHelper() {
		return helper;
	}

	public Map<EClass, EndDescription> getEndsMap() {
		return _endsMap;
	}

}

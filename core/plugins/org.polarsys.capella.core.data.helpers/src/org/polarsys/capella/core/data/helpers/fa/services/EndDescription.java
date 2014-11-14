/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.helpers.fa.services;

import org.eclipse.emf.ecore.EClass;

/**
 * 
 */
public class EndDescription {

	/**
	 * The End's EClass.
	 */
	private EClass _eClass;

	/**
	 * The bus role name (as seen from end).
	 */
	private String _busName;

	/**
	 * The end feature name (as seen from bus).
	 */
	private String _endName;

	public EClass get_eClass() {
		return _eClass;
	}

	public void set_eClass(EClass class1) {
		_eClass = class1;
	}

	public String getBusName() {
		return _busName;
	}

	public void setBusName(String name) {
		_busName = name;
	}

	public String getEndName() {
		return _endName;
	}

	public void setEndName(String name) {
		_endName = name;
	}
}

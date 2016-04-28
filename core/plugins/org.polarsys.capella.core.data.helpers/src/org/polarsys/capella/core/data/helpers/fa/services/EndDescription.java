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

package org.polarsys.capella.core.data.helpers.fa.services;

import org.eclipse.emf.ecore.EClass;

/**
 * 
 */
public class EndDescription {

	/**
	 * The End's EClass.
	 */
	private EClass eClass;

	/**
	 * The bus role name (as seen from end).
	 */
	private String busName;

	/**
	 * The end feature name (as seen from bus).
	 */
	private String endName;

	public EClass get_eClass() {
		return eClass;
	}

	public void set_eClass(EClass class1) {
	  this.eClass = class1;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String name) {
	  this.busName = name;
	}

	public String getEndName() {
		return endName;
	}

	public void setEndName(String name) {
	  this.endName = name;
	}
}

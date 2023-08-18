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
package org.polarsys.capella.common.re;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Named Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.re.ReNamedElement#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.re.RePackage#getReNamedElement()
 * @model abstract="true"
 * @generated
 */

public interface ReNamedElement extends ReAbstractElement {





	/**
   * Returns the value of the '<em><b>Name</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.polarsys.capella.common.re.RePackage#getReNamedElement_Name()
   * @model annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getName();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.re.ReNamedElement#getName <em>Name</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */

	void setName(String value);





} // ReNamedElement

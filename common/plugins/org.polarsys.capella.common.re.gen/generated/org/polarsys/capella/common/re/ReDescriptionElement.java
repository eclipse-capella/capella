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
 * A representation of the model object '<em><b>Description Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.re.ReDescriptionElement#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.re.RePackage#getReDescriptionElement()
 * @model abstract="true"
 * @generated
 */

public interface ReDescriptionElement extends ReNamedElement {





	/**
   * Returns the value of the '<em><b>Description</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Description</em>' attribute.
   * @see #setDescription(String)
   * @see org.polarsys.capella.common.re.RePackage#getReDescriptionElement_Description()
   * @model annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getDescription();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.re.ReDescriptionElement#getDescription <em>Description</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Description</em>' attribute.
   * @see #getDescription()
   * @generated
   */

	void setDescription(String value);





} // ReDescriptionElement

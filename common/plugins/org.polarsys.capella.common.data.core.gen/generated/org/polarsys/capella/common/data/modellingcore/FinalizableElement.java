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
package org.polarsys.capella.common.data.modellingcore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Finalizable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.FinalizableElement#isFinal <em>Final</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getFinalizableElement()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' type='n/a' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 * @generated
 */
public interface FinalizableElement extends ModelElement {





	/**
   * Returns the value of the '<em><b>Final</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Final</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Final</em>' attribute.
   * @see #setFinal(boolean)
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getFinalizableElement_Final()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' type='n/a' comment/notes='none'"
   * @generated
   */

	boolean isFinal();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.modellingcore.FinalizableElement#isFinal <em>Final</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Final</em>' attribute.
   * @see #isFinal()
   * @generated
   */

	void setFinal(boolean value);





} // FinalizableElement

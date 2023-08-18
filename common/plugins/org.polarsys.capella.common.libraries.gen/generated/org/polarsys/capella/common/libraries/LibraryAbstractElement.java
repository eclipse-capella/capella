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
package org.polarsys.capella.common.libraries;

import org.polarsys.kitalpha.emde.model.ExtensibleElement;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library Abstract Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.libraries.LibraryAbstractElement#getId <em>Id</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.libraries.LibrariesPackage#getLibraryAbstractElement()
 * @model abstract="true"
 * @generated
 */

public interface LibraryAbstractElement extends ExtensibleElement {





	/**
   * Returns the value of the '<em><b>Id</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see org.polarsys.capella.common.libraries.LibrariesPackage#getLibraryAbstractElement_Id()
   * @model id="true"
   * @generated
   */

	String getId();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.libraries.LibraryAbstractElement#getId <em>Id</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */

	void setId(String value);





} // LibraryAbstractElement

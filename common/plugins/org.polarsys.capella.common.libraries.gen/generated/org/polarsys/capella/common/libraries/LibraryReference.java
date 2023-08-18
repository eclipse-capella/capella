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



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.libraries.LibraryReference#getLibrary <em>Library</em>}</li>
 *   <li>{@link org.polarsys.capella.common.libraries.LibraryReference#getAccessPolicy <em>Access Policy</em>}</li>
 *   <li>{@link org.polarsys.capella.common.libraries.LibraryReference#getVersion <em>Version</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.libraries.LibrariesPackage#getLibraryReference()
 * @model
 * @generated
 */

public interface LibraryReference extends LibraryAbstractElement {





	/**
   * Returns the value of the '<em><b>Library</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Library</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Library</em>' reference.
   * @see #setLibrary(ModelInformation)
   * @see org.polarsys.capella.common.libraries.LibrariesPackage#getLibraryReference_Library()
   * @model required="true"
   * @generated
   */

	ModelInformation getLibrary();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.libraries.LibraryReference#getLibrary <em>Library</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Library</em>' reference.
   * @see #getLibrary()
   * @generated
   */

	void setLibrary(ModelInformation value);







	/**
   * Returns the value of the '<em><b>Access Policy</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.common.libraries.AccessPolicy}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Access Policy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Access Policy</em>' attribute.
   * @see org.polarsys.capella.common.libraries.AccessPolicy
   * @see #setAccessPolicy(AccessPolicy)
   * @see org.polarsys.capella.common.libraries.LibrariesPackage#getLibraryReference_AccessPolicy()
   * @model required="true"
   * @generated
   */

	AccessPolicy getAccessPolicy();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.libraries.LibraryReference#getAccessPolicy <em>Access Policy</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Access Policy</em>' attribute.
   * @see org.polarsys.capella.common.libraries.AccessPolicy
   * @see #getAccessPolicy()
   * @generated
   */

	void setAccessPolicy(AccessPolicy value);







	/**
   * Returns the value of the '<em><b>Version</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Version</em>' reference.
   * @see #setVersion(ModelVersion)
   * @see org.polarsys.capella.common.libraries.LibrariesPackage#getLibraryReference_Version()
   * @model
   * @generated
   */

	ModelVersion getVersion();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.libraries.LibraryReference#getVersion <em>Version</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Version</em>' reference.
   * @see #getVersion()
   * @generated
   */

	void setVersion(ModelVersion value);





} // LibraryReference

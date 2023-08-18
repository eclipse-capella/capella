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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.common.libraries.LibrariesPackage
 * @generated
 */
public interface LibrariesFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	LibrariesFactory eINSTANCE = org.polarsys.capella.common.libraries.impl.LibrariesFactoryImpl.init();

	/**
   * Returns a new object of class '<em>Model Information</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Model Information</em>'.
   * @generated
   */
	ModelInformation createModelInformation();

	/**
   * Returns a new object of class '<em>Library Reference</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Library Reference</em>'.
   * @generated
   */
	LibraryReference createLibraryReference();

	/**
   * Returns a new object of class '<em>Model Version</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Model Version</em>'.
   * @generated
   */
	ModelVersion createModelVersion();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	LibrariesPackage getLibrariesPackage();

	//begin-capella-code
	
	//end-capella-code
} //LibrariesFactory
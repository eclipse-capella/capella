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
package org.polarsys.capella.core.data.capellamodeller;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage
 * @generated
 */
public interface CapellamodellerFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	CapellamodellerFactory eINSTANCE = org.polarsys.capella.core.data.capellamodeller.impl.CapellamodellerFactoryImpl.init();

	/**
   * Returns a new object of class '<em>Project</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Project</em>'.
   * @generated
   */
	Project createProject();

	/**
   * Returns a new object of class '<em>Folder</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Folder</em>'.
   * @generated
   */
	Folder createFolder();

	/**
   * Returns a new object of class '<em>System Engineering</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>System Engineering</em>'.
   * @generated
   */
	SystemEngineering createSystemEngineering();

	/**
   * Returns a new object of class '<em>System Engineering Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>System Engineering Pkg</em>'.
   * @generated
   */
	SystemEngineeringPkg createSystemEngineeringPkg();

	/**
   * Returns a new object of class '<em>Library</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Library</em>'.
   * @generated
   */
	Library createLibrary();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	CapellamodellerPackage getCapellamodellerPackage();

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Project createProject(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Folder createFolder(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	SystemEngineering createSystemEngineering(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	SystemEngineeringPkg createSystemEngineeringPkg(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Library createLibrary(String name_p);

	//begin-capella-code
	//end-capella-code
} //CapellamodellerFactory

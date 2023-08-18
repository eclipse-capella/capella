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
package org.polarsys.capella.core.data.epbs;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.epbs.EpbsPackage
 * @generated
 */
public interface EpbsFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	EpbsFactory eINSTANCE = org.polarsys.capella.core.data.epbs.impl.EpbsFactoryImpl.init();

	/**
   * Returns a new object of class '<em>EPBS Architecture Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>EPBS Architecture Pkg</em>'.
   * @generated
   */
	EPBSArchitecturePkg createEPBSArchitecturePkg();

	/**
   * Returns a new object of class '<em>EPBS Architecture</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>EPBS Architecture</em>'.
   * @generated
   */
	EPBSArchitecture createEPBSArchitecture();

	/**
   * Returns a new object of class '<em>Configuration Item Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Configuration Item Pkg</em>'.
   * @generated
   */
	ConfigurationItemPkg createConfigurationItemPkg();

	/**
   * Returns a new object of class '<em>Configuration Item</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Configuration Item</em>'.
   * @generated
   */
	ConfigurationItem createConfigurationItem();

	/**
   * Returns a new object of class '<em>Physical Architecture Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Physical Architecture Realization</em>'.
   * @generated
   */
	PhysicalArchitectureRealization createPhysicalArchitectureRealization();

	/**
   * Returns a new object of class '<em>Physical Artifact Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Physical Artifact Realization</em>'.
   * @generated
   */
	PhysicalArtifactRealization createPhysicalArtifactRealization();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	EpbsPackage getEpbsPackage();

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	EPBSArchitecturePkg createEPBSArchitecturePkg(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	EPBSArchitecture createEPBSArchitecture(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ConfigurationItemPkg createConfigurationItemPkg(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ConfigurationItem createConfigurationItem(String name_p);

	//begin-capella-code
	//end-capella-code
} //EpbsFactory

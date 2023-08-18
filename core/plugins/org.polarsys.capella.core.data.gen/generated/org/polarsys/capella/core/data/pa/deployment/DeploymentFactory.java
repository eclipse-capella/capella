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
package org.polarsys.capella.core.data.pa.deployment;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.pa.deployment.DeploymentPackage
 * @generated
 */
public interface DeploymentFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	DeploymentFactory eINSTANCE = org.polarsys.capella.core.data.pa.deployment.impl.DeploymentFactoryImpl.init();

	/**
   * Returns a new object of class '<em>Component Instance</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Component Instance</em>'.
   * @generated
   */
	ComponentInstance createComponentInstance();

	/**
   * Returns a new object of class '<em>Connection Instance</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Connection Instance</em>'.
   * @generated
   */
	ConnectionInstance createConnectionInstance();

	/**
   * Returns a new object of class '<em>Aspect</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Aspect</em>'.
   * @generated
   */
	DeploymentAspect createDeploymentAspect();

	/**
   * Returns a new object of class '<em>Configuration</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Configuration</em>'.
   * @generated
   */
	DeploymentConfiguration createDeploymentConfiguration();

	/**
   * Returns a new object of class '<em>Instance Deployment Link</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Instance Deployment Link</em>'.
   * @generated
   */
	InstanceDeploymentLink createInstanceDeploymentLink();

	/**
   * Returns a new object of class '<em>Part Deployment Link</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Part Deployment Link</em>'.
   * @generated
   */
	PartDeploymentLink createPartDeploymentLink();

	/**
   * Returns a new object of class '<em>Port Instance</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Port Instance</em>'.
   * @generated
   */
	PortInstance createPortInstance();

	/**
   * Returns a new object of class '<em>Type Deployment Link</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Type Deployment Link</em>'.
   * @generated
   */
	TypeDeploymentLink createTypeDeploymentLink();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	DeploymentPackage getDeploymentPackage();

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ComponentInstance createComponentInstance(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	DeploymentAspect createDeploymentAspect(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	DeploymentConfiguration createDeploymentConfiguration(String name_p);

	//begin-capella-code
	//end-capella-code
} //DeploymentFactory

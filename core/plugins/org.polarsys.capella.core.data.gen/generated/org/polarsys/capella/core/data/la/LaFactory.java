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
package org.polarsys.capella.core.data.la;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.la.LaPackage
 * @generated
 */
public interface LaFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	LaFactory eINSTANCE = org.polarsys.capella.core.data.la.impl.LaFactoryImpl.init();

	/**
   * Returns a new object of class '<em>Logical Architecture Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Logical Architecture Pkg</em>'.
   * @generated
   */
	LogicalArchitecturePkg createLogicalArchitecturePkg();

	/**
   * Returns a new object of class '<em>Logical Architecture</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Logical Architecture</em>'.
   * @generated
   */
	LogicalArchitecture createLogicalArchitecture();

	/**
   * Returns a new object of class '<em>Logical Function</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Logical Function</em>'.
   * @generated
   */
	LogicalFunction createLogicalFunction();

	/**
   * Returns a new object of class '<em>Logical Function Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Logical Function Pkg</em>'.
   * @generated
   */
	LogicalFunctionPkg createLogicalFunctionPkg();

	/**
   * Returns a new object of class '<em>Logical Component</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Logical Component</em>'.
   * @generated
   */
	LogicalComponent createLogicalComponent();

	/**
   * Returns a new object of class '<em>Logical Component Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Logical Component Pkg</em>'.
   * @generated
   */
	LogicalComponentPkg createLogicalComponentPkg();

	/**
   * Returns a new object of class '<em>Capability Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Capability Realization</em>'.
   * @generated
   */
	CapabilityRealization createCapabilityRealization();

	/**
   * Returns a new object of class '<em>Capability Realization Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Capability Realization Pkg</em>'.
   * @generated
   */
	CapabilityRealizationPkg createCapabilityRealizationPkg();

	/**
   * Returns a new object of class '<em>System Analysis Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>System Analysis Realization</em>'.
   * @generated
   */
	SystemAnalysisRealization createSystemAnalysisRealization();

	/**
   * Returns a new object of class '<em>Context Interface Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Context Interface Realization</em>'.
   * @generated
   */
	ContextInterfaceRealization createContextInterfaceRealization();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	LaPackage getLaPackage();

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	LogicalArchitecturePkg createLogicalArchitecturePkg(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	LogicalArchitecture createLogicalArchitecture(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	LogicalFunction createLogicalFunction(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	LogicalFunctionPkg createLogicalFunctionPkg(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	LogicalComponent createLogicalComponent(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	LogicalComponentPkg createLogicalComponentPkg(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	CapabilityRealization createCapabilityRealization(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	CapabilityRealizationPkg createCapabilityRealizationPkg(String name_p);

	//begin-capella-code
	//end-capella-code
} //LaFactory

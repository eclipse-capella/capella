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
package org.polarsys.capella.core.data.ctx;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.ctx.CtxPackage
 * @generated
 */
public interface CtxFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	CtxFactory eINSTANCE = org.polarsys.capella.core.data.ctx.impl.CtxFactoryImpl.init();

	/**
   * Returns a new object of class '<em>System Analysis</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>System Analysis</em>'.
   * @generated
   */
	SystemAnalysis createSystemAnalysis();

	/**
   * Returns a new object of class '<em>System Function</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>System Function</em>'.
   * @generated
   */
	SystemFunction createSystemFunction();

	/**
   * Returns a new object of class '<em>System Function Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>System Function Pkg</em>'.
   * @generated
   */
	SystemFunctionPkg createSystemFunctionPkg();

	/**
   * Returns a new object of class '<em>System Communication Hook</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>System Communication Hook</em>'.
   * @generated
   */
	SystemCommunicationHook createSystemCommunicationHook();

	/**
   * Returns a new object of class '<em>System Communication</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>System Communication</em>'.
   * @generated
   */
	SystemCommunication createSystemCommunication();

	/**
   * Returns a new object of class '<em>Capability Involvement</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Capability Involvement</em>'.
   * @generated
   */
	CapabilityInvolvement createCapabilityInvolvement();

	/**
   * Returns a new object of class '<em>Mission Involvement</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Mission Involvement</em>'.
   * @generated
   */
	MissionInvolvement createMissionInvolvement();

	/**
   * Returns a new object of class '<em>Mission</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Mission</em>'.
   * @generated
   */
	Mission createMission();

	/**
   * Returns a new object of class '<em>Mission Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Mission Pkg</em>'.
   * @generated
   */
	MissionPkg createMissionPkg();

	/**
   * Returns a new object of class '<em>Capability</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Capability</em>'.
   * @generated
   */
	Capability createCapability();

	/**
   * Returns a new object of class '<em>Capability Exploitation</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Capability Exploitation</em>'.
   * @generated
   */
	CapabilityExploitation createCapabilityExploitation();

	/**
   * Returns a new object of class '<em>Capability Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Capability Pkg</em>'.
   * @generated
   */
	CapabilityPkg createCapabilityPkg();

	/**
   * Returns a new object of class '<em>Operational Analysis Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Operational Analysis Realization</em>'.
   * @generated
   */
	OperationalAnalysisRealization createOperationalAnalysisRealization();

	/**
   * Returns a new object of class '<em>System Component Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>System Component Pkg</em>'.
   * @generated
   */
	SystemComponentPkg createSystemComponentPkg();

	/**
   * Returns a new object of class '<em>System Component</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>System Component</em>'.
   * @generated
   */
	SystemComponent createSystemComponent();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	CtxPackage getCtxPackage();

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	SystemAnalysis createSystemAnalysis(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	SystemFunction createSystemFunction(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	SystemFunctionPkg createSystemFunctionPkg(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	SystemCommunicationHook createSystemCommunicationHook(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Mission createMission(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	MissionPkg createMissionPkg(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Capability createCapability(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	CapabilityPkg createCapabilityPkg(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	SystemComponentPkg createSystemComponentPkg(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	SystemComponent createSystemComponent(String name_p);

	//begin-capella-code
	//end-capella-code
} //CtxFactory

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
package org.polarsys.capella.core.data.capellacommon;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage
 * @generated
 */
public interface CapellacommonFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	CapellacommonFactory eINSTANCE = org.polarsys.capella.core.data.capellacommon.impl.CapellacommonFactoryImpl.init();

	/**
   * Returns a new object of class '<em>Generic Trace</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Generic Trace</em>'.
   * @generated
   */
	GenericTrace createGenericTrace();

	/**
   * Returns a new object of class '<em>Transfo Link</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Transfo Link</em>'.
   * @generated
   */
	TransfoLink createTransfoLink();

	/**
   * Returns a new object of class '<em>Justification Link</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Justification Link</em>'.
   * @generated
   */
	JustificationLink createJustificationLink();

	/**
   * Returns a new object of class '<em>Capability Realization Involvement</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Capability Realization Involvement</em>'.
   * @generated
   */
	CapabilityRealizationInvolvement createCapabilityRealizationInvolvement();

	/**
   * Returns a new object of class '<em>State Machine</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>State Machine</em>'.
   * @generated
   */
	StateMachine createStateMachine();

	/**
   * Returns a new object of class '<em>Region</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Region</em>'.
   * @generated
   */
	Region createRegion();

	/**
   * Returns a new object of class '<em>State</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>State</em>'.
   * @generated
   */
	State createState();

	/**
   * Returns a new object of class '<em>Mode</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Mode</em>'.
   * @generated
   */
	Mode createMode();

	/**
   * Returns a new object of class '<em>Final State</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Final State</em>'.
   * @generated
   */
	FinalState createFinalState();

	/**
   * Returns a new object of class '<em>State Transition</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>State Transition</em>'.
   * @generated
   */
	StateTransition createStateTransition();

	/**
   * Returns a new object of class '<em>Initial Pseudo State</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Initial Pseudo State</em>'.
   * @generated
   */
	InitialPseudoState createInitialPseudoState();

	/**
   * Returns a new object of class '<em>Join Pseudo State</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Join Pseudo State</em>'.
   * @generated
   */
	JoinPseudoState createJoinPseudoState();

	/**
   * Returns a new object of class '<em>Fork Pseudo State</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Fork Pseudo State</em>'.
   * @generated
   */
	ForkPseudoState createForkPseudoState();

	/**
   * Returns a new object of class '<em>Choice Pseudo State</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Choice Pseudo State</em>'.
   * @generated
   */
	ChoicePseudoState createChoicePseudoState();

	/**
   * Returns a new object of class '<em>Terminate Pseudo State</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Terminate Pseudo State</em>'.
   * @generated
   */
	TerminatePseudoState createTerminatePseudoState();

	/**
   * Returns a new object of class '<em>Abstract State Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Abstract State Realization</em>'.
   * @generated
   */
	AbstractStateRealization createAbstractStateRealization();

	/**
   * Returns a new object of class '<em>State Transition Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>State Transition Realization</em>'.
   * @generated
   */
	StateTransitionRealization createStateTransitionRealization();

	/**
   * Returns a new object of class '<em>Shallow History Pseudo State</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Shallow History Pseudo State</em>'.
   * @generated
   */
	ShallowHistoryPseudoState createShallowHistoryPseudoState();

	/**
   * Returns a new object of class '<em>Deep History Pseudo State</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Deep History Pseudo State</em>'.
   * @generated
   */
	DeepHistoryPseudoState createDeepHistoryPseudoState();

	/**
   * Returns a new object of class '<em>Entry Point Pseudo State</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Entry Point Pseudo State</em>'.
   * @generated
   */
	EntryPointPseudoState createEntryPointPseudoState();

	/**
   * Returns a new object of class '<em>Exit Point Pseudo State</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Exit Point Pseudo State</em>'.
   * @generated
   */
	ExitPointPseudoState createExitPointPseudoState();

	/**
   * Returns a new object of class '<em>State Event Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>State Event Realization</em>'.
   * @generated
   */
	StateEventRealization createStateEventRealization();

	/**
   * Returns a new object of class '<em>Change Event</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Change Event</em>'.
   * @generated
   */
	ChangeEvent createChangeEvent();

	/**
   * Returns a new object of class '<em>Time Event</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Time Event</em>'.
   * @generated
   */
	TimeEvent createTimeEvent();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	CapellacommonPackage getCapellacommonPackage();

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	StateMachine createStateMachine(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Region createRegion(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	State createState(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Mode createMode(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	FinalState createFinalState(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	StateTransition createStateTransition(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	InitialPseudoState createInitialPseudoState(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	JoinPseudoState createJoinPseudoState(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ForkPseudoState createForkPseudoState(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ChoicePseudoState createChoicePseudoState(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	TerminatePseudoState createTerminatePseudoState(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ShallowHistoryPseudoState createShallowHistoryPseudoState(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	DeepHistoryPseudoState createDeepHistoryPseudoState(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	EntryPointPseudoState createEntryPointPseudoState(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ExitPointPseudoState createExitPointPseudoState(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ChangeEvent createChangeEvent(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	TimeEvent createTimeEvent(String name_p);

	//begin-capella-code
	//end-capella-code
} //CapellacommonFactory

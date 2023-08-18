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
package org.polarsys.capella.core.data.interaction;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage
 * @generated
 */
public interface InteractionFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	InteractionFactory eINSTANCE = org.polarsys.capella.core.data.interaction.impl.InteractionFactoryImpl.init();

	/**
   * Returns a new object of class '<em>Sequence Message</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Sequence Message</em>'.
   * @generated
   */
	SequenceMessage createSequenceMessage();

	/**
   * Returns a new object of class '<em>Scenario</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Scenario</em>'.
   * @generated
   */
	Scenario createScenario();

	/**
   * Returns a new object of class '<em>Message End</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Message End</em>'.
   * @generated
   */
	MessageEnd createMessageEnd();

	/**
   * Returns a new object of class '<em>Execution</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Execution</em>'.
   * @generated
   */
	Execution createExecution();

	/**
   * Returns a new object of class '<em>Execution End</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Execution End</em>'.
   * @generated
   */
	ExecutionEnd createExecutionEnd();

	/**
   * Returns a new object of class '<em>Creation Event</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Creation Event</em>'.
   * @generated
   */
	CreationEvent createCreationEvent();

	/**
   * Returns a new object of class '<em>Destruction Event</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Destruction Event</em>'.
   * @generated
   */
	DestructionEvent createDestructionEvent();

	/**
   * Returns a new object of class '<em>Execution Event</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Execution Event</em>'.
   * @generated
   */
	ExecutionEvent createExecutionEvent();

	/**
   * Returns a new object of class '<em>Instance Role</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Instance Role</em>'.
   * @generated
   */
	InstanceRole createInstanceRole();

	/**
   * Returns a new object of class '<em>Event Receipt Operation</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Event Receipt Operation</em>'.
   * @generated
   */
	EventReceiptOperation createEventReceiptOperation();

	/**
   * Returns a new object of class '<em>Event Sent Operation</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Event Sent Operation</em>'.
   * @generated
   */
	EventSentOperation createEventSentOperation();

	/**
   * Returns a new object of class '<em>Merge Link</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Merge Link</em>'.
   * @generated
   */
	MergeLink createMergeLink();

	/**
   * Returns a new object of class '<em>Refinement Link</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Refinement Link</em>'.
   * @generated
   */
	RefinementLink createRefinementLink();

	/**
   * Returns a new object of class '<em>Abstract Capability Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Abstract Capability Realization</em>'.
   * @generated
   */
	AbstractCapabilityRealization createAbstractCapabilityRealization();

	/**
   * Returns a new object of class '<em>Abstract Capability Extend</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Abstract Capability Extend</em>'.
   * @generated
   */
	AbstractCapabilityExtend createAbstractCapabilityExtend();

	/**
   * Returns a new object of class '<em>Abstract Capability Extension Point</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Abstract Capability Extension Point</em>'.
   * @generated
   */
	AbstractCapabilityExtensionPoint createAbstractCapabilityExtensionPoint();

	/**
   * Returns a new object of class '<em>Abstract Capability Generalization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Abstract Capability Generalization</em>'.
   * @generated
   */
	AbstractCapabilityGeneralization createAbstractCapabilityGeneralization();

	/**
   * Returns a new object of class '<em>Abstract Capability Include</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Abstract Capability Include</em>'.
   * @generated
   */
	AbstractCapabilityInclude createAbstractCapabilityInclude();

	/**
   * Returns a new object of class '<em>State</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>State</em>'.
   * @generated
   */
	InteractionState createInteractionState();

	/**
   * Returns a new object of class '<em>Use</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Use</em>'.
   * @generated
   */
	InteractionUse createInteractionUse();

	/**
   * Returns a new object of class '<em>Combined Fragment</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Combined Fragment</em>'.
   * @generated
   */
	CombinedFragment createCombinedFragment();

	/**
   * Returns a new object of class '<em>Gate</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Gate</em>'.
   * @generated
   */
	Gate createGate();

	/**
   * Returns a new object of class '<em>Operand</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Operand</em>'.
   * @generated
   */
	InteractionOperand createInteractionOperand();

	/**
   * Returns a new object of class '<em>Fragment End</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Fragment End</em>'.
   * @generated
   */
	FragmentEnd createFragmentEnd();

	/**
   * Returns a new object of class '<em>Functional Chain Abstract Capability Involvement</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Functional Chain Abstract Capability Involvement</em>'.
   * @generated
   */
	FunctionalChainAbstractCapabilityInvolvement createFunctionalChainAbstractCapabilityInvolvement();

	/**
   * Returns a new object of class '<em>Abstract Function Abstract Capability Involvement</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Abstract Function Abstract Capability Involvement</em>'.
   * @generated
   */
	AbstractFunctionAbstractCapabilityInvolvement createAbstractFunctionAbstractCapabilityInvolvement();

	/**
   * Returns a new object of class '<em>Scenario Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Scenario Realization</em>'.
   * @generated
   */
	ScenarioRealization createScenarioRealization();

	/**
   * Returns a new object of class '<em>State Fragment</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>State Fragment</em>'.
   * @generated
   */
	StateFragment createStateFragment();

	/**
   * Returns a new object of class '<em>Arm Timer Event</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Arm Timer Event</em>'.
   * @generated
   */
	ArmTimerEvent createArmTimerEvent();

	/**
   * Returns a new object of class '<em>Cancel Timer Event</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Cancel Timer Event</em>'.
   * @generated
   */
	CancelTimerEvent createCancelTimerEvent();

	/**
   * Returns a new object of class '<em>Constraint Duration</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Constraint Duration</em>'.
   * @generated
   */
	ConstraintDuration createConstraintDuration();

	/**
   * Returns a new object of class '<em>Sequence Message Valuation</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Sequence Message Valuation</em>'.
   * @generated
   */
	SequenceMessageValuation createSequenceMessageValuation();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	InteractionPackage getInteractionPackage();

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	SequenceMessage createSequenceMessage(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Scenario createScenario(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	MessageEnd createMessageEnd(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Execution createExecution(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ExecutionEnd createExecutionEnd(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	CreationEvent createCreationEvent(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	DestructionEvent createDestructionEvent(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ExecutionEvent createExecutionEvent(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	InstanceRole createInstanceRole(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	EventReceiptOperation createEventReceiptOperation(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	EventSentOperation createEventSentOperation(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	AbstractCapabilityExtensionPoint createAbstractCapabilityExtensionPoint(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	InteractionState createInteractionState(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	InteractionUse createInteractionUse(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	CombinedFragment createCombinedFragment(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Gate createGate(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	InteractionOperand createInteractionOperand(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	FragmentEnd createFragmentEnd(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	StateFragment createStateFragment(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ArmTimerEvent createArmTimerEvent(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	CancelTimerEvent createCancelTimerEvent(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ConstraintDuration createConstraintDuration(String name_p);

	//begin-capella-code
	//end-capella-code
} //InteractionFactory

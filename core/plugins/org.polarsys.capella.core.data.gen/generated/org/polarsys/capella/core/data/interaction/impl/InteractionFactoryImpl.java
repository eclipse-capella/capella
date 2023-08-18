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
package org.polarsys.capella.core.data.interaction.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.core.data.interaction.*;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.ArmTimerEvent;
import org.polarsys.capella.core.data.interaction.CancelTimerEvent;
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.ConstraintDuration;
import org.polarsys.capella.core.data.interaction.CreationEvent;
import org.polarsys.capella.core.data.interaction.DestructionEvent;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.ExecutionEvent;
import org.polarsys.capella.core.data.interaction.FragmentEnd;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.Gate;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionOperatorKind;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionState;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.MergeLink;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.RefinementLink;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.ScenarioRealization;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.SequenceMessageValuation;
import org.polarsys.capella.core.data.interaction.StateFragment;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class InteractionFactoryImpl extends EFactoryImpl implements InteractionFactory {
	/**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static InteractionFactory init() {
    try {
      InteractionFactory theInteractionFactory = (InteractionFactory)EPackage.Registry.INSTANCE.getEFactory(InteractionPackage.eNS_URI);
      if (theInteractionFactory != null) {
        return theInteractionFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new InteractionFactoryImpl();
  }

	/**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public InteractionFactoryImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EObject create(EClass eClass) {
    switch (eClass.getClassifierID()) {
      case InteractionPackage.SEQUENCE_MESSAGE: return createSequenceMessage();
      case InteractionPackage.SCENARIO: return createScenario();
      case InteractionPackage.MESSAGE_END: return createMessageEnd();
      case InteractionPackage.EXECUTION: return createExecution();
      case InteractionPackage.EXECUTION_END: return createExecutionEnd();
      case InteractionPackage.CREATION_EVENT: return createCreationEvent();
      case InteractionPackage.DESTRUCTION_EVENT: return createDestructionEvent();
      case InteractionPackage.EXECUTION_EVENT: return createExecutionEvent();
      case InteractionPackage.INSTANCE_ROLE: return createInstanceRole();
      case InteractionPackage.EVENT_RECEIPT_OPERATION: return createEventReceiptOperation();
      case InteractionPackage.EVENT_SENT_OPERATION: return createEventSentOperation();
      case InteractionPackage.MERGE_LINK: return createMergeLink();
      case InteractionPackage.REFINEMENT_LINK: return createRefinementLink();
      case InteractionPackage.ABSTRACT_CAPABILITY_REALIZATION: return createAbstractCapabilityRealization();
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND: return createAbstractCapabilityExtend();
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT: return createAbstractCapabilityExtensionPoint();
      case InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION: return createAbstractCapabilityGeneralization();
      case InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE: return createAbstractCapabilityInclude();
      case InteractionPackage.INTERACTION_STATE: return createInteractionState();
      case InteractionPackage.INTERACTION_USE: return createInteractionUse();
      case InteractionPackage.COMBINED_FRAGMENT: return createCombinedFragment();
      case InteractionPackage.GATE: return createGate();
      case InteractionPackage.INTERACTION_OPERAND: return createInteractionOperand();
      case InteractionPackage.FRAGMENT_END: return createFragmentEnd();
      case InteractionPackage.FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT: return createFunctionalChainAbstractCapabilityInvolvement();
      case InteractionPackage.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT: return createAbstractFunctionAbstractCapabilityInvolvement();
      case InteractionPackage.SCENARIO_REALIZATION: return createScenarioRealization();
      case InteractionPackage.STATE_FRAGMENT: return createStateFragment();
      case InteractionPackage.ARM_TIMER_EVENT: return createArmTimerEvent();
      case InteractionPackage.CANCEL_TIMER_EVENT: return createCancelTimerEvent();
      case InteractionPackage.CONSTRAINT_DURATION: return createConstraintDuration();
      case InteractionPackage.SEQUENCE_MESSAGE_VALUATION: return createSequenceMessageValuation();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
    switch (eDataType.getClassifierID()) {
      case InteractionPackage.MESSAGE_KIND:
        return createMessageKindFromString(eDataType, initialValue);
      case InteractionPackage.SCENARIO_KIND:
        return createScenarioKindFromString(eDataType, initialValue);
      case InteractionPackage.INTERACTION_OPERATOR_KIND:
        return createInteractionOperatorKindFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
    switch (eDataType.getClassifierID()) {
      case InteractionPackage.MESSAGE_KIND:
        return convertMessageKindToString(eDataType, instanceValue);
      case InteractionPackage.SCENARIO_KIND:
        return convertScenarioKindToString(eDataType, instanceValue);
      case InteractionPackage.INTERACTION_OPERATOR_KIND:
        return convertInteractionOperatorKindToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public SequenceMessage createSequenceMessage() {
    SequenceMessageImpl sequenceMessage = new SequenceMessageImpl();
    //begin-capella-code
    sequenceMessage.setId(IdGenerator.createId());
    //end-capella-code
    return sequenceMessage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Scenario createScenario() {
    ScenarioImpl scenario = new ScenarioImpl();
    //begin-capella-code
    scenario.setId(IdGenerator.createId());
    //end-capella-code
    return scenario;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public MessageEnd createMessageEnd() {
    MessageEndImpl messageEnd = new MessageEndImpl();
    //begin-capella-code
    messageEnd.setId(IdGenerator.createId());
    //end-capella-code
    return messageEnd;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Execution createExecution() {
    ExecutionImpl execution = new ExecutionImpl();
    //begin-capella-code
    execution.setId(IdGenerator.createId());
    //end-capella-code
    return execution;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ExecutionEnd createExecutionEnd() {
    ExecutionEndImpl executionEnd = new ExecutionEndImpl();
    //begin-capella-code
    executionEnd.setId(IdGenerator.createId());
    //end-capella-code
    return executionEnd;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CreationEvent createCreationEvent() {
    CreationEventImpl creationEvent = new CreationEventImpl();
    //begin-capella-code
    creationEvent.setId(IdGenerator.createId());
    //end-capella-code
    return creationEvent;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public DestructionEvent createDestructionEvent() {
    DestructionEventImpl destructionEvent = new DestructionEventImpl();
    //begin-capella-code
    destructionEvent.setId(IdGenerator.createId());
    //end-capella-code
    return destructionEvent;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ExecutionEvent createExecutionEvent() {
    ExecutionEventImpl executionEvent = new ExecutionEventImpl();
    //begin-capella-code
    executionEvent.setId(IdGenerator.createId());
    //end-capella-code
    return executionEvent;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public InstanceRole createInstanceRole() {
    InstanceRoleImpl instanceRole = new InstanceRoleImpl();
    //begin-capella-code
    instanceRole.setId(IdGenerator.createId());
    //end-capella-code
    return instanceRole;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EventReceiptOperation createEventReceiptOperation() {
    EventReceiptOperationImpl eventReceiptOperation = new EventReceiptOperationImpl();
    //begin-capella-code
    eventReceiptOperation.setId(IdGenerator.createId());
    //end-capella-code
    return eventReceiptOperation;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EventSentOperation createEventSentOperation() {
    EventSentOperationImpl eventSentOperation = new EventSentOperationImpl();
    //begin-capella-code
    eventSentOperation.setId(IdGenerator.createId());
    //end-capella-code
    return eventSentOperation;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public MergeLink createMergeLink() {
    MergeLinkImpl mergeLink = new MergeLinkImpl();
    //begin-capella-code
    mergeLink.setId(IdGenerator.createId());
    //end-capella-code
    return mergeLink;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public RefinementLink createRefinementLink() {
    RefinementLinkImpl refinementLink = new RefinementLinkImpl();
    //begin-capella-code
    refinementLink.setId(IdGenerator.createId());
    //end-capella-code
    return refinementLink;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public AbstractCapabilityRealization createAbstractCapabilityRealization() {
    AbstractCapabilityRealizationImpl abstractCapabilityRealization = new AbstractCapabilityRealizationImpl();
    //begin-capella-code
    abstractCapabilityRealization.setId(IdGenerator.createId());
    //end-capella-code
    return abstractCapabilityRealization;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public AbstractCapabilityExtend createAbstractCapabilityExtend() {
    AbstractCapabilityExtendImpl abstractCapabilityExtend = new AbstractCapabilityExtendImpl();
    //begin-capella-code
    abstractCapabilityExtend.setId(IdGenerator.createId());
    //end-capella-code
    return abstractCapabilityExtend;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public AbstractCapabilityExtensionPoint createAbstractCapabilityExtensionPoint() {
    AbstractCapabilityExtensionPointImpl abstractCapabilityExtensionPoint = new AbstractCapabilityExtensionPointImpl();
    //begin-capella-code
    abstractCapabilityExtensionPoint.setId(IdGenerator.createId());
    //end-capella-code
    return abstractCapabilityExtensionPoint;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public AbstractCapabilityGeneralization createAbstractCapabilityGeneralization() {
    AbstractCapabilityGeneralizationImpl abstractCapabilityGeneralization = new AbstractCapabilityGeneralizationImpl();
    //begin-capella-code
    abstractCapabilityGeneralization.setId(IdGenerator.createId());
    //end-capella-code
    return abstractCapabilityGeneralization;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public AbstractCapabilityInclude createAbstractCapabilityInclude() {
    AbstractCapabilityIncludeImpl abstractCapabilityInclude = new AbstractCapabilityIncludeImpl();
    //begin-capella-code
    abstractCapabilityInclude.setId(IdGenerator.createId());
    //end-capella-code
    return abstractCapabilityInclude;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public InteractionState createInteractionState() {
    InteractionStateImpl interactionState = new InteractionStateImpl();
    //begin-capella-code
    interactionState.setId(IdGenerator.createId());
    //end-capella-code
    return interactionState;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public InteractionUse createInteractionUse() {
    InteractionUseImpl interactionUse = new InteractionUseImpl();
    //begin-capella-code
    interactionUse.setId(IdGenerator.createId());
    //end-capella-code
    return interactionUse;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CombinedFragment createCombinedFragment() {
    CombinedFragmentImpl combinedFragment = new CombinedFragmentImpl();
    //begin-capella-code
    combinedFragment.setId(IdGenerator.createId());
    //end-capella-code
    return combinedFragment;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Gate createGate() {
    GateImpl gate = new GateImpl();
    //begin-capella-code
    gate.setId(IdGenerator.createId());
    //end-capella-code
    return gate;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public InteractionOperand createInteractionOperand() {
    InteractionOperandImpl interactionOperand = new InteractionOperandImpl();
    //begin-capella-code
    interactionOperand.setId(IdGenerator.createId());
    //end-capella-code
    return interactionOperand;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public FragmentEnd createFragmentEnd() {
    FragmentEndImpl fragmentEnd = new FragmentEndImpl();
    //begin-capella-code
    fragmentEnd.setId(IdGenerator.createId());
    //end-capella-code
    return fragmentEnd;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public FunctionalChainAbstractCapabilityInvolvement createFunctionalChainAbstractCapabilityInvolvement() {
    FunctionalChainAbstractCapabilityInvolvementImpl functionalChainAbstractCapabilityInvolvement = new FunctionalChainAbstractCapabilityInvolvementImpl();
    //begin-capella-code
    functionalChainAbstractCapabilityInvolvement.setId(IdGenerator.createId());
    //end-capella-code
    return functionalChainAbstractCapabilityInvolvement;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public AbstractFunctionAbstractCapabilityInvolvement createAbstractFunctionAbstractCapabilityInvolvement() {
    AbstractFunctionAbstractCapabilityInvolvementImpl abstractFunctionAbstractCapabilityInvolvement = new AbstractFunctionAbstractCapabilityInvolvementImpl();
    //begin-capella-code
    abstractFunctionAbstractCapabilityInvolvement.setId(IdGenerator.createId());
    //end-capella-code
    return abstractFunctionAbstractCapabilityInvolvement;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ScenarioRealization createScenarioRealization() {
    ScenarioRealizationImpl scenarioRealization = new ScenarioRealizationImpl();
    //begin-capella-code
    scenarioRealization.setId(IdGenerator.createId());
    //end-capella-code
    return scenarioRealization;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public StateFragment createStateFragment() {
    StateFragmentImpl stateFragment = new StateFragmentImpl();
    //begin-capella-code
    stateFragment.setId(IdGenerator.createId());
    //end-capella-code
    return stateFragment;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ArmTimerEvent createArmTimerEvent() {
    ArmTimerEventImpl armTimerEvent = new ArmTimerEventImpl();
    //begin-capella-code
    armTimerEvent.setId(IdGenerator.createId());
    //end-capella-code
    return armTimerEvent;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CancelTimerEvent createCancelTimerEvent() {
    CancelTimerEventImpl cancelTimerEvent = new CancelTimerEventImpl();
    //begin-capella-code
    cancelTimerEvent.setId(IdGenerator.createId());
    //end-capella-code
    return cancelTimerEvent;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ConstraintDuration createConstraintDuration() {
    ConstraintDurationImpl constraintDuration = new ConstraintDurationImpl();
    //begin-capella-code
    constraintDuration.setId(IdGenerator.createId());
    //end-capella-code
    return constraintDuration;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public SequenceMessageValuation createSequenceMessageValuation() {
    SequenceMessageValuationImpl sequenceMessageValuation = new SequenceMessageValuationImpl();
    //begin-capella-code
    sequenceMessageValuation.setId(IdGenerator.createId());
    //end-capella-code
    return sequenceMessageValuation;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public MessageKind createMessageKindFromString(EDataType eDataType, String initialValue) {
    MessageKind result = MessageKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertMessageKindToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ScenarioKind createScenarioKindFromString(EDataType eDataType, String initialValue) {
    ScenarioKind result = ScenarioKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertScenarioKindToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public InteractionOperatorKind createInteractionOperatorKindFromString(EDataType eDataType, String initialValue) {
    InteractionOperatorKind result = InteractionOperatorKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertInteractionOperatorKindToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public InteractionPackage getInteractionPackage() {
    return (InteractionPackage)getEPackage();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
	@Deprecated
	public static InteractionPackage getPackage() {
    return InteractionPackage.eINSTANCE;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public SequenceMessage createSequenceMessage(String name_p) {
    SequenceMessage sequenceMessage = createSequenceMessage();
    sequenceMessage.setName(name_p);	  
    return sequenceMessage;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Scenario createScenario(String name_p) {
    Scenario scenario = createScenario();
    scenario.setName(name_p);	  
    return scenario;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public MessageEnd createMessageEnd(String name_p) {
    MessageEnd messageEnd = createMessageEnd();
    messageEnd.setName(name_p);	  
    return messageEnd;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Execution createExecution(String name_p) {
    Execution execution = createExecution();
    execution.setName(name_p);	  
    return execution;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ExecutionEnd createExecutionEnd(String name_p) {
    ExecutionEnd executionEnd = createExecutionEnd();
    executionEnd.setName(name_p);	  
    return executionEnd;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public CreationEvent createCreationEvent(String name_p) {
    CreationEvent creationEvent = createCreationEvent();
    creationEvent.setName(name_p);	  
    return creationEvent;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public DestructionEvent createDestructionEvent(String name_p) {
    DestructionEvent destructionEvent = createDestructionEvent();
    destructionEvent.setName(name_p);	  
    return destructionEvent;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ExecutionEvent createExecutionEvent(String name_p) {
    ExecutionEvent executionEvent = createExecutionEvent();
    executionEvent.setName(name_p);	  
    return executionEvent;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public InstanceRole createInstanceRole(String name_p) {
    InstanceRole instanceRole = createInstanceRole();
    instanceRole.setName(name_p);	  
    return instanceRole;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public EventReceiptOperation createEventReceiptOperation(String name_p) {
    EventReceiptOperation eventReceiptOperation = createEventReceiptOperation();
    eventReceiptOperation.setName(name_p);	  
    return eventReceiptOperation;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public EventSentOperation createEventSentOperation(String name_p) {
    EventSentOperation eventSentOperation = createEventSentOperation();
    eventSentOperation.setName(name_p);	  
    return eventSentOperation;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public AbstractCapabilityExtensionPoint createAbstractCapabilityExtensionPoint(String name_p) {
    AbstractCapabilityExtensionPoint abstractCapabilityExtensionPoint = createAbstractCapabilityExtensionPoint();
    abstractCapabilityExtensionPoint.setName(name_p);	  
    return abstractCapabilityExtensionPoint;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public InteractionState createInteractionState(String name_p) {
    InteractionState interactionState = createInteractionState();
    interactionState.setName(name_p);	  
    return interactionState;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public InteractionUse createInteractionUse(String name_p) {
    InteractionUse interactionUse = createInteractionUse();
    interactionUse.setName(name_p);	  
    return interactionUse;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public CombinedFragment createCombinedFragment(String name_p) {
    CombinedFragment combinedFragment = createCombinedFragment();
    combinedFragment.setName(name_p);	  
    return combinedFragment;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Gate createGate(String name_p) {
    Gate gate = createGate();
    gate.setName(name_p);	  
    return gate;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public InteractionOperand createInteractionOperand(String name_p) {
    InteractionOperand interactionOperand = createInteractionOperand();
    interactionOperand.setName(name_p);	  
    return interactionOperand;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public FragmentEnd createFragmentEnd(String name_p) {
    FragmentEnd fragmentEnd = createFragmentEnd();
    fragmentEnd.setName(name_p);	  
    return fragmentEnd;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public StateFragment createStateFragment(String name_p) {
    StateFragment stateFragment = createStateFragment();
    stateFragment.setName(name_p);	  
    return stateFragment;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ArmTimerEvent createArmTimerEvent(String name_p) {
    ArmTimerEvent armTimerEvent = createArmTimerEvent();
    armTimerEvent.setName(name_p);	  
    return armTimerEvent;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public CancelTimerEvent createCancelTimerEvent(String name_p) {
    CancelTimerEvent cancelTimerEvent = createCancelTimerEvent();
    cancelTimerEvent.setName(name_p);	  
    return cancelTimerEvent;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ConstraintDuration createConstraintDuration(String name_p) {
    ConstraintDuration constraintDuration = createConstraintDuration();
    constraintDuration.setName(name_p);	  
    return constraintDuration;
  }

	//begin-capella-code

	//end-capella-code
} //InteractionFactoryImpl

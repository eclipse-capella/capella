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
package org.polarsys.capella.core.data.interaction.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.polarsys.capella.common.data.behavior.AbstractBehavior;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractRelationship;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacore.Allocation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.NamedRelationship;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer;
import org.polarsys.capella.core.data.interaction.*;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.AbstractFragment;
import org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.ArmTimerEvent;
import org.polarsys.capella.core.data.interaction.CancelTimerEvent;
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.ConstraintDuration;
import org.polarsys.capella.core.data.interaction.CreationEvent;
import org.polarsys.capella.core.data.interaction.DestructionEvent;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.ExecutionEvent;
import org.polarsys.capella.core.data.interaction.FragmentEnd;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.Gate;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionState;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.MergeLink;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.RefinementLink;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioRealization;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.SequenceMessageValuation;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.kitalpha.emde.model.Element;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage
 * @generated
 */
public class InteractionSwitch<T> extends Switch<T> {
	/**
   * The cached model package
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static InteractionPackage modelPackage;

	/**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public InteractionSwitch() {
    if (modelPackage == null) {
      modelPackage = InteractionPackage.eINSTANCE;
    }
  }

	/**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
    return ePackage == modelPackage;
  }

	/**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
    switch (classifierID) {
      case InteractionPackage.SEQUENCE_MESSAGE: {
        SequenceMessage sequenceMessage = (SequenceMessage)theEObject;
        T result = caseSequenceMessage(sequenceMessage);
        if (result == null) result = caseNamedElement(sequenceMessage);
        if (result == null) result = caseAbstractNamedElement(sequenceMessage);
        if (result == null) result = caseCapellaElement(sequenceMessage);
        if (result == null) result = caseTraceableElement(sequenceMessage);
        if (result == null) result = casePublishableElement(sequenceMessage);
        if (result == null) result = caseModelElement(sequenceMessage);
        if (result == null) result = caseExtensibleElement(sequenceMessage);
        if (result == null) result = caseElement(sequenceMessage);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.SCENARIO: {
        Scenario scenario = (Scenario)theEObject;
        T result = caseScenario(scenario);
        if (result == null) result = caseNamespace(scenario);
        if (result == null) result = caseAbstractBehavior(scenario);
        if (result == null) result = caseNamedElement(scenario);
        if (result == null) result = caseAbstractNamedElement(scenario);
        if (result == null) result = caseCapellaElement(scenario);
        if (result == null) result = caseTraceableElement(scenario);
        if (result == null) result = casePublishableElement(scenario);
        if (result == null) result = caseModelElement(scenario);
        if (result == null) result = caseExtensibleElement(scenario);
        if (result == null) result = caseElement(scenario);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.MESSAGE_END: {
        MessageEnd messageEnd = (MessageEnd)theEObject;
        T result = caseMessageEnd(messageEnd);
        if (result == null) result = caseAbstractEnd(messageEnd);
        if (result == null) result = caseInteractionFragment(messageEnd);
        if (result == null) result = caseNamedElement(messageEnd);
        if (result == null) result = caseAbstractNamedElement(messageEnd);
        if (result == null) result = caseCapellaElement(messageEnd);
        if (result == null) result = caseTraceableElement(messageEnd);
        if (result == null) result = casePublishableElement(messageEnd);
        if (result == null) result = caseModelElement(messageEnd);
        if (result == null) result = caseExtensibleElement(messageEnd);
        if (result == null) result = caseElement(messageEnd);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.EXECUTION: {
        Execution execution = (Execution)theEObject;
        T result = caseExecution(execution);
        if (result == null) result = caseTimeLapse(execution);
        if (result == null) result = caseNamedElement(execution);
        if (result == null) result = caseAbstractNamedElement(execution);
        if (result == null) result = caseCapellaElement(execution);
        if (result == null) result = caseTraceableElement(execution);
        if (result == null) result = casePublishableElement(execution);
        if (result == null) result = caseModelElement(execution);
        if (result == null) result = caseExtensibleElement(execution);
        if (result == null) result = caseElement(execution);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.EXECUTION_END: {
        ExecutionEnd executionEnd = (ExecutionEnd)theEObject;
        T result = caseExecutionEnd(executionEnd);
        if (result == null) result = caseAbstractEnd(executionEnd);
        if (result == null) result = caseInteractionFragment(executionEnd);
        if (result == null) result = caseNamedElement(executionEnd);
        if (result == null) result = caseAbstractNamedElement(executionEnd);
        if (result == null) result = caseCapellaElement(executionEnd);
        if (result == null) result = caseTraceableElement(executionEnd);
        if (result == null) result = casePublishableElement(executionEnd);
        if (result == null) result = caseModelElement(executionEnd);
        if (result == null) result = caseExtensibleElement(executionEnd);
        if (result == null) result = caseElement(executionEnd);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.CREATION_EVENT: {
        CreationEvent creationEvent = (CreationEvent)theEObject;
        T result = caseCreationEvent(creationEvent);
        if (result == null) result = caseEvent(creationEvent);
        if (result == null) result = caseNamedElement(creationEvent);
        if (result == null) result = caseAbstractEvent(creationEvent);
        if (result == null) result = caseCapellaElement(creationEvent);
        if (result == null) result = caseAbstractType(creationEvent);
        if (result == null) result = caseAbstractNamedElement(creationEvent);
        if (result == null) result = caseTraceableElement(creationEvent);
        if (result == null) result = casePublishableElement(creationEvent);
        if (result == null) result = caseModelElement(creationEvent);
        if (result == null) result = caseExtensibleElement(creationEvent);
        if (result == null) result = caseElement(creationEvent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.DESTRUCTION_EVENT: {
        DestructionEvent destructionEvent = (DestructionEvent)theEObject;
        T result = caseDestructionEvent(destructionEvent);
        if (result == null) result = caseEvent(destructionEvent);
        if (result == null) result = caseNamedElement(destructionEvent);
        if (result == null) result = caseAbstractEvent(destructionEvent);
        if (result == null) result = caseCapellaElement(destructionEvent);
        if (result == null) result = caseAbstractType(destructionEvent);
        if (result == null) result = caseAbstractNamedElement(destructionEvent);
        if (result == null) result = caseTraceableElement(destructionEvent);
        if (result == null) result = casePublishableElement(destructionEvent);
        if (result == null) result = caseModelElement(destructionEvent);
        if (result == null) result = caseExtensibleElement(destructionEvent);
        if (result == null) result = caseElement(destructionEvent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.EXECUTION_EVENT: {
        ExecutionEvent executionEvent = (ExecutionEvent)theEObject;
        T result = caseExecutionEvent(executionEvent);
        if (result == null) result = caseEvent(executionEvent);
        if (result == null) result = caseNamedElement(executionEvent);
        if (result == null) result = caseAbstractEvent(executionEvent);
        if (result == null) result = caseCapellaElement(executionEvent);
        if (result == null) result = caseAbstractType(executionEvent);
        if (result == null) result = caseAbstractNamedElement(executionEvent);
        if (result == null) result = caseTraceableElement(executionEvent);
        if (result == null) result = casePublishableElement(executionEvent);
        if (result == null) result = caseModelElement(executionEvent);
        if (result == null) result = caseExtensibleElement(executionEvent);
        if (result == null) result = caseElement(executionEvent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.INSTANCE_ROLE: {
        InstanceRole instanceRole = (InstanceRole)theEObject;
        T result = caseInstanceRole(instanceRole);
        if (result == null) result = caseNamedElement(instanceRole);
        if (result == null) result = caseAbstractNamedElement(instanceRole);
        if (result == null) result = caseCapellaElement(instanceRole);
        if (result == null) result = caseTraceableElement(instanceRole);
        if (result == null) result = casePublishableElement(instanceRole);
        if (result == null) result = caseModelElement(instanceRole);
        if (result == null) result = caseExtensibleElement(instanceRole);
        if (result == null) result = caseElement(instanceRole);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.ABSTRACT_END: {
        AbstractEnd abstractEnd = (AbstractEnd)theEObject;
        T result = caseAbstractEnd(abstractEnd);
        if (result == null) result = caseInteractionFragment(abstractEnd);
        if (result == null) result = caseNamedElement(abstractEnd);
        if (result == null) result = caseAbstractNamedElement(abstractEnd);
        if (result == null) result = caseCapellaElement(abstractEnd);
        if (result == null) result = caseTraceableElement(abstractEnd);
        if (result == null) result = casePublishableElement(abstractEnd);
        if (result == null) result = caseModelElement(abstractEnd);
        if (result == null) result = caseExtensibleElement(abstractEnd);
        if (result == null) result = caseElement(abstractEnd);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.EVENT: {
        Event event = (Event)theEObject;
        T result = caseEvent(event);
        if (result == null) result = caseNamedElement(event);
        if (result == null) result = caseAbstractEvent(event);
        if (result == null) result = caseCapellaElement(event);
        if (result == null) result = caseAbstractType(event);
        if (result == null) result = caseAbstractNamedElement(event);
        if (result == null) result = caseTraceableElement(event);
        if (result == null) result = casePublishableElement(event);
        if (result == null) result = caseModelElement(event);
        if (result == null) result = caseExtensibleElement(event);
        if (result == null) result = caseElement(event);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.EVENT_RECEIPT_OPERATION: {
        EventReceiptOperation eventReceiptOperation = (EventReceiptOperation)theEObject;
        T result = caseEventReceiptOperation(eventReceiptOperation);
        if (result == null) result = caseEvent(eventReceiptOperation);
        if (result == null) result = caseNamedElement(eventReceiptOperation);
        if (result == null) result = caseAbstractEvent(eventReceiptOperation);
        if (result == null) result = caseCapellaElement(eventReceiptOperation);
        if (result == null) result = caseAbstractType(eventReceiptOperation);
        if (result == null) result = caseAbstractNamedElement(eventReceiptOperation);
        if (result == null) result = caseTraceableElement(eventReceiptOperation);
        if (result == null) result = casePublishableElement(eventReceiptOperation);
        if (result == null) result = caseModelElement(eventReceiptOperation);
        if (result == null) result = caseExtensibleElement(eventReceiptOperation);
        if (result == null) result = caseElement(eventReceiptOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.EVENT_SENT_OPERATION: {
        EventSentOperation eventSentOperation = (EventSentOperation)theEObject;
        T result = caseEventSentOperation(eventSentOperation);
        if (result == null) result = caseEvent(eventSentOperation);
        if (result == null) result = caseNamedElement(eventSentOperation);
        if (result == null) result = caseAbstractEvent(eventSentOperation);
        if (result == null) result = caseCapellaElement(eventSentOperation);
        if (result == null) result = caseAbstractType(eventSentOperation);
        if (result == null) result = caseAbstractNamedElement(eventSentOperation);
        if (result == null) result = caseTraceableElement(eventSentOperation);
        if (result == null) result = casePublishableElement(eventSentOperation);
        if (result == null) result = caseModelElement(eventSentOperation);
        if (result == null) result = caseExtensibleElement(eventSentOperation);
        if (result == null) result = caseElement(eventSentOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.MERGE_LINK: {
        MergeLink mergeLink = (MergeLink)theEObject;
        T result = caseMergeLink(mergeLink);
        if (result == null) result = caseTrace(mergeLink);
        if (result == null) result = caseRelationship(mergeLink);
        if (result == null) result = caseAbstractTrace(mergeLink);
        if (result == null) result = caseAbstractRelationship(mergeLink);
        if (result == null) result = caseCapellaElement(mergeLink);
        if (result == null) result = caseTraceableElement(mergeLink);
        if (result == null) result = casePublishableElement(mergeLink);
        if (result == null) result = caseModelElement(mergeLink);
        if (result == null) result = caseExtensibleElement(mergeLink);
        if (result == null) result = caseElement(mergeLink);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.REFINEMENT_LINK: {
        RefinementLink refinementLink = (RefinementLink)theEObject;
        T result = caseRefinementLink(refinementLink);
        if (result == null) result = caseTrace(refinementLink);
        if (result == null) result = caseRelationship(refinementLink);
        if (result == null) result = caseAbstractTrace(refinementLink);
        if (result == null) result = caseAbstractRelationship(refinementLink);
        if (result == null) result = caseCapellaElement(refinementLink);
        if (result == null) result = caseTraceableElement(refinementLink);
        if (result == null) result = casePublishableElement(refinementLink);
        if (result == null) result = caseModelElement(refinementLink);
        if (result == null) result = caseExtensibleElement(refinementLink);
        if (result == null) result = caseElement(refinementLink);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.ABSTRACT_CAPABILITY_REALIZATION: {
        AbstractCapabilityRealization abstractCapabilityRealization = (AbstractCapabilityRealization)theEObject;
        T result = caseAbstractCapabilityRealization(abstractCapabilityRealization);
        if (result == null) result = caseAllocation(abstractCapabilityRealization);
        if (result == null) result = caseRelationship(abstractCapabilityRealization);
        if (result == null) result = caseAbstractTrace(abstractCapabilityRealization);
        if (result == null) result = caseAbstractRelationship(abstractCapabilityRealization);
        if (result == null) result = caseCapellaElement(abstractCapabilityRealization);
        if (result == null) result = caseTraceableElement(abstractCapabilityRealization);
        if (result == null) result = casePublishableElement(abstractCapabilityRealization);
        if (result == null) result = caseModelElement(abstractCapabilityRealization);
        if (result == null) result = caseExtensibleElement(abstractCapabilityRealization);
        if (result == null) result = caseElement(abstractCapabilityRealization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.ABSTRACT_CAPABILITY: {
        AbstractCapability abstractCapability = (AbstractCapability)theEObject;
        T result = caseAbstractCapability(abstractCapability);
        if (result == null) result = caseStructure(abstractCapability);
        if (result == null) result = caseInvolverElement(abstractCapability);
        if (result == null) result = caseAbstractFunctionalChainContainer(abstractCapability);
        if (result == null) result = caseNamespace(abstractCapability);
        if (result == null) result = caseNamedElement(abstractCapability);
        if (result == null) result = caseAbstractNamedElement(abstractCapability);
        if (result == null) result = caseCapellaElement(abstractCapability);
        if (result == null) result = caseTraceableElement(abstractCapability);
        if (result == null) result = casePublishableElement(abstractCapability);
        if (result == null) result = caseModelElement(abstractCapability);
        if (result == null) result = caseExtensibleElement(abstractCapability);
        if (result == null) result = caseElement(abstractCapability);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND: {
        AbstractCapabilityExtend abstractCapabilityExtend = (AbstractCapabilityExtend)theEObject;
        T result = caseAbstractCapabilityExtend(abstractCapabilityExtend);
        if (result == null) result = caseRelationship(abstractCapabilityExtend);
        if (result == null) result = caseAbstractRelationship(abstractCapabilityExtend);
        if (result == null) result = caseCapellaElement(abstractCapabilityExtend);
        if (result == null) result = caseTraceableElement(abstractCapabilityExtend);
        if (result == null) result = casePublishableElement(abstractCapabilityExtend);
        if (result == null) result = caseModelElement(abstractCapabilityExtend);
        if (result == null) result = caseExtensibleElement(abstractCapabilityExtend);
        if (result == null) result = caseElement(abstractCapabilityExtend);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT: {
        AbstractCapabilityExtensionPoint abstractCapabilityExtensionPoint = (AbstractCapabilityExtensionPoint)theEObject;
        T result = caseAbstractCapabilityExtensionPoint(abstractCapabilityExtensionPoint);
        if (result == null) result = caseNamedRelationship(abstractCapabilityExtensionPoint);
        if (result == null) result = caseRelationship(abstractCapabilityExtensionPoint);
        if (result == null) result = caseNamedElement(abstractCapabilityExtensionPoint);
        if (result == null) result = caseAbstractRelationship(abstractCapabilityExtensionPoint);
        if (result == null) result = caseCapellaElement(abstractCapabilityExtensionPoint);
        if (result == null) result = caseAbstractNamedElement(abstractCapabilityExtensionPoint);
        if (result == null) result = caseTraceableElement(abstractCapabilityExtensionPoint);
        if (result == null) result = casePublishableElement(abstractCapabilityExtensionPoint);
        if (result == null) result = caseModelElement(abstractCapabilityExtensionPoint);
        if (result == null) result = caseExtensibleElement(abstractCapabilityExtensionPoint);
        if (result == null) result = caseElement(abstractCapabilityExtensionPoint);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.ABSTRACT_CAPABILITY_GENERALIZATION: {
        AbstractCapabilityGeneralization abstractCapabilityGeneralization = (AbstractCapabilityGeneralization)theEObject;
        T result = caseAbstractCapabilityGeneralization(abstractCapabilityGeneralization);
        if (result == null) result = caseRelationship(abstractCapabilityGeneralization);
        if (result == null) result = caseAbstractRelationship(abstractCapabilityGeneralization);
        if (result == null) result = caseCapellaElement(abstractCapabilityGeneralization);
        if (result == null) result = caseTraceableElement(abstractCapabilityGeneralization);
        if (result == null) result = casePublishableElement(abstractCapabilityGeneralization);
        if (result == null) result = caseModelElement(abstractCapabilityGeneralization);
        if (result == null) result = caseExtensibleElement(abstractCapabilityGeneralization);
        if (result == null) result = caseElement(abstractCapabilityGeneralization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.ABSTRACT_CAPABILITY_INCLUDE: {
        AbstractCapabilityInclude abstractCapabilityInclude = (AbstractCapabilityInclude)theEObject;
        T result = caseAbstractCapabilityInclude(abstractCapabilityInclude);
        if (result == null) result = caseRelationship(abstractCapabilityInclude);
        if (result == null) result = caseAbstractRelationship(abstractCapabilityInclude);
        if (result == null) result = caseCapellaElement(abstractCapabilityInclude);
        if (result == null) result = caseTraceableElement(abstractCapabilityInclude);
        if (result == null) result = casePublishableElement(abstractCapabilityInclude);
        if (result == null) result = caseModelElement(abstractCapabilityInclude);
        if (result == null) result = caseExtensibleElement(abstractCapabilityInclude);
        if (result == null) result = caseElement(abstractCapabilityInclude);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.INTERACTION_FRAGMENT: {
        InteractionFragment interactionFragment = (InteractionFragment)theEObject;
        T result = caseInteractionFragment(interactionFragment);
        if (result == null) result = caseNamedElement(interactionFragment);
        if (result == null) result = caseAbstractNamedElement(interactionFragment);
        if (result == null) result = caseCapellaElement(interactionFragment);
        if (result == null) result = caseTraceableElement(interactionFragment);
        if (result == null) result = casePublishableElement(interactionFragment);
        if (result == null) result = caseModelElement(interactionFragment);
        if (result == null) result = caseExtensibleElement(interactionFragment);
        if (result == null) result = caseElement(interactionFragment);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.INTERACTION_STATE: {
        InteractionState interactionState = (InteractionState)theEObject;
        T result = caseInteractionState(interactionState);
        if (result == null) result = caseInteractionFragment(interactionState);
        if (result == null) result = caseNamedElement(interactionState);
        if (result == null) result = caseAbstractNamedElement(interactionState);
        if (result == null) result = caseCapellaElement(interactionState);
        if (result == null) result = caseTraceableElement(interactionState);
        if (result == null) result = casePublishableElement(interactionState);
        if (result == null) result = caseModelElement(interactionState);
        if (result == null) result = caseExtensibleElement(interactionState);
        if (result == null) result = caseElement(interactionState);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.INTERACTION_USE: {
        InteractionUse interactionUse = (InteractionUse)theEObject;
        T result = caseInteractionUse(interactionUse);
        if (result == null) result = caseAbstractFragment(interactionUse);
        if (result == null) result = caseTimeLapse(interactionUse);
        if (result == null) result = caseNamedElement(interactionUse);
        if (result == null) result = caseAbstractNamedElement(interactionUse);
        if (result == null) result = caseCapellaElement(interactionUse);
        if (result == null) result = caseTraceableElement(interactionUse);
        if (result == null) result = casePublishableElement(interactionUse);
        if (result == null) result = caseModelElement(interactionUse);
        if (result == null) result = caseExtensibleElement(interactionUse);
        if (result == null) result = caseElement(interactionUse);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.COMBINED_FRAGMENT: {
        CombinedFragment combinedFragment = (CombinedFragment)theEObject;
        T result = caseCombinedFragment(combinedFragment);
        if (result == null) result = caseAbstractFragment(combinedFragment);
        if (result == null) result = caseTimeLapse(combinedFragment);
        if (result == null) result = caseNamedElement(combinedFragment);
        if (result == null) result = caseAbstractNamedElement(combinedFragment);
        if (result == null) result = caseCapellaElement(combinedFragment);
        if (result == null) result = caseTraceableElement(combinedFragment);
        if (result == null) result = casePublishableElement(combinedFragment);
        if (result == null) result = caseModelElement(combinedFragment);
        if (result == null) result = caseExtensibleElement(combinedFragment);
        if (result == null) result = caseElement(combinedFragment);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.GATE: {
        Gate gate = (Gate)theEObject;
        T result = caseGate(gate);
        if (result == null) result = caseMessageEnd(gate);
        if (result == null) result = caseAbstractEnd(gate);
        if (result == null) result = caseInteractionFragment(gate);
        if (result == null) result = caseNamedElement(gate);
        if (result == null) result = caseAbstractNamedElement(gate);
        if (result == null) result = caseCapellaElement(gate);
        if (result == null) result = caseTraceableElement(gate);
        if (result == null) result = casePublishableElement(gate);
        if (result == null) result = caseModelElement(gate);
        if (result == null) result = caseExtensibleElement(gate);
        if (result == null) result = caseElement(gate);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.INTERACTION_OPERAND: {
        InteractionOperand interactionOperand = (InteractionOperand)theEObject;
        T result = caseInteractionOperand(interactionOperand);
        if (result == null) result = caseInteractionFragment(interactionOperand);
        if (result == null) result = caseNamedElement(interactionOperand);
        if (result == null) result = caseAbstractNamedElement(interactionOperand);
        if (result == null) result = caseCapellaElement(interactionOperand);
        if (result == null) result = caseTraceableElement(interactionOperand);
        if (result == null) result = casePublishableElement(interactionOperand);
        if (result == null) result = caseModelElement(interactionOperand);
        if (result == null) result = caseExtensibleElement(interactionOperand);
        if (result == null) result = caseElement(interactionOperand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.TIME_LAPSE: {
        TimeLapse timeLapse = (TimeLapse)theEObject;
        T result = caseTimeLapse(timeLapse);
        if (result == null) result = caseNamedElement(timeLapse);
        if (result == null) result = caseAbstractNamedElement(timeLapse);
        if (result == null) result = caseCapellaElement(timeLapse);
        if (result == null) result = caseTraceableElement(timeLapse);
        if (result == null) result = casePublishableElement(timeLapse);
        if (result == null) result = caseModelElement(timeLapse);
        if (result == null) result = caseExtensibleElement(timeLapse);
        if (result == null) result = caseElement(timeLapse);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.ABSTRACT_FRAGMENT: {
        AbstractFragment abstractFragment = (AbstractFragment)theEObject;
        T result = caseAbstractFragment(abstractFragment);
        if (result == null) result = caseTimeLapse(abstractFragment);
        if (result == null) result = caseNamedElement(abstractFragment);
        if (result == null) result = caseAbstractNamedElement(abstractFragment);
        if (result == null) result = caseCapellaElement(abstractFragment);
        if (result == null) result = caseTraceableElement(abstractFragment);
        if (result == null) result = casePublishableElement(abstractFragment);
        if (result == null) result = caseModelElement(abstractFragment);
        if (result == null) result = caseExtensibleElement(abstractFragment);
        if (result == null) result = caseElement(abstractFragment);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.FRAGMENT_END: {
        FragmentEnd fragmentEnd = (FragmentEnd)theEObject;
        T result = caseFragmentEnd(fragmentEnd);
        if (result == null) result = caseInteractionFragment(fragmentEnd);
        if (result == null) result = caseNamedElement(fragmentEnd);
        if (result == null) result = caseAbstractNamedElement(fragmentEnd);
        if (result == null) result = caseCapellaElement(fragmentEnd);
        if (result == null) result = caseTraceableElement(fragmentEnd);
        if (result == null) result = casePublishableElement(fragmentEnd);
        if (result == null) result = caseModelElement(fragmentEnd);
        if (result == null) result = caseExtensibleElement(fragmentEnd);
        if (result == null) result = caseElement(fragmentEnd);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT: {
        FunctionalChainAbstractCapabilityInvolvement functionalChainAbstractCapabilityInvolvement = (FunctionalChainAbstractCapabilityInvolvement)theEObject;
        T result = caseFunctionalChainAbstractCapabilityInvolvement(functionalChainAbstractCapabilityInvolvement);
        if (result == null) result = caseInvolvement(functionalChainAbstractCapabilityInvolvement);
        if (result == null) result = caseRelationship(functionalChainAbstractCapabilityInvolvement);
        if (result == null) result = caseAbstractRelationship(functionalChainAbstractCapabilityInvolvement);
        if (result == null) result = caseCapellaElement(functionalChainAbstractCapabilityInvolvement);
        if (result == null) result = caseTraceableElement(functionalChainAbstractCapabilityInvolvement);
        if (result == null) result = casePublishableElement(functionalChainAbstractCapabilityInvolvement);
        if (result == null) result = caseModelElement(functionalChainAbstractCapabilityInvolvement);
        if (result == null) result = caseExtensibleElement(functionalChainAbstractCapabilityInvolvement);
        if (result == null) result = caseElement(functionalChainAbstractCapabilityInvolvement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT: {
        AbstractFunctionAbstractCapabilityInvolvement abstractFunctionAbstractCapabilityInvolvement = (AbstractFunctionAbstractCapabilityInvolvement)theEObject;
        T result = caseAbstractFunctionAbstractCapabilityInvolvement(abstractFunctionAbstractCapabilityInvolvement);
        if (result == null) result = caseInvolvement(abstractFunctionAbstractCapabilityInvolvement);
        if (result == null) result = caseRelationship(abstractFunctionAbstractCapabilityInvolvement);
        if (result == null) result = caseAbstractRelationship(abstractFunctionAbstractCapabilityInvolvement);
        if (result == null) result = caseCapellaElement(abstractFunctionAbstractCapabilityInvolvement);
        if (result == null) result = caseTraceableElement(abstractFunctionAbstractCapabilityInvolvement);
        if (result == null) result = casePublishableElement(abstractFunctionAbstractCapabilityInvolvement);
        if (result == null) result = caseModelElement(abstractFunctionAbstractCapabilityInvolvement);
        if (result == null) result = caseExtensibleElement(abstractFunctionAbstractCapabilityInvolvement);
        if (result == null) result = caseElement(abstractFunctionAbstractCapabilityInvolvement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.SCENARIO_REALIZATION: {
        ScenarioRealization scenarioRealization = (ScenarioRealization)theEObject;
        T result = caseScenarioRealization(scenarioRealization);
        if (result == null) result = caseAllocation(scenarioRealization);
        if (result == null) result = caseRelationship(scenarioRealization);
        if (result == null) result = caseAbstractTrace(scenarioRealization);
        if (result == null) result = caseAbstractRelationship(scenarioRealization);
        if (result == null) result = caseCapellaElement(scenarioRealization);
        if (result == null) result = caseTraceableElement(scenarioRealization);
        if (result == null) result = casePublishableElement(scenarioRealization);
        if (result == null) result = caseModelElement(scenarioRealization);
        if (result == null) result = caseExtensibleElement(scenarioRealization);
        if (result == null) result = caseElement(scenarioRealization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.STATE_FRAGMENT: {
        StateFragment stateFragment = (StateFragment)theEObject;
        T result = caseStateFragment(stateFragment);
        if (result == null) result = caseTimeLapse(stateFragment);
        if (result == null) result = caseNamedElement(stateFragment);
        if (result == null) result = caseAbstractNamedElement(stateFragment);
        if (result == null) result = caseCapellaElement(stateFragment);
        if (result == null) result = caseTraceableElement(stateFragment);
        if (result == null) result = casePublishableElement(stateFragment);
        if (result == null) result = caseModelElement(stateFragment);
        if (result == null) result = caseExtensibleElement(stateFragment);
        if (result == null) result = caseElement(stateFragment);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.ARM_TIMER_EVENT: {
        ArmTimerEvent armTimerEvent = (ArmTimerEvent)theEObject;
        T result = caseArmTimerEvent(armTimerEvent);
        if (result == null) result = caseEvent(armTimerEvent);
        if (result == null) result = caseNamedElement(armTimerEvent);
        if (result == null) result = caseAbstractEvent(armTimerEvent);
        if (result == null) result = caseCapellaElement(armTimerEvent);
        if (result == null) result = caseAbstractType(armTimerEvent);
        if (result == null) result = caseAbstractNamedElement(armTimerEvent);
        if (result == null) result = caseTraceableElement(armTimerEvent);
        if (result == null) result = casePublishableElement(armTimerEvent);
        if (result == null) result = caseModelElement(armTimerEvent);
        if (result == null) result = caseExtensibleElement(armTimerEvent);
        if (result == null) result = caseElement(armTimerEvent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.CANCEL_TIMER_EVENT: {
        CancelTimerEvent cancelTimerEvent = (CancelTimerEvent)theEObject;
        T result = caseCancelTimerEvent(cancelTimerEvent);
        if (result == null) result = caseEvent(cancelTimerEvent);
        if (result == null) result = caseNamedElement(cancelTimerEvent);
        if (result == null) result = caseAbstractEvent(cancelTimerEvent);
        if (result == null) result = caseCapellaElement(cancelTimerEvent);
        if (result == null) result = caseAbstractType(cancelTimerEvent);
        if (result == null) result = caseAbstractNamedElement(cancelTimerEvent);
        if (result == null) result = caseTraceableElement(cancelTimerEvent);
        if (result == null) result = casePublishableElement(cancelTimerEvent);
        if (result == null) result = caseModelElement(cancelTimerEvent);
        if (result == null) result = caseExtensibleElement(cancelTimerEvent);
        if (result == null) result = caseElement(cancelTimerEvent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.CONSTRAINT_DURATION: {
        ConstraintDuration constraintDuration = (ConstraintDuration)theEObject;
        T result = caseConstraintDuration(constraintDuration);
        if (result == null) result = caseNamedElement(constraintDuration);
        if (result == null) result = caseAbstractNamedElement(constraintDuration);
        if (result == null) result = caseCapellaElement(constraintDuration);
        if (result == null) result = caseTraceableElement(constraintDuration);
        if (result == null) result = casePublishableElement(constraintDuration);
        if (result == null) result = caseModelElement(constraintDuration);
        if (result == null) result = caseExtensibleElement(constraintDuration);
        if (result == null) result = caseElement(constraintDuration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case InteractionPackage.SEQUENCE_MESSAGE_VALUATION: {
        SequenceMessageValuation sequenceMessageValuation = (SequenceMessageValuation)theEObject;
        T result = caseSequenceMessageValuation(sequenceMessageValuation);
        if (result == null) result = caseCapellaElement(sequenceMessageValuation);
        if (result == null) result = caseTraceableElement(sequenceMessageValuation);
        if (result == null) result = casePublishableElement(sequenceMessageValuation);
        if (result == null) result = caseModelElement(sequenceMessageValuation);
        if (result == null) result = caseExtensibleElement(sequenceMessageValuation);
        if (result == null) result = caseElement(sequenceMessageValuation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Sequence Message</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Sequence Message</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseSequenceMessage(SequenceMessage object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Scenario</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Scenario</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseScenario(Scenario object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Message End</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Message End</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseMessageEnd(MessageEnd object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Execution</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Execution</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseExecution(Execution object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Execution End</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Execution End</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseExecutionEnd(ExecutionEnd object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Creation Event</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Creation Event</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCreationEvent(CreationEvent object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Destruction Event</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Destruction Event</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseDestructionEvent(DestructionEvent object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Execution Event</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Execution Event</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseExecutionEvent(ExecutionEvent object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Instance Role</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Instance Role</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInstanceRole(InstanceRole object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract End</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract End</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractEnd(AbstractEnd object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Event</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Event</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseEvent(Event object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Event Receipt Operation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Event Receipt Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseEventReceiptOperation(EventReceiptOperation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Event Sent Operation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Event Sent Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseEventSentOperation(EventSentOperation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Merge Link</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Merge Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseMergeLink(MergeLink object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Refinement Link</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Refinement Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseRefinementLink(RefinementLink object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Capability Realization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Capability Realization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractCapabilityRealization(AbstractCapabilityRealization object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Capability</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Capability</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractCapability(AbstractCapability object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Capability Extend</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Capability Extend</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractCapabilityExtend(AbstractCapabilityExtend object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Capability Extension Point</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Capability Extension Point</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractCapabilityExtensionPoint(AbstractCapabilityExtensionPoint object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Capability Generalization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Capability Generalization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractCapabilityGeneralization(AbstractCapabilityGeneralization object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Capability Include</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Capability Include</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractCapabilityInclude(AbstractCapabilityInclude object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Fragment</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fragment</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInteractionFragment(InteractionFragment object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>State</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>State</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInteractionState(InteractionState object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Use</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Use</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInteractionUse(InteractionUse object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Combined Fragment</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Combined Fragment</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCombinedFragment(CombinedFragment object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Gate</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Gate</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseGate(Gate object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Operand</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operand</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInteractionOperand(InteractionOperand object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Time Lapse</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Time Lapse</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTimeLapse(TimeLapse object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Fragment</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Fragment</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractFragment(AbstractFragment object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Fragment End</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fragment End</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFragmentEnd(FragmentEnd object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Functional Chain Abstract Capability Involvement</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Functional Chain Abstract Capability Involvement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFunctionalChainAbstractCapabilityInvolvement(FunctionalChainAbstractCapabilityInvolvement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Function Abstract Capability Involvement</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Function Abstract Capability Involvement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractFunctionAbstractCapabilityInvolvement(AbstractFunctionAbstractCapabilityInvolvement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Scenario Realization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Scenario Realization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseScenarioRealization(ScenarioRealization object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>State Fragment</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>State Fragment</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseStateFragment(StateFragment object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Arm Timer Event</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Arm Timer Event</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseArmTimerEvent(ArmTimerEvent object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Cancel Timer Event</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Cancel Timer Event</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCancelTimerEvent(CancelTimerEvent object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Constraint Duration</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Constraint Duration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseConstraintDuration(ConstraintDuration object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Sequence Message Valuation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Sequence Message Valuation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseSequenceMessageValuation(SequenceMessageValuation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseElement(Element object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Extensible Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Extensible Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseExtensibleElement(ExtensibleElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseModelElement(ModelElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Named Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Named Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractNamedElement(AbstractNamedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Traceable Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Traceable Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTraceableElement(TraceableElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Publishable Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Publishable Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePublishableElement(PublishableElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Capella Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Capella Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCapellaElement(CapellaElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNamedElement(NamedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Namespace</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Namespace</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNamespace(Namespace object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Behavior</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Behavior</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractBehavior(AbstractBehavior object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Type</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractType(AbstractType object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Event</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Event</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractEvent(AbstractEvent object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Relationship</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Relationship</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractRelationship(AbstractRelationship object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Relationship</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Relationship</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseRelationship(Relationship object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Trace</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Trace</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractTrace(AbstractTrace object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Trace</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Trace</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTrace(Trace object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Allocation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Allocation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAllocation(Allocation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Structure</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Structure</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseStructure(Structure object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Involver Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Involver Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInvolverElement(InvolverElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Functional Chain Container</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Functional Chain Container</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractFunctionalChainContainer(AbstractFunctionalChainContainer object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Named Relationship</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Relationship</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNamedRelationship(NamedRelationship object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Involvement</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Involvement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInvolvement(Involvement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
	@Override
	public T defaultCase(EObject object) {
    return null;
  }

} //InteractionSwitch

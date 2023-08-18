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
package org.polarsys.capella.core.data.capellacommon.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.polarsys.capella.common.data.behavior.AbstractBehavior;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractRelationship;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacommon.*;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.AbstractStateRealization;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.ChangeEvent;
import org.polarsys.capella.core.data.capellacommon.ChoicePseudoState;
import org.polarsys.capella.core.data.capellacommon.DeepHistoryPseudoState;
import org.polarsys.capella.core.data.capellacommon.EntryPointPseudoState;
import org.polarsys.capella.core.data.capellacommon.ExitPointPseudoState;
import org.polarsys.capella.core.data.capellacommon.FinalState;
import org.polarsys.capella.core.data.capellacommon.ForkPseudoState;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacommon.InitialPseudoState;
import org.polarsys.capella.core.data.capellacommon.JoinPseudoState;
import org.polarsys.capella.core.data.capellacommon.JustificationLink;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.Pseudostate;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.ShallowHistoryPseudoState;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateEvent;
import org.polarsys.capella.core.data.capellacommon.StateEventRealization;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacommon.StateTransitionRealization;
import org.polarsys.capella.core.data.capellacommon.TerminatePseudoState;
import org.polarsys.capella.core.data.capellacommon.TimeEvent;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.capellacore.Allocation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellacore.Trace;
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
 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage
 * @generated
 */
public class CapellacommonSwitch<T> extends Switch<T> {
	/**
   * The cached model package
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static CapellacommonPackage modelPackage;

	/**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CapellacommonSwitch() {
    if (modelPackage == null) {
      modelPackage = CapellacommonPackage.eINSTANCE;
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
      case CapellacommonPackage.ABSTRACT_CAPABILITY_PKG: {
        AbstractCapabilityPkg abstractCapabilityPkg = (AbstractCapabilityPkg)theEObject;
        T result = caseAbstractCapabilityPkg(abstractCapabilityPkg);
        if (result == null) result = caseStructure(abstractCapabilityPkg);
        if (result == null) result = caseNamespace(abstractCapabilityPkg);
        if (result == null) result = caseNamedElement(abstractCapabilityPkg);
        if (result == null) result = caseAbstractNamedElement(abstractCapabilityPkg);
        if (result == null) result = caseCapellaElement(abstractCapabilityPkg);
        if (result == null) result = caseTraceableElement(abstractCapabilityPkg);
        if (result == null) result = casePublishableElement(abstractCapabilityPkg);
        if (result == null) result = caseModelElement(abstractCapabilityPkg);
        if (result == null) result = caseExtensibleElement(abstractCapabilityPkg);
        if (result == null) result = caseElement(abstractCapabilityPkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.GENERIC_TRACE: {
        GenericTrace genericTrace = (GenericTrace)theEObject;
        T result = caseGenericTrace(genericTrace);
        if (result == null) result = caseTrace(genericTrace);
        if (result == null) result = caseRelationship(genericTrace);
        if (result == null) result = caseAbstractTrace(genericTrace);
        if (result == null) result = caseAbstractRelationship(genericTrace);
        if (result == null) result = caseCapellaElement(genericTrace);
        if (result == null) result = caseTraceableElement(genericTrace);
        if (result == null) result = casePublishableElement(genericTrace);
        if (result == null) result = caseModelElement(genericTrace);
        if (result == null) result = caseExtensibleElement(genericTrace);
        if (result == null) result = caseElement(genericTrace);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.TRANSFO_LINK: {
        TransfoLink transfoLink = (TransfoLink)theEObject;
        T result = caseTransfoLink(transfoLink);
        if (result == null) result = caseGenericTrace(transfoLink);
        if (result == null) result = caseTrace(transfoLink);
        if (result == null) result = caseRelationship(transfoLink);
        if (result == null) result = caseAbstractTrace(transfoLink);
        if (result == null) result = caseAbstractRelationship(transfoLink);
        if (result == null) result = caseCapellaElement(transfoLink);
        if (result == null) result = caseTraceableElement(transfoLink);
        if (result == null) result = casePublishableElement(transfoLink);
        if (result == null) result = caseModelElement(transfoLink);
        if (result == null) result = caseExtensibleElement(transfoLink);
        if (result == null) result = caseElement(transfoLink);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.JUSTIFICATION_LINK: {
        JustificationLink justificationLink = (JustificationLink)theEObject;
        T result = caseJustificationLink(justificationLink);
        if (result == null) result = caseGenericTrace(justificationLink);
        if (result == null) result = caseTrace(justificationLink);
        if (result == null) result = caseRelationship(justificationLink);
        if (result == null) result = caseAbstractTrace(justificationLink);
        if (result == null) result = caseAbstractRelationship(justificationLink);
        if (result == null) result = caseCapellaElement(justificationLink);
        if (result == null) result = caseTraceableElement(justificationLink);
        if (result == null) result = casePublishableElement(justificationLink);
        if (result == null) result = caseModelElement(justificationLink);
        if (result == null) result = caseExtensibleElement(justificationLink);
        if (result == null) result = caseElement(justificationLink);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVEMENT: {
        CapabilityRealizationInvolvement capabilityRealizationInvolvement = (CapabilityRealizationInvolvement)theEObject;
        T result = caseCapabilityRealizationInvolvement(capabilityRealizationInvolvement);
        if (result == null) result = caseInvolvement(capabilityRealizationInvolvement);
        if (result == null) result = caseRelationship(capabilityRealizationInvolvement);
        if (result == null) result = caseAbstractRelationship(capabilityRealizationInvolvement);
        if (result == null) result = caseCapellaElement(capabilityRealizationInvolvement);
        if (result == null) result = caseTraceableElement(capabilityRealizationInvolvement);
        if (result == null) result = casePublishableElement(capabilityRealizationInvolvement);
        if (result == null) result = caseModelElement(capabilityRealizationInvolvement);
        if (result == null) result = caseExtensibleElement(capabilityRealizationInvolvement);
        if (result == null) result = caseElement(capabilityRealizationInvolvement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT: {
        CapabilityRealizationInvolvedElement capabilityRealizationInvolvedElement = (CapabilityRealizationInvolvedElement)theEObject;
        T result = caseCapabilityRealizationInvolvedElement(capabilityRealizationInvolvedElement);
        if (result == null) result = caseInvolvedElement(capabilityRealizationInvolvedElement);
        if (result == null) result = caseCapellaElement(capabilityRealizationInvolvedElement);
        if (result == null) result = caseTraceableElement(capabilityRealizationInvolvedElement);
        if (result == null) result = casePublishableElement(capabilityRealizationInvolvedElement);
        if (result == null) result = caseModelElement(capabilityRealizationInvolvedElement);
        if (result == null) result = caseExtensibleElement(capabilityRealizationInvolvedElement);
        if (result == null) result = caseElement(capabilityRealizationInvolvedElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.STATE_MACHINE: {
        StateMachine stateMachine = (StateMachine)theEObject;
        T result = caseStateMachine(stateMachine);
        if (result == null) result = caseCapellaElement(stateMachine);
        if (result == null) result = caseAbstractBehavior(stateMachine);
        if (result == null) result = caseTraceableElement(stateMachine);
        if (result == null) result = casePublishableElement(stateMachine);
        if (result == null) result = caseAbstractNamedElement(stateMachine);
        if (result == null) result = caseModelElement(stateMachine);
        if (result == null) result = caseExtensibleElement(stateMachine);
        if (result == null) result = caseElement(stateMachine);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.REGION: {
        Region region = (Region)theEObject;
        T result = caseRegion(region);
        if (result == null) result = caseNamedElement(region);
        if (result == null) result = caseAbstractNamedElement(region);
        if (result == null) result = caseCapellaElement(region);
        if (result == null) result = caseTraceableElement(region);
        if (result == null) result = casePublishableElement(region);
        if (result == null) result = caseModelElement(region);
        if (result == null) result = caseExtensibleElement(region);
        if (result == null) result = caseElement(region);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.STATE: {
        State state = (State)theEObject;
        T result = caseState(state);
        if (result == null) result = caseAbstractState(state);
        if (result == null) result = caseNamedElement(state);
        if (result == null) result = caseIState(state);
        if (result == null) result = caseAbstractNamedElement(state);
        if (result == null) result = caseCapellaElement(state);
        if (result == null) result = caseTraceableElement(state);
        if (result == null) result = casePublishableElement(state);
        if (result == null) result = caseModelElement(state);
        if (result == null) result = caseExtensibleElement(state);
        if (result == null) result = caseElement(state);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.MODE: {
        Mode mode = (Mode)theEObject;
        T result = caseMode(mode);
        if (result == null) result = caseState(mode);
        if (result == null) result = caseAbstractState(mode);
        if (result == null) result = caseNamedElement(mode);
        if (result == null) result = caseIState(mode);
        if (result == null) result = caseAbstractNamedElement(mode);
        if (result == null) result = caseCapellaElement(mode);
        if (result == null) result = caseTraceableElement(mode);
        if (result == null) result = casePublishableElement(mode);
        if (result == null) result = caseModelElement(mode);
        if (result == null) result = caseExtensibleElement(mode);
        if (result == null) result = caseElement(mode);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.FINAL_STATE: {
        FinalState finalState = (FinalState)theEObject;
        T result = caseFinalState(finalState);
        if (result == null) result = caseState(finalState);
        if (result == null) result = caseAbstractState(finalState);
        if (result == null) result = caseNamedElement(finalState);
        if (result == null) result = caseIState(finalState);
        if (result == null) result = caseAbstractNamedElement(finalState);
        if (result == null) result = caseCapellaElement(finalState);
        if (result == null) result = caseTraceableElement(finalState);
        if (result == null) result = casePublishableElement(finalState);
        if (result == null) result = caseModelElement(finalState);
        if (result == null) result = caseExtensibleElement(finalState);
        if (result == null) result = caseElement(finalState);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.ABSTRACT_STATE: {
        AbstractState abstractState = (AbstractState)theEObject;
        T result = caseAbstractState(abstractState);
        if (result == null) result = caseNamedElement(abstractState);
        if (result == null) result = caseIState(abstractState);
        if (result == null) result = caseAbstractNamedElement(abstractState);
        if (result == null) result = caseCapellaElement(abstractState);
        if (result == null) result = caseTraceableElement(abstractState);
        if (result == null) result = casePublishableElement(abstractState);
        if (result == null) result = caseModelElement(abstractState);
        if (result == null) result = caseExtensibleElement(abstractState);
        if (result == null) result = caseElement(abstractState);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.STATE_TRANSITION: {
        StateTransition stateTransition = (StateTransition)theEObject;
        T result = caseStateTransition(stateTransition);
        if (result == null) result = caseNamedElement(stateTransition);
        if (result == null) result = caseRelationship(stateTransition);
        if (result == null) result = caseAbstractNamedElement(stateTransition);
        if (result == null) result = caseCapellaElement(stateTransition);
        if (result == null) result = caseAbstractRelationship(stateTransition);
        if (result == null) result = caseTraceableElement(stateTransition);
        if (result == null) result = casePublishableElement(stateTransition);
        if (result == null) result = caseModelElement(stateTransition);
        if (result == null) result = caseExtensibleElement(stateTransition);
        if (result == null) result = caseElement(stateTransition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.PSEUDOSTATE: {
        Pseudostate pseudostate = (Pseudostate)theEObject;
        T result = casePseudostate(pseudostate);
        if (result == null) result = caseAbstractState(pseudostate);
        if (result == null) result = caseNamedElement(pseudostate);
        if (result == null) result = caseIState(pseudostate);
        if (result == null) result = caseAbstractNamedElement(pseudostate);
        if (result == null) result = caseCapellaElement(pseudostate);
        if (result == null) result = caseTraceableElement(pseudostate);
        if (result == null) result = casePublishableElement(pseudostate);
        if (result == null) result = caseModelElement(pseudostate);
        if (result == null) result = caseExtensibleElement(pseudostate);
        if (result == null) result = caseElement(pseudostate);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.INITIAL_PSEUDO_STATE: {
        InitialPseudoState initialPseudoState = (InitialPseudoState)theEObject;
        T result = caseInitialPseudoState(initialPseudoState);
        if (result == null) result = casePseudostate(initialPseudoState);
        if (result == null) result = caseAbstractState(initialPseudoState);
        if (result == null) result = caseNamedElement(initialPseudoState);
        if (result == null) result = caseIState(initialPseudoState);
        if (result == null) result = caseAbstractNamedElement(initialPseudoState);
        if (result == null) result = caseCapellaElement(initialPseudoState);
        if (result == null) result = caseTraceableElement(initialPseudoState);
        if (result == null) result = casePublishableElement(initialPseudoState);
        if (result == null) result = caseModelElement(initialPseudoState);
        if (result == null) result = caseExtensibleElement(initialPseudoState);
        if (result == null) result = caseElement(initialPseudoState);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.JOIN_PSEUDO_STATE: {
        JoinPseudoState joinPseudoState = (JoinPseudoState)theEObject;
        T result = caseJoinPseudoState(joinPseudoState);
        if (result == null) result = casePseudostate(joinPseudoState);
        if (result == null) result = caseAbstractState(joinPseudoState);
        if (result == null) result = caseNamedElement(joinPseudoState);
        if (result == null) result = caseIState(joinPseudoState);
        if (result == null) result = caseAbstractNamedElement(joinPseudoState);
        if (result == null) result = caseCapellaElement(joinPseudoState);
        if (result == null) result = caseTraceableElement(joinPseudoState);
        if (result == null) result = casePublishableElement(joinPseudoState);
        if (result == null) result = caseModelElement(joinPseudoState);
        if (result == null) result = caseExtensibleElement(joinPseudoState);
        if (result == null) result = caseElement(joinPseudoState);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.FORK_PSEUDO_STATE: {
        ForkPseudoState forkPseudoState = (ForkPseudoState)theEObject;
        T result = caseForkPseudoState(forkPseudoState);
        if (result == null) result = casePseudostate(forkPseudoState);
        if (result == null) result = caseAbstractState(forkPseudoState);
        if (result == null) result = caseNamedElement(forkPseudoState);
        if (result == null) result = caseIState(forkPseudoState);
        if (result == null) result = caseAbstractNamedElement(forkPseudoState);
        if (result == null) result = caseCapellaElement(forkPseudoState);
        if (result == null) result = caseTraceableElement(forkPseudoState);
        if (result == null) result = casePublishableElement(forkPseudoState);
        if (result == null) result = caseModelElement(forkPseudoState);
        if (result == null) result = caseExtensibleElement(forkPseudoState);
        if (result == null) result = caseElement(forkPseudoState);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.CHOICE_PSEUDO_STATE: {
        ChoicePseudoState choicePseudoState = (ChoicePseudoState)theEObject;
        T result = caseChoicePseudoState(choicePseudoState);
        if (result == null) result = casePseudostate(choicePseudoState);
        if (result == null) result = caseAbstractState(choicePseudoState);
        if (result == null) result = caseNamedElement(choicePseudoState);
        if (result == null) result = caseIState(choicePseudoState);
        if (result == null) result = caseAbstractNamedElement(choicePseudoState);
        if (result == null) result = caseCapellaElement(choicePseudoState);
        if (result == null) result = caseTraceableElement(choicePseudoState);
        if (result == null) result = casePublishableElement(choicePseudoState);
        if (result == null) result = caseModelElement(choicePseudoState);
        if (result == null) result = caseExtensibleElement(choicePseudoState);
        if (result == null) result = caseElement(choicePseudoState);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.TERMINATE_PSEUDO_STATE: {
        TerminatePseudoState terminatePseudoState = (TerminatePseudoState)theEObject;
        T result = caseTerminatePseudoState(terminatePseudoState);
        if (result == null) result = casePseudostate(terminatePseudoState);
        if (result == null) result = caseAbstractState(terminatePseudoState);
        if (result == null) result = caseNamedElement(terminatePseudoState);
        if (result == null) result = caseIState(terminatePseudoState);
        if (result == null) result = caseAbstractNamedElement(terminatePseudoState);
        if (result == null) result = caseCapellaElement(terminatePseudoState);
        if (result == null) result = caseTraceableElement(terminatePseudoState);
        if (result == null) result = casePublishableElement(terminatePseudoState);
        if (result == null) result = caseModelElement(terminatePseudoState);
        if (result == null) result = caseExtensibleElement(terminatePseudoState);
        if (result == null) result = caseElement(terminatePseudoState);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.ABSTRACT_STATE_REALIZATION: {
        AbstractStateRealization abstractStateRealization = (AbstractStateRealization)theEObject;
        T result = caseAbstractStateRealization(abstractStateRealization);
        if (result == null) result = caseAllocation(abstractStateRealization);
        if (result == null) result = caseRelationship(abstractStateRealization);
        if (result == null) result = caseAbstractTrace(abstractStateRealization);
        if (result == null) result = caseAbstractRelationship(abstractStateRealization);
        if (result == null) result = caseCapellaElement(abstractStateRealization);
        if (result == null) result = caseTraceableElement(abstractStateRealization);
        if (result == null) result = casePublishableElement(abstractStateRealization);
        if (result == null) result = caseModelElement(abstractStateRealization);
        if (result == null) result = caseExtensibleElement(abstractStateRealization);
        if (result == null) result = caseElement(abstractStateRealization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.STATE_TRANSITION_REALIZATION: {
        StateTransitionRealization stateTransitionRealization = (StateTransitionRealization)theEObject;
        T result = caseStateTransitionRealization(stateTransitionRealization);
        if (result == null) result = caseAllocation(stateTransitionRealization);
        if (result == null) result = caseRelationship(stateTransitionRealization);
        if (result == null) result = caseAbstractTrace(stateTransitionRealization);
        if (result == null) result = caseAbstractRelationship(stateTransitionRealization);
        if (result == null) result = caseCapellaElement(stateTransitionRealization);
        if (result == null) result = caseTraceableElement(stateTransitionRealization);
        if (result == null) result = casePublishableElement(stateTransitionRealization);
        if (result == null) result = caseModelElement(stateTransitionRealization);
        if (result == null) result = caseExtensibleElement(stateTransitionRealization);
        if (result == null) result = caseElement(stateTransitionRealization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.SHALLOW_HISTORY_PSEUDO_STATE: {
        ShallowHistoryPseudoState shallowHistoryPseudoState = (ShallowHistoryPseudoState)theEObject;
        T result = caseShallowHistoryPseudoState(shallowHistoryPseudoState);
        if (result == null) result = casePseudostate(shallowHistoryPseudoState);
        if (result == null) result = caseAbstractState(shallowHistoryPseudoState);
        if (result == null) result = caseNamedElement(shallowHistoryPseudoState);
        if (result == null) result = caseIState(shallowHistoryPseudoState);
        if (result == null) result = caseAbstractNamedElement(shallowHistoryPseudoState);
        if (result == null) result = caseCapellaElement(shallowHistoryPseudoState);
        if (result == null) result = caseTraceableElement(shallowHistoryPseudoState);
        if (result == null) result = casePublishableElement(shallowHistoryPseudoState);
        if (result == null) result = caseModelElement(shallowHistoryPseudoState);
        if (result == null) result = caseExtensibleElement(shallowHistoryPseudoState);
        if (result == null) result = caseElement(shallowHistoryPseudoState);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.DEEP_HISTORY_PSEUDO_STATE: {
        DeepHistoryPseudoState deepHistoryPseudoState = (DeepHistoryPseudoState)theEObject;
        T result = caseDeepHistoryPseudoState(deepHistoryPseudoState);
        if (result == null) result = casePseudostate(deepHistoryPseudoState);
        if (result == null) result = caseAbstractState(deepHistoryPseudoState);
        if (result == null) result = caseNamedElement(deepHistoryPseudoState);
        if (result == null) result = caseIState(deepHistoryPseudoState);
        if (result == null) result = caseAbstractNamedElement(deepHistoryPseudoState);
        if (result == null) result = caseCapellaElement(deepHistoryPseudoState);
        if (result == null) result = caseTraceableElement(deepHistoryPseudoState);
        if (result == null) result = casePublishableElement(deepHistoryPseudoState);
        if (result == null) result = caseModelElement(deepHistoryPseudoState);
        if (result == null) result = caseExtensibleElement(deepHistoryPseudoState);
        if (result == null) result = caseElement(deepHistoryPseudoState);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.ENTRY_POINT_PSEUDO_STATE: {
        EntryPointPseudoState entryPointPseudoState = (EntryPointPseudoState)theEObject;
        T result = caseEntryPointPseudoState(entryPointPseudoState);
        if (result == null) result = casePseudostate(entryPointPseudoState);
        if (result == null) result = caseAbstractState(entryPointPseudoState);
        if (result == null) result = caseNamedElement(entryPointPseudoState);
        if (result == null) result = caseIState(entryPointPseudoState);
        if (result == null) result = caseAbstractNamedElement(entryPointPseudoState);
        if (result == null) result = caseCapellaElement(entryPointPseudoState);
        if (result == null) result = caseTraceableElement(entryPointPseudoState);
        if (result == null) result = casePublishableElement(entryPointPseudoState);
        if (result == null) result = caseModelElement(entryPointPseudoState);
        if (result == null) result = caseExtensibleElement(entryPointPseudoState);
        if (result == null) result = caseElement(entryPointPseudoState);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.EXIT_POINT_PSEUDO_STATE: {
        ExitPointPseudoState exitPointPseudoState = (ExitPointPseudoState)theEObject;
        T result = caseExitPointPseudoState(exitPointPseudoState);
        if (result == null) result = casePseudostate(exitPointPseudoState);
        if (result == null) result = caseAbstractState(exitPointPseudoState);
        if (result == null) result = caseNamedElement(exitPointPseudoState);
        if (result == null) result = caseIState(exitPointPseudoState);
        if (result == null) result = caseAbstractNamedElement(exitPointPseudoState);
        if (result == null) result = caseCapellaElement(exitPointPseudoState);
        if (result == null) result = caseTraceableElement(exitPointPseudoState);
        if (result == null) result = casePublishableElement(exitPointPseudoState);
        if (result == null) result = caseModelElement(exitPointPseudoState);
        if (result == null) result = caseExtensibleElement(exitPointPseudoState);
        if (result == null) result = caseElement(exitPointPseudoState);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.STATE_EVENT_REALIZATION: {
        StateEventRealization stateEventRealization = (StateEventRealization)theEObject;
        T result = caseStateEventRealization(stateEventRealization);
        if (result == null) result = caseAllocation(stateEventRealization);
        if (result == null) result = caseRelationship(stateEventRealization);
        if (result == null) result = caseAbstractTrace(stateEventRealization);
        if (result == null) result = caseAbstractRelationship(stateEventRealization);
        if (result == null) result = caseCapellaElement(stateEventRealization);
        if (result == null) result = caseTraceableElement(stateEventRealization);
        if (result == null) result = casePublishableElement(stateEventRealization);
        if (result == null) result = caseModelElement(stateEventRealization);
        if (result == null) result = caseExtensibleElement(stateEventRealization);
        if (result == null) result = caseElement(stateEventRealization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.STATE_EVENT: {
        StateEvent stateEvent = (StateEvent)theEObject;
        T result = caseStateEvent(stateEvent);
        if (result == null) result = caseNamedElement(stateEvent);
        if (result == null) result = caseAbstractEvent(stateEvent);
        if (result == null) result = caseCapellaElement(stateEvent);
        if (result == null) result = caseAbstractType(stateEvent);
        if (result == null) result = caseAbstractNamedElement(stateEvent);
        if (result == null) result = caseTraceableElement(stateEvent);
        if (result == null) result = casePublishableElement(stateEvent);
        if (result == null) result = caseModelElement(stateEvent);
        if (result == null) result = caseExtensibleElement(stateEvent);
        if (result == null) result = caseElement(stateEvent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.CHANGE_EVENT: {
        ChangeEvent changeEvent = (ChangeEvent)theEObject;
        T result = caseChangeEvent(changeEvent);
        if (result == null) result = caseStateEvent(changeEvent);
        if (result == null) result = caseNamedElement(changeEvent);
        if (result == null) result = caseAbstractEvent(changeEvent);
        if (result == null) result = caseCapellaElement(changeEvent);
        if (result == null) result = caseAbstractType(changeEvent);
        if (result == null) result = caseAbstractNamedElement(changeEvent);
        if (result == null) result = caseTraceableElement(changeEvent);
        if (result == null) result = casePublishableElement(changeEvent);
        if (result == null) result = caseModelElement(changeEvent);
        if (result == null) result = caseExtensibleElement(changeEvent);
        if (result == null) result = caseElement(changeEvent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CapellacommonPackage.TIME_EVENT: {
        TimeEvent timeEvent = (TimeEvent)theEObject;
        T result = caseTimeEvent(timeEvent);
        if (result == null) result = caseStateEvent(timeEvent);
        if (result == null) result = caseNamedElement(timeEvent);
        if (result == null) result = caseAbstractEvent(timeEvent);
        if (result == null) result = caseCapellaElement(timeEvent);
        if (result == null) result = caseAbstractType(timeEvent);
        if (result == null) result = caseAbstractNamedElement(timeEvent);
        if (result == null) result = caseTraceableElement(timeEvent);
        if (result == null) result = casePublishableElement(timeEvent);
        if (result == null) result = caseModelElement(timeEvent);
        if (result == null) result = caseExtensibleElement(timeEvent);
        if (result == null) result = caseElement(timeEvent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Capability Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Capability Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractCapabilityPkg(AbstractCapabilityPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Generic Trace</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Generic Trace</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseGenericTrace(GenericTrace object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Transfo Link</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Transfo Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTransfoLink(TransfoLink object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Justification Link</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Justification Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseJustificationLink(JustificationLink object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Capability Realization Involvement</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Capability Realization Involvement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCapabilityRealizationInvolvement(CapabilityRealizationInvolvement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Capability Realization Involved Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Capability Realization Involved Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCapabilityRealizationInvolvedElement(CapabilityRealizationInvolvedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>State Machine</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>State Machine</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseStateMachine(StateMachine object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Region</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Region</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseRegion(Region object) {
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
	public T caseState(State object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Mode</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Mode</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseMode(Mode object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Final State</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Final State</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFinalState(FinalState object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract State</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract State</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractState(AbstractState object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>State Transition</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>State Transition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseStateTransition(StateTransition object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Pseudostate</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Pseudostate</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePseudostate(Pseudostate object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Initial Pseudo State</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Initial Pseudo State</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInitialPseudoState(InitialPseudoState object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Join Pseudo State</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Join Pseudo State</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseJoinPseudoState(JoinPseudoState object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Fork Pseudo State</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fork Pseudo State</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseForkPseudoState(ForkPseudoState object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Choice Pseudo State</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Choice Pseudo State</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseChoicePseudoState(ChoicePseudoState object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Terminate Pseudo State</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Terminate Pseudo State</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTerminatePseudoState(TerminatePseudoState object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract State Realization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract State Realization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractStateRealization(AbstractStateRealization object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>State Transition Realization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>State Transition Realization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseStateTransitionRealization(StateTransitionRealization object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Shallow History Pseudo State</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Shallow History Pseudo State</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseShallowHistoryPseudoState(ShallowHistoryPseudoState object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Deep History Pseudo State</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Deep History Pseudo State</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseDeepHistoryPseudoState(DeepHistoryPseudoState object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Entry Point Pseudo State</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Entry Point Pseudo State</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseEntryPointPseudoState(EntryPointPseudoState object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Exit Point Pseudo State</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Exit Point Pseudo State</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseExitPointPseudoState(ExitPointPseudoState object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>State Event Realization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>State Event Realization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseStateEventRealization(StateEventRealization object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>State Event</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>State Event</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseStateEvent(StateEvent object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Change Event</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Change Event</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseChangeEvent(ChangeEvent object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Time Event</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Time Event</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTimeEvent(TimeEvent object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Involved Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Involved Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInvolvedElement(InvolvedElement object) {
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
   * Returns the result of interpreting the object as an instance of '<em>IState</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IState</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseIState(IState object) {
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

} //CapellacommonSwitch

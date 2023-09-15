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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage
 * @generated
 */
public class InteractionAdapterFactory extends AdapterFactoryImpl {
	/**
   * The cached model package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static InteractionPackage modelPackage;

	/**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public InteractionAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = InteractionPackage.eINSTANCE;
    }
  }

	/**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
	@Override
	public boolean isFactoryForType(Object object) {
    if (object == modelPackage) {
      return true;
    }
    if (object instanceof EObject) {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

	/**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected InteractionSwitch<Adapter> modelSwitch =
		new InteractionSwitch<Adapter>() {
      @Override
      public Adapter caseSequenceMessage(SequenceMessage object) {
        return createSequenceMessageAdapter();
      }
      @Override
      public Adapter caseScenario(Scenario object) {
        return createScenarioAdapter();
      }
      @Override
      public Adapter caseMessageEnd(MessageEnd object) {
        return createMessageEndAdapter();
      }
      @Override
      public Adapter caseExecution(Execution object) {
        return createExecutionAdapter();
      }
      @Override
      public Adapter caseExecutionEnd(ExecutionEnd object) {
        return createExecutionEndAdapter();
      }
      @Override
      public Adapter caseCreationEvent(CreationEvent object) {
        return createCreationEventAdapter();
      }
      @Override
      public Adapter caseDestructionEvent(DestructionEvent object) {
        return createDestructionEventAdapter();
      }
      @Override
      public Adapter caseExecutionEvent(ExecutionEvent object) {
        return createExecutionEventAdapter();
      }
      @Override
      public Adapter caseInstanceRole(InstanceRole object) {
        return createInstanceRoleAdapter();
      }
      @Override
      public Adapter caseAbstractEnd(AbstractEnd object) {
        return createAbstractEndAdapter();
      }
      @Override
      public Adapter caseEvent(Event object) {
        return createEventAdapter();
      }
      @Override
      public Adapter caseEventReceiptOperation(EventReceiptOperation object) {
        return createEventReceiptOperationAdapter();
      }
      @Override
      public Adapter caseEventSentOperation(EventSentOperation object) {
        return createEventSentOperationAdapter();
      }
      @Override
      public Adapter caseMergeLink(MergeLink object) {
        return createMergeLinkAdapter();
      }
      @Override
      public Adapter caseRefinementLink(RefinementLink object) {
        return createRefinementLinkAdapter();
      }
      @Override
      public Adapter caseAbstractCapabilityRealization(AbstractCapabilityRealization object) {
        return createAbstractCapabilityRealizationAdapter();
      }
      @Override
      public Adapter caseAbstractCapability(AbstractCapability object) {
        return createAbstractCapabilityAdapter();
      }
      @Override
      public Adapter caseAbstractCapabilityExtend(AbstractCapabilityExtend object) {
        return createAbstractCapabilityExtendAdapter();
      }
      @Override
      public Adapter caseAbstractCapabilityExtensionPoint(AbstractCapabilityExtensionPoint object) {
        return createAbstractCapabilityExtensionPointAdapter();
      }
      @Override
      public Adapter caseAbstractCapabilityGeneralization(AbstractCapabilityGeneralization object) {
        return createAbstractCapabilityGeneralizationAdapter();
      }
      @Override
      public Adapter caseAbstractCapabilityInclude(AbstractCapabilityInclude object) {
        return createAbstractCapabilityIncludeAdapter();
      }
      @Override
      public Adapter caseInteractionFragment(InteractionFragment object) {
        return createInteractionFragmentAdapter();
      }
      @Override
      public Adapter caseInteractionState(InteractionState object) {
        return createInteractionStateAdapter();
      }
      @Override
      public Adapter caseInteractionUse(InteractionUse object) {
        return createInteractionUseAdapter();
      }
      @Override
      public Adapter caseCombinedFragment(CombinedFragment object) {
        return createCombinedFragmentAdapter();
      }
      @Override
      public Adapter caseGate(Gate object) {
        return createGateAdapter();
      }
      @Override
      public Adapter caseInteractionOperand(InteractionOperand object) {
        return createInteractionOperandAdapter();
      }
      @Override
      public Adapter caseTimeLapse(TimeLapse object) {
        return createTimeLapseAdapter();
      }
      @Override
      public Adapter caseAbstractFragment(AbstractFragment object) {
        return createAbstractFragmentAdapter();
      }
      @Override
      public Adapter caseFragmentEnd(FragmentEnd object) {
        return createFragmentEndAdapter();
      }
      @Override
      public Adapter caseFunctionalChainAbstractCapabilityInvolvement(FunctionalChainAbstractCapabilityInvolvement object) {
        return createFunctionalChainAbstractCapabilityInvolvementAdapter();
      }
      @Override
      public Adapter caseAbstractFunctionAbstractCapabilityInvolvement(AbstractFunctionAbstractCapabilityInvolvement object) {
        return createAbstractFunctionAbstractCapabilityInvolvementAdapter();
      }
      @Override
      public Adapter caseScenarioRealization(ScenarioRealization object) {
        return createScenarioRealizationAdapter();
      }
      @Override
      public Adapter caseStateFragment(StateFragment object) {
        return createStateFragmentAdapter();
      }
      @Override
      public Adapter caseArmTimerEvent(ArmTimerEvent object) {
        return createArmTimerEventAdapter();
      }
      @Override
      public Adapter caseCancelTimerEvent(CancelTimerEvent object) {
        return createCancelTimerEventAdapter();
      }
      @Override
      public Adapter caseConstraintDuration(ConstraintDuration object) {
        return createConstraintDurationAdapter();
      }
      @Override
      public Adapter caseSequenceMessageValuation(SequenceMessageValuation object) {
        return createSequenceMessageValuationAdapter();
      }
      @Override
      public Adapter caseElement(Element object) {
        return createElementAdapter();
      }
      @Override
      public Adapter caseExtensibleElement(ExtensibleElement object) {
        return createExtensibleElementAdapter();
      }
      @Override
      public Adapter caseModelElement(ModelElement object) {
        return createModelElementAdapter();
      }
      @Override
      public Adapter caseAbstractNamedElement(AbstractNamedElement object) {
        return createAbstractNamedElementAdapter();
      }
      @Override
      public Adapter caseTraceableElement(TraceableElement object) {
        return createTraceableElementAdapter();
      }
      @Override
      public Adapter casePublishableElement(PublishableElement object) {
        return createPublishableElementAdapter();
      }
      @Override
      public Adapter caseCapellaElement(CapellaElement object) {
        return createCapellaElementAdapter();
      }
      @Override
      public Adapter caseNamedElement(NamedElement object) {
        return createNamedElementAdapter();
      }
      @Override
      public Adapter caseNamespace(Namespace object) {
        return createNamespaceAdapter();
      }
      @Override
      public Adapter caseAbstractBehavior(AbstractBehavior object) {
        return createAbstractBehaviorAdapter();
      }
      @Override
      public Adapter caseAbstractType(AbstractType object) {
        return createAbstractTypeAdapter();
      }
      @Override
      public Adapter caseAbstractEvent(AbstractEvent object) {
        return createAbstractEventAdapter();
      }
      @Override
      public Adapter caseAbstractRelationship(AbstractRelationship object) {
        return createAbstractRelationshipAdapter();
      }
      @Override
      public Adapter caseRelationship(Relationship object) {
        return createRelationshipAdapter();
      }
      @Override
      public Adapter caseAbstractTrace(AbstractTrace object) {
        return createAbstractTraceAdapter();
      }
      @Override
      public Adapter caseTrace(Trace object) {
        return createTraceAdapter();
      }
      @Override
      public Adapter caseAllocation(Allocation object) {
        return createAllocationAdapter();
      }
      @Override
      public Adapter caseStructure(Structure object) {
        return createStructureAdapter();
      }
      @Override
      public Adapter caseInvolverElement(InvolverElement object) {
        return createInvolverElementAdapter();
      }
      @Override
      public Adapter caseAbstractFunctionalChainContainer(AbstractFunctionalChainContainer object) {
        return createAbstractFunctionalChainContainerAdapter();
      }
      @Override
      public Adapter caseNamedRelationship(NamedRelationship object) {
        return createNamedRelationshipAdapter();
      }
      @Override
      public Adapter caseInvolvement(Involvement object) {
        return createInvolvementAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object) {
        return createEObjectAdapter();
      }
    };

	/**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
	@Override
	public Adapter createAdapter(Notifier target) {
    return modelSwitch.doSwitch((EObject)target);
  }


	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.SequenceMessage <em>Sequence Message</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.SequenceMessage
   * @generated
   */
	public Adapter createSequenceMessageAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.Scenario <em>Scenario</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.Scenario
   * @generated
   */
	public Adapter createScenarioAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.MessageEnd <em>Message End</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.MessageEnd
   * @generated
   */
	public Adapter createMessageEndAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.Execution <em>Execution</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.Execution
   * @generated
   */
	public Adapter createExecutionAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.ExecutionEnd <em>Execution End</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.ExecutionEnd
   * @generated
   */
	public Adapter createExecutionEndAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.CreationEvent <em>Creation Event</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.CreationEvent
   * @generated
   */
	public Adapter createCreationEventAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.DestructionEvent <em>Destruction Event</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.DestructionEvent
   * @generated
   */
	public Adapter createDestructionEventAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.ExecutionEvent <em>Execution Event</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.ExecutionEvent
   * @generated
   */
	public Adapter createExecutionEventAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.InstanceRole <em>Instance Role</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.InstanceRole
   * @generated
   */
	public Adapter createInstanceRoleAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.AbstractEnd <em>Abstract End</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.AbstractEnd
   * @generated
   */
	public Adapter createAbstractEndAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.Event <em>Event</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.Event
   * @generated
   */
	public Adapter createEventAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.EventReceiptOperation <em>Event Receipt Operation</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.EventReceiptOperation
   * @generated
   */
	public Adapter createEventReceiptOperationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.EventSentOperation <em>Event Sent Operation</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.EventSentOperation
   * @generated
   */
	public Adapter createEventSentOperationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.MergeLink <em>Merge Link</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.MergeLink
   * @generated
   */
	public Adapter createMergeLinkAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.RefinementLink <em>Refinement Link</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.RefinementLink
   * @generated
   */
	public Adapter createRefinementLinkAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization <em>Abstract Capability Realization</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization
   * @generated
   */
	public Adapter createAbstractCapabilityRealizationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.AbstractCapability <em>Abstract Capability</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability
   * @generated
   */
	public Adapter createAbstractCapabilityAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend <em>Abstract Capability Extend</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend
   * @generated
   */
	public Adapter createAbstractCapabilityExtendAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint <em>Abstract Capability Extension Point</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint
   * @generated
   */
	public Adapter createAbstractCapabilityExtensionPointAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization <em>Abstract Capability Generalization</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization
   * @generated
   */
	public Adapter createAbstractCapabilityGeneralizationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude <em>Abstract Capability Include</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude
   * @generated
   */
	public Adapter createAbstractCapabilityIncludeAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.InteractionFragment <em>Fragment</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.InteractionFragment
   * @generated
   */
	public Adapter createInteractionFragmentAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.InteractionState <em>State</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.InteractionState
   * @generated
   */
	public Adapter createInteractionStateAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.InteractionUse <em>Use</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.InteractionUse
   * @generated
   */
	public Adapter createInteractionUseAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.CombinedFragment <em>Combined Fragment</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.CombinedFragment
   * @generated
   */
	public Adapter createCombinedFragmentAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.Gate <em>Gate</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.Gate
   * @generated
   */
	public Adapter createGateAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.InteractionOperand <em>Operand</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.InteractionOperand
   * @generated
   */
	public Adapter createInteractionOperandAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.TimeLapse <em>Time Lapse</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.TimeLapse
   * @generated
   */
	public Adapter createTimeLapseAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.AbstractFragment <em>Abstract Fragment</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.AbstractFragment
   * @generated
   */
	public Adapter createAbstractFragmentAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.FragmentEnd <em>Fragment End</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.FragmentEnd
   * @generated
   */
	public Adapter createFragmentEndAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement <em>Functional Chain Abstract Capability Involvement</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement
   * @generated
   */
	public Adapter createFunctionalChainAbstractCapabilityInvolvementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement <em>Abstract Function Abstract Capability Involvement</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement
   * @generated
   */
	public Adapter createAbstractFunctionAbstractCapabilityInvolvementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.ScenarioRealization <em>Scenario Realization</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.ScenarioRealization
   * @generated
   */
	public Adapter createScenarioRealizationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.StateFragment <em>State Fragment</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.StateFragment
   * @generated
   */
	public Adapter createStateFragmentAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.ArmTimerEvent <em>Arm Timer Event</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.ArmTimerEvent
   * @generated
   */
	public Adapter createArmTimerEventAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.CancelTimerEvent <em>Cancel Timer Event</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.CancelTimerEvent
   * @generated
   */
	public Adapter createCancelTimerEventAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.ConstraintDuration <em>Constraint Duration</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.ConstraintDuration
   * @generated
   */
	public Adapter createConstraintDurationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.interaction.SequenceMessageValuation <em>Sequence Message Valuation</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.interaction.SequenceMessageValuation
   * @generated
   */
	public Adapter createSequenceMessageValuationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.kitalpha.emde.model.Element <em>Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.kitalpha.emde.model.Element
   * @generated
   */
	public Adapter createElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.kitalpha.emde.model.ExtensibleElement <em>Extensible Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.kitalpha.emde.model.ExtensibleElement
   * @generated
   */
	public Adapter createExtensibleElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.ModelElement <em>Model Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.ModelElement
   * @generated
   */
	public Adapter createModelElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.AbstractNamedElement <em>Abstract Named Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractNamedElement
   * @generated
   */
	public Adapter createAbstractNamedElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.TraceableElement <em>Traceable Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.TraceableElement
   * @generated
   */
	public Adapter createTraceableElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.PublishableElement <em>Publishable Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.PublishableElement
   * @generated
   */
	public Adapter createPublishableElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.CapellaElement <em>Capella Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.CapellaElement
   * @generated
   */
	public Adapter createCapellaElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.NamedElement <em>Named Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.NamedElement
   * @generated
   */
	public Adapter createNamedElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.Namespace <em>Namespace</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.Namespace
   * @generated
   */
	public Adapter createNamespaceAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.behavior.AbstractBehavior <em>Abstract Behavior</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.behavior.AbstractBehavior
   * @generated
   */
	public Adapter createAbstractBehaviorAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.AbstractType <em>Abstract Type</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractType
   * @generated
   */
	public Adapter createAbstractTypeAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.behavior.AbstractEvent <em>Abstract Event</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.behavior.AbstractEvent
   * @generated
   */
	public Adapter createAbstractEventAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.AbstractRelationship <em>Abstract Relationship</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractRelationship
   * @generated
   */
	public Adapter createAbstractRelationshipAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.Relationship <em>Relationship</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.Relationship
   * @generated
   */
	public Adapter createRelationshipAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.AbstractTrace <em>Abstract Trace</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractTrace
   * @generated
   */
	public Adapter createAbstractTraceAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.Trace <em>Trace</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.Trace
   * @generated
   */
	public Adapter createTraceAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.Allocation <em>Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.Allocation
   * @generated
   */
	public Adapter createAllocationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.Structure <em>Structure</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.Structure
   * @generated
   */
	public Adapter createStructureAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.InvolverElement <em>Involver Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.InvolverElement
   * @generated
   */
	public Adapter createInvolverElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer <em>Abstract Functional Chain Container</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer
   * @generated
   */
	public Adapter createAbstractFunctionalChainContainerAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.NamedRelationship <em>Named Relationship</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.NamedRelationship
   * @generated
   */
	public Adapter createNamedRelationshipAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.Involvement <em>Involvement</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.Involvement
   * @generated
   */
	public Adapter createInvolvementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
	public Adapter createEObjectAdapter() {
    return null;
  }

} //InteractionAdapterFactory

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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage
 * @generated
 */
public class CapellacommonAdapterFactory extends AdapterFactoryImpl {
	/**
   * The cached model package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static CapellacommonPackage modelPackage;

	/**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CapellacommonAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = CapellacommonPackage.eINSTANCE;
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
	protected CapellacommonSwitch<Adapter> modelSwitch =
		new CapellacommonSwitch<Adapter>() {
      @Override
      public Adapter caseAbstractCapabilityPkg(AbstractCapabilityPkg object) {
        return createAbstractCapabilityPkgAdapter();
      }
      @Override
      public Adapter caseGenericTrace(GenericTrace object) {
        return createGenericTraceAdapter();
      }
      @Override
      public Adapter caseTransfoLink(TransfoLink object) {
        return createTransfoLinkAdapter();
      }
      @Override
      public Adapter caseJustificationLink(JustificationLink object) {
        return createJustificationLinkAdapter();
      }
      @Override
      public Adapter caseCapabilityRealizationInvolvement(CapabilityRealizationInvolvement object) {
        return createCapabilityRealizationInvolvementAdapter();
      }
      @Override
      public Adapter caseCapabilityRealizationInvolvedElement(CapabilityRealizationInvolvedElement object) {
        return createCapabilityRealizationInvolvedElementAdapter();
      }
      @Override
      public Adapter caseStateMachine(StateMachine object) {
        return createStateMachineAdapter();
      }
      @Override
      public Adapter caseRegion(Region object) {
        return createRegionAdapter();
      }
      @Override
      public Adapter caseState(State object) {
        return createStateAdapter();
      }
      @Override
      public Adapter caseMode(Mode object) {
        return createModeAdapter();
      }
      @Override
      public Adapter caseFinalState(FinalState object) {
        return createFinalStateAdapter();
      }
      @Override
      public Adapter caseAbstractState(AbstractState object) {
        return createAbstractStateAdapter();
      }
      @Override
      public Adapter caseStateTransition(StateTransition object) {
        return createStateTransitionAdapter();
      }
      @Override
      public Adapter casePseudostate(Pseudostate object) {
        return createPseudostateAdapter();
      }
      @Override
      public Adapter caseInitialPseudoState(InitialPseudoState object) {
        return createInitialPseudoStateAdapter();
      }
      @Override
      public Adapter caseJoinPseudoState(JoinPseudoState object) {
        return createJoinPseudoStateAdapter();
      }
      @Override
      public Adapter caseForkPseudoState(ForkPseudoState object) {
        return createForkPseudoStateAdapter();
      }
      @Override
      public Adapter caseChoicePseudoState(ChoicePseudoState object) {
        return createChoicePseudoStateAdapter();
      }
      @Override
      public Adapter caseTerminatePseudoState(TerminatePseudoState object) {
        return createTerminatePseudoStateAdapter();
      }
      @Override
      public Adapter caseAbstractStateRealization(AbstractStateRealization object) {
        return createAbstractStateRealizationAdapter();
      }
      @Override
      public Adapter caseStateTransitionRealization(StateTransitionRealization object) {
        return createStateTransitionRealizationAdapter();
      }
      @Override
      public Adapter caseShallowHistoryPseudoState(ShallowHistoryPseudoState object) {
        return createShallowHistoryPseudoStateAdapter();
      }
      @Override
      public Adapter caseDeepHistoryPseudoState(DeepHistoryPseudoState object) {
        return createDeepHistoryPseudoStateAdapter();
      }
      @Override
      public Adapter caseEntryPointPseudoState(EntryPointPseudoState object) {
        return createEntryPointPseudoStateAdapter();
      }
      @Override
      public Adapter caseExitPointPseudoState(ExitPointPseudoState object) {
        return createExitPointPseudoStateAdapter();
      }
      @Override
      public Adapter caseStateEventRealization(StateEventRealization object) {
        return createStateEventRealizationAdapter();
      }
      @Override
      public Adapter caseStateEvent(StateEvent object) {
        return createStateEventAdapter();
      }
      @Override
      public Adapter caseChangeEvent(ChangeEvent object) {
        return createChangeEventAdapter();
      }
      @Override
      public Adapter caseTimeEvent(TimeEvent object) {
        return createTimeEventAdapter();
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
      public Adapter caseStructure(Structure object) {
        return createStructureAdapter();
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
      public Adapter caseInvolvement(Involvement object) {
        return createInvolvementAdapter();
      }
      @Override
      public Adapter caseInvolvedElement(InvolvedElement object) {
        return createInvolvedElementAdapter();
      }
      @Override
      public Adapter caseAbstractBehavior(AbstractBehavior object) {
        return createAbstractBehaviorAdapter();
      }
      @Override
      public Adapter caseIState(IState object) {
        return createIStateAdapter();
      }
      @Override
      public Adapter caseAllocation(Allocation object) {
        return createAllocationAdapter();
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
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg <em>Abstract Capability Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg
   * @generated
   */
	public Adapter createAbstractCapabilityPkgAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.GenericTrace <em>Generic Trace</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.GenericTrace
   * @generated
   */
	public Adapter createGenericTraceAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.TransfoLink <em>Transfo Link</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.TransfoLink
   * @generated
   */
	public Adapter createTransfoLinkAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.JustificationLink <em>Justification Link</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.JustificationLink
   * @generated
   */
	public Adapter createJustificationLinkAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement <em>Capability Realization Involvement</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement
   * @generated
   */
	public Adapter createCapabilityRealizationInvolvementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement <em>Capability Realization Involved Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement
   * @generated
   */
	public Adapter createCapabilityRealizationInvolvedElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.StateMachine <em>State Machine</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.StateMachine
   * @generated
   */
	public Adapter createStateMachineAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.Region <em>Region</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.Region
   * @generated
   */
	public Adapter createRegionAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.State <em>State</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.State
   * @generated
   */
	public Adapter createStateAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.Mode <em>Mode</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.Mode
   * @generated
   */
	public Adapter createModeAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.FinalState <em>Final State</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.FinalState
   * @generated
   */
	public Adapter createFinalStateAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.AbstractState <em>Abstract State</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.AbstractState
   * @generated
   */
	public Adapter createAbstractStateAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.StateTransition <em>State Transition</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.StateTransition
   * @generated
   */
	public Adapter createStateTransitionAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.Pseudostate <em>Pseudostate</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.Pseudostate
   * @generated
   */
	public Adapter createPseudostateAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.InitialPseudoState <em>Initial Pseudo State</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.InitialPseudoState
   * @generated
   */
	public Adapter createInitialPseudoStateAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.JoinPseudoState <em>Join Pseudo State</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.JoinPseudoState
   * @generated
   */
	public Adapter createJoinPseudoStateAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.ForkPseudoState <em>Fork Pseudo State</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.ForkPseudoState
   * @generated
   */
	public Adapter createForkPseudoStateAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.ChoicePseudoState <em>Choice Pseudo State</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.ChoicePseudoState
   * @generated
   */
	public Adapter createChoicePseudoStateAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.TerminatePseudoState <em>Terminate Pseudo State</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.TerminatePseudoState
   * @generated
   */
	public Adapter createTerminatePseudoStateAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.AbstractStateRealization <em>Abstract State Realization</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.AbstractStateRealization
   * @generated
   */
	public Adapter createAbstractStateRealizationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.StateTransitionRealization <em>State Transition Realization</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.StateTransitionRealization
   * @generated
   */
	public Adapter createStateTransitionRealizationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.ShallowHistoryPseudoState <em>Shallow History Pseudo State</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.ShallowHistoryPseudoState
   * @generated
   */
	public Adapter createShallowHistoryPseudoStateAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.DeepHistoryPseudoState <em>Deep History Pseudo State</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.DeepHistoryPseudoState
   * @generated
   */
	public Adapter createDeepHistoryPseudoStateAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.EntryPointPseudoState <em>Entry Point Pseudo State</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.EntryPointPseudoState
   * @generated
   */
	public Adapter createEntryPointPseudoStateAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.ExitPointPseudoState <em>Exit Point Pseudo State</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.ExitPointPseudoState
   * @generated
   */
	public Adapter createExitPointPseudoStateAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.StateEventRealization <em>State Event Realization</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.StateEventRealization
   * @generated
   */
	public Adapter createStateEventRealizationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.StateEvent <em>State Event</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.StateEvent
   * @generated
   */
	public Adapter createStateEventAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.ChangeEvent <em>Change Event</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.ChangeEvent
   * @generated
   */
	public Adapter createChangeEventAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacommon.TimeEvent <em>Time Event</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacommon.TimeEvent
   * @generated
   */
	public Adapter createTimeEventAdapter() {
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
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.InvolvedElement <em>Involved Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.InvolvedElement
   * @generated
   */
	public Adapter createInvolvedElementAdapter() {
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
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.IState <em>IState</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.IState
   * @generated
   */
	public Adapter createIStateAdapter() {
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

} //CapellacommonAdapterFactory

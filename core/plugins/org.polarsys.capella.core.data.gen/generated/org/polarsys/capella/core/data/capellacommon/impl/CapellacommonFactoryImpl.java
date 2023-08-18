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
package org.polarsys.capella.core.data.capellacommon.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.core.data.capellacommon.*;
import org.polarsys.capella.core.data.capellacommon.AbstractStateRealization;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.ChangeEvent;
import org.polarsys.capella.core.data.capellacommon.ChangeEventKind;
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
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.ShallowHistoryPseudoState;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateEventRealization;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacommon.StateTransitionRealization;
import org.polarsys.capella.core.data.capellacommon.TerminatePseudoState;
import org.polarsys.capella.core.data.capellacommon.TimeEvent;
import org.polarsys.capella.core.data.capellacommon.TimeEventKind;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.capellacommon.TransitionKind;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CapellacommonFactoryImpl extends EFactoryImpl implements CapellacommonFactory {
	/**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static CapellacommonFactory init() {
    try {
      CapellacommonFactory theCapellacommonFactory = (CapellacommonFactory)EPackage.Registry.INSTANCE.getEFactory(CapellacommonPackage.eNS_URI);
      if (theCapellacommonFactory != null) {
        return theCapellacommonFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new CapellacommonFactoryImpl();
  }

	/**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CapellacommonFactoryImpl() {
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
      case CapellacommonPackage.GENERIC_TRACE: return createGenericTrace();
      case CapellacommonPackage.TRANSFO_LINK: return createTransfoLink();
      case CapellacommonPackage.JUSTIFICATION_LINK: return createJustificationLink();
      case CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVEMENT: return createCapabilityRealizationInvolvement();
      case CapellacommonPackage.STATE_MACHINE: return createStateMachine();
      case CapellacommonPackage.REGION: return createRegion();
      case CapellacommonPackage.STATE: return createState();
      case CapellacommonPackage.MODE: return createMode();
      case CapellacommonPackage.FINAL_STATE: return createFinalState();
      case CapellacommonPackage.STATE_TRANSITION: return createStateTransition();
      case CapellacommonPackage.INITIAL_PSEUDO_STATE: return createInitialPseudoState();
      case CapellacommonPackage.JOIN_PSEUDO_STATE: return createJoinPseudoState();
      case CapellacommonPackage.FORK_PSEUDO_STATE: return createForkPseudoState();
      case CapellacommonPackage.CHOICE_PSEUDO_STATE: return createChoicePseudoState();
      case CapellacommonPackage.TERMINATE_PSEUDO_STATE: return createTerminatePseudoState();
      case CapellacommonPackage.ABSTRACT_STATE_REALIZATION: return createAbstractStateRealization();
      case CapellacommonPackage.STATE_TRANSITION_REALIZATION: return createStateTransitionRealization();
      case CapellacommonPackage.SHALLOW_HISTORY_PSEUDO_STATE: return createShallowHistoryPseudoState();
      case CapellacommonPackage.DEEP_HISTORY_PSEUDO_STATE: return createDeepHistoryPseudoState();
      case CapellacommonPackage.ENTRY_POINT_PSEUDO_STATE: return createEntryPointPseudoState();
      case CapellacommonPackage.EXIT_POINT_PSEUDO_STATE: return createExitPointPseudoState();
      case CapellacommonPackage.STATE_EVENT_REALIZATION: return createStateEventRealization();
      case CapellacommonPackage.CHANGE_EVENT: return createChangeEvent();
      case CapellacommonPackage.TIME_EVENT: return createTimeEvent();
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
      case CapellacommonPackage.TRANSITION_KIND:
        return createTransitionKindFromString(eDataType, initialValue);
      case CapellacommonPackage.TIME_EVENT_KIND:
        return createTimeEventKindFromString(eDataType, initialValue);
      case CapellacommonPackage.CHANGE_EVENT_KIND:
        return createChangeEventKindFromString(eDataType, initialValue);
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
      case CapellacommonPackage.TRANSITION_KIND:
        return convertTransitionKindToString(eDataType, instanceValue);
      case CapellacommonPackage.TIME_EVENT_KIND:
        return convertTimeEventKindToString(eDataType, instanceValue);
      case CapellacommonPackage.CHANGE_EVENT_KIND:
        return convertChangeEventKindToString(eDataType, instanceValue);
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
	public GenericTrace createGenericTrace() {
    GenericTraceImpl genericTrace = new GenericTraceImpl();
    //begin-capella-code
    genericTrace.setId(IdGenerator.createId());
    //end-capella-code
    return genericTrace;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public TransfoLink createTransfoLink() {
    TransfoLinkImpl transfoLink = new TransfoLinkImpl();
    //begin-capella-code
    transfoLink.setId(IdGenerator.createId());
    //end-capella-code
    return transfoLink;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public JustificationLink createJustificationLink() {
    JustificationLinkImpl justificationLink = new JustificationLinkImpl();
    //begin-capella-code
    justificationLink.setId(IdGenerator.createId());
    //end-capella-code
    return justificationLink;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CapabilityRealizationInvolvement createCapabilityRealizationInvolvement() {
    CapabilityRealizationInvolvementImpl capabilityRealizationInvolvement = new CapabilityRealizationInvolvementImpl();
    //begin-capella-code
    capabilityRealizationInvolvement.setId(IdGenerator.createId());
    //end-capella-code
    return capabilityRealizationInvolvement;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public StateMachine createStateMachine() {
    StateMachineImpl stateMachine = new StateMachineImpl();
    //begin-capella-code
    stateMachine.setId(IdGenerator.createId());
    //end-capella-code
    return stateMachine;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Region createRegion() {
    RegionImpl region = new RegionImpl();
    //begin-capella-code
    region.setId(IdGenerator.createId());
    //end-capella-code
    return region;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public State createState() {
    StateImpl state = new StateImpl();
    //begin-capella-code
    state.setId(IdGenerator.createId());
    //end-capella-code
    return state;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Mode createMode() {
    ModeImpl mode = new ModeImpl();
    //begin-capella-code
    mode.setId(IdGenerator.createId());
    //end-capella-code
    return mode;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public FinalState createFinalState() {
    FinalStateImpl finalState = new FinalStateImpl();
    //begin-capella-code
    finalState.setId(IdGenerator.createId());
    //end-capella-code
    return finalState;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public StateTransition createStateTransition() {
    StateTransitionImpl stateTransition = new StateTransitionImpl();
    //begin-capella-code
    stateTransition.setId(IdGenerator.createId());
    //end-capella-code
    return stateTransition;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public InitialPseudoState createInitialPseudoState() {
    InitialPseudoStateImpl initialPseudoState = new InitialPseudoStateImpl();
    //begin-capella-code
    initialPseudoState.setId(IdGenerator.createId());
    //end-capella-code
    return initialPseudoState;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public JoinPseudoState createJoinPseudoState() {
    JoinPseudoStateImpl joinPseudoState = new JoinPseudoStateImpl();
    //begin-capella-code
    joinPseudoState.setId(IdGenerator.createId());
    //end-capella-code
    return joinPseudoState;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ForkPseudoState createForkPseudoState() {
    ForkPseudoStateImpl forkPseudoState = new ForkPseudoStateImpl();
    //begin-capella-code
    forkPseudoState.setId(IdGenerator.createId());
    //end-capella-code
    return forkPseudoState;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ChoicePseudoState createChoicePseudoState() {
    ChoicePseudoStateImpl choicePseudoState = new ChoicePseudoStateImpl();
    //begin-capella-code
    choicePseudoState.setId(IdGenerator.createId());
    //end-capella-code
    return choicePseudoState;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public TerminatePseudoState createTerminatePseudoState() {
    TerminatePseudoStateImpl terminatePseudoState = new TerminatePseudoStateImpl();
    //begin-capella-code
    terminatePseudoState.setId(IdGenerator.createId());
    //end-capella-code
    return terminatePseudoState;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public AbstractStateRealization createAbstractStateRealization() {
    AbstractStateRealizationImpl abstractStateRealization = new AbstractStateRealizationImpl();
    //begin-capella-code
    abstractStateRealization.setId(IdGenerator.createId());
    //end-capella-code
    return abstractStateRealization;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public StateTransitionRealization createStateTransitionRealization() {
    StateTransitionRealizationImpl stateTransitionRealization = new StateTransitionRealizationImpl();
    //begin-capella-code
    stateTransitionRealization.setId(IdGenerator.createId());
    //end-capella-code
    return stateTransitionRealization;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ShallowHistoryPseudoState createShallowHistoryPseudoState() {
    ShallowHistoryPseudoStateImpl shallowHistoryPseudoState = new ShallowHistoryPseudoStateImpl();
    //begin-capella-code
    shallowHistoryPseudoState.setId(IdGenerator.createId());
    //end-capella-code
    return shallowHistoryPseudoState;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public DeepHistoryPseudoState createDeepHistoryPseudoState() {
    DeepHistoryPseudoStateImpl deepHistoryPseudoState = new DeepHistoryPseudoStateImpl();
    //begin-capella-code
    deepHistoryPseudoState.setId(IdGenerator.createId());
    //end-capella-code
    return deepHistoryPseudoState;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EntryPointPseudoState createEntryPointPseudoState() {
    EntryPointPseudoStateImpl entryPointPseudoState = new EntryPointPseudoStateImpl();
    //begin-capella-code
    entryPointPseudoState.setId(IdGenerator.createId());
    //end-capella-code
    return entryPointPseudoState;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ExitPointPseudoState createExitPointPseudoState() {
    ExitPointPseudoStateImpl exitPointPseudoState = new ExitPointPseudoStateImpl();
    //begin-capella-code
    exitPointPseudoState.setId(IdGenerator.createId());
    //end-capella-code
    return exitPointPseudoState;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public StateEventRealization createStateEventRealization() {
    StateEventRealizationImpl stateEventRealization = new StateEventRealizationImpl();
    //begin-capella-code
    stateEventRealization.setId(IdGenerator.createId());
    //end-capella-code
    return stateEventRealization;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ChangeEvent createChangeEvent() {
    ChangeEventImpl changeEvent = new ChangeEventImpl();
    //begin-capella-code
    changeEvent.setId(IdGenerator.createId());
    //end-capella-code
    return changeEvent;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public TimeEvent createTimeEvent() {
    TimeEventImpl timeEvent = new TimeEventImpl();
    //begin-capella-code
    timeEvent.setId(IdGenerator.createId());
    //end-capella-code
    return timeEvent;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public TransitionKind createTransitionKindFromString(EDataType eDataType, String initialValue) {
    TransitionKind result = TransitionKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertTransitionKindToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public TimeEventKind createTimeEventKindFromString(EDataType eDataType, String initialValue) {
    TimeEventKind result = TimeEventKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertTimeEventKindToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ChangeEventKind createChangeEventKindFromString(EDataType eDataType, String initialValue) {
    ChangeEventKind result = ChangeEventKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertChangeEventKindToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CapellacommonPackage getCapellacommonPackage() {
    return (CapellacommonPackage)getEPackage();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
	@Deprecated
	public static CapellacommonPackage getPackage() {
    return CapellacommonPackage.eINSTANCE;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public StateMachine createStateMachine(String name_p) {
    StateMachine stateMachine = createStateMachine();
    stateMachine.setName(name_p);	  
    return stateMachine;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Region createRegion(String name_p) {
    Region region = createRegion();
    region.setName(name_p);	  
    return region;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public State createState(String name_p) {
    State state = createState();
    state.setName(name_p);	  
    return state;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Mode createMode(String name_p) {
    Mode mode = createMode();
    mode.setName(name_p);	  
    return mode;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public FinalState createFinalState(String name_p) {
    FinalState finalState = createFinalState();
    finalState.setName(name_p);	  
    return finalState;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public StateTransition createStateTransition(String name_p) {
    StateTransition stateTransition = createStateTransition();
    stateTransition.setName(name_p);	  
    return stateTransition;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public InitialPseudoState createInitialPseudoState(String name_p) {
    InitialPseudoState initialPseudoState = createInitialPseudoState();
    initialPseudoState.setName(name_p);	  
    return initialPseudoState;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public JoinPseudoState createJoinPseudoState(String name_p) {
    JoinPseudoState joinPseudoState = createJoinPseudoState();
    joinPseudoState.setName(name_p);	  
    return joinPseudoState;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ForkPseudoState createForkPseudoState(String name_p) {
    ForkPseudoState forkPseudoState = createForkPseudoState();
    forkPseudoState.setName(name_p);	  
    return forkPseudoState;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ChoicePseudoState createChoicePseudoState(String name_p) {
    ChoicePseudoState choicePseudoState = createChoicePseudoState();
    choicePseudoState.setName(name_p);	  
    return choicePseudoState;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public TerminatePseudoState createTerminatePseudoState(String name_p) {
    TerminatePseudoState terminatePseudoState = createTerminatePseudoState();
    terminatePseudoState.setName(name_p);	  
    return terminatePseudoState;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ShallowHistoryPseudoState createShallowHistoryPseudoState(String name_p) {
    ShallowHistoryPseudoState shallowHistoryPseudoState = createShallowHistoryPseudoState();
    shallowHistoryPseudoState.setName(name_p);	  
    return shallowHistoryPseudoState;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public DeepHistoryPseudoState createDeepHistoryPseudoState(String name_p) {
    DeepHistoryPseudoState deepHistoryPseudoState = createDeepHistoryPseudoState();
    deepHistoryPseudoState.setName(name_p);	  
    return deepHistoryPseudoState;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public EntryPointPseudoState createEntryPointPseudoState(String name_p) {
    EntryPointPseudoState entryPointPseudoState = createEntryPointPseudoState();
    entryPointPseudoState.setName(name_p);	  
    return entryPointPseudoState;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ExitPointPseudoState createExitPointPseudoState(String name_p) {
    ExitPointPseudoState exitPointPseudoState = createExitPointPseudoState();
    exitPointPseudoState.setName(name_p);	  
    return exitPointPseudoState;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ChangeEvent createChangeEvent(String name_p) {
    ChangeEvent changeEvent = createChangeEvent();
    changeEvent.setName(name_p);	  
    return changeEvent;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public TimeEvent createTimeEvent(String name_p) {
    TimeEvent timeEvent = createTimeEvent();
    timeEvent.setName(name_p);	  
    return timeEvent;
  }

	//begin-capella-code

	//end-capella-code
} //CapellacommonFactoryImpl

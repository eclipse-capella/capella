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

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Relationship;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>State Transition</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getKind <em>Kind</em>}</li>
 * <li>{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getTriggerDescription
 * <em>Trigger Description</em>}</li>
 * <li>{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getGuard <em>Guard</em>}</li>
 * <li>{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getSource <em>Source</em>}</li>
 * <li>{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getTarget <em>Target</em>}</li>
 * <li>{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getEffect <em>Effect</em>}</li>
 * <li>{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getTriggers <em>Triggers</em>}</li>
 * <li>{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getOwnedStateTransitionRealizations
 * <em>Owned State Transition Realizations</em>}</li>
 * <li>{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getRealizedStateTransitions
 * <em>Realized State Transitions</em>}</li>
 * <li>{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getRealizingStateTransitions
 * <em>Realizing State Transitions</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateTransition()
 * @model annotation=
 *        "http://www.polarsys.org/kitalpha/ecore/documentation description='A transition is a directed relationship between a source vertex and a target vertex. It may be part of a compound\r\ntransition, which takes the state machine from one state configuration to another, representing the complete response of\r\nthe state machine to an occurrence of an event of a particular type.\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='../img/usage_examples/example_statemachine.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation=
 *        "http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Transition' explanation='none' constraints='none'"
 * @generated
 */
public interface StateTransition extends NamedElement, Relationship {

  /**
   * Returns the value of the '<em><b>Guard</b></em>' reference.

   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Guard</em>' attribute isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Guard</em>' reference.
   * @see #setGuard(Constraint)
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateTransition_Guard()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies the guard of the state transition' constraints='none' type='' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

  Constraint getGuard();

  /**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getGuard <em>Guard</em>}' reference.

   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @param value the new value of the '<em>Guard</em>' reference.
   * @see #getGuard()
   * @generated
   */

  void setGuard(Constraint value);

  /**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.capellacommon.TransitionKind}.

   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Kind</em>' attribute isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.capellacommon.TransitionKind
   * @see #setKind(TransitionKind)
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateTransition_Kind()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies the type of the state transition (see TransitionKind)\r\n[source: Capella study]' constraints='none' type='refer to TransitionKind definition' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Transition::kind' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

  TransitionKind getKind();

  /**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getKind <em>Kind</em>}' attribute.

   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.capellacommon.TransitionKind
   * @see #getKind()
   * @generated
   */

  void setKind(TransitionKind value);

  /**
   * Returns the value of the '<em><b>Trigger Description</b></em>' attribute.

   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Trigger Description</em>' attribute isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Trigger Description</em>' attribute.
   * @see #setTriggerDescription(String)
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateTransition_TriggerDescription()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='describes the trigger associated to the transition\r\n[source: Capella study]' constraints='none' type='' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

  String getTriggerDescription();

  /**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getTriggerDescription <em>Trigger Description</em>}' attribute.

   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @param value the new value of the '<em>Trigger Description</em>' attribute.
   * @see #getTriggerDescription()
   * @generated
   */

  void setTriggerDescription(String value);

  /**
   * Returns the value of the '<em><b>Source</b></em>' reference.

   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source</em>' reference isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source</em>' reference.
   * @see #setSource(AbstractState)
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateTransition_Source()
   * @model required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Designates the originating vertex (state or pseudostate) of the transition.\r\n[source:UML Superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Transition::source' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

  AbstractState getSource();

  /**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getSource <em>Source</em>}' reference.

   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' reference.
   * @see #getSource()
   * @generated
   */

  void setSource(AbstractState value);

  /**
   * Returns the value of the '<em><b>Target</b></em>' reference.

   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target</em>' reference isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' reference.
   * @see #setTarget(AbstractState)
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateTransition_Target()
   * @model required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Designates the target vertex that is reached when the transition is taken.\r\n[source:UML Superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Transition::target' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

  AbstractState getTarget();

  /**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getTarget <em>Target</em>}' reference.

   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' reference.
   * @see #getTarget()
   * @generated
   */

  void setTarget(AbstractState value);

  /**
   * Returns the value of the '<em><b>Effect</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.behavior.AbstractEvent}.

   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Effect</em>' reference isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Effect</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateTransition_Effect()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The event to be triggered' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

  EList<AbstractEvent> getEffect();

  /**
   * Returns the value of the '<em><b>Triggers</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.behavior.AbstractEvent}.

   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Triggers</em>' reference list isn't clear, there really should be more of a description
   * here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Triggers</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateTransition_Triggers()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies the triggers that may fire the transition.' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Transition::trigger' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

  EList<AbstractEvent> getTriggers();

  /**
   * Returns the value of the '<em><b>Owned State Transition Realizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacommon.StateTransitionRealization}.

   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Owned State Transition Realizations</em>' containment reference list isn't clear, there
   * really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Owned State Transition Realizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateTransition_OwnedStateTransitionRealizations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the realization links that are owned/contained in this StateTransition\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='Elements are contained in the nearest possible parent container.' constraints='Some elements on which StateTransitionRealization stereotype or any stereotype that inherits from it is applied'"
   * @generated
   */

  EList<StateTransitionRealization> getOwnedStateTransitionRealizations();

  /**
   * Returns the value of the '<em><b>Realized State Transitions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacommon.StateTransition}.

   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Realized State Transitions</em>' reference list isn't clear, there really should be more
   * of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Realized State Transitions</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateTransition_RealizedStateTransitions()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='StateTransitionRealization.realizingStateTransition(asr, self);\r\nStateTransitionRealization.realizedStateTransition(asr, target);\r\n'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

  EList<StateTransition> getRealizedStateTransitions();

  /**
   * Returns the value of the '<em><b>Realizing State Transitions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacommon.StateTransition}.

   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Realizing State Transitions</em>' reference list isn't clear, there really should be
   * more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing State Transitions</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateTransition_RealizingStateTransitions()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='StateTransitionRealization.realizedStateTransition(asr, self);\r\nStateTransitionRealization.realizingStateTransition(asr, target);\r\n'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

  EList<StateTransition> getRealizingStateTransitions();

} // StateTransition

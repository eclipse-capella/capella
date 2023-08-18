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

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.behavior.AbstractBehavior;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scenario</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.Scenario#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.Scenario#isMerged <em>Merged</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.Scenario#getPreCondition <em>Pre Condition</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.Scenario#getPostCondition <em>Post Condition</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.Scenario#getOwnedInstanceRoles <em>Owned Instance Roles</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.Scenario#getOwnedMessages <em>Owned Messages</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.Scenario#getOwnedInteractionFragments <em>Owned Interaction Fragments</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.Scenario#getOwnedTimeLapses <em>Owned Time Lapses</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.Scenario#getOwnedEvents <em>Owned Events</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.Scenario#getOwnedFormalGates <em>Owned Formal Gates</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.Scenario#getOwnedScenarioRealization <em>Owned Scenario Realization</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.Scenario#getOwnedConstraintDurations <em>Owned Constraint Durations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.Scenario#getContainedFunctions <em>Contained Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.Scenario#getContainedParts <em>Contained Parts</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.Scenario#getReferencedScenarios <em>Referenced Scenarios</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.Scenario#getRealizedScenarios <em>Realized Scenarios</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.Scenario#getRealizingScenarios <em>Realizing Scenarios</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getScenario()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Scenario'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Interaction' stereotype='eng.Scenario'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Definition of a dynamic behaviour composed of the following information :\r\nContext, objective, pre-conditions, post-conditions, used capabilities, involved roles &amp; actors, operational exchanges &amp; interactions, processes and activities. Ability to be validated. Temporal &amp; performance description.Criticity.\r\nScenarios can be gathered in a set of Use Cases.\r\n\r\nA scenario describes a temporal dynamic interaction between actors (included the system or possibly its components) through their exchanges, it also describes the initialisation and the evolution of the context of the interaction.\r\n[source:ARCADIA encyclopedia v0.8.0]\r\n\r\nA scenario is similar to UML Interaction concept :\r\nAn interaction is a unit of behavior that focuses on the observable exchange of information between\r\nConnectableElements.\r\n\r\nA scenario can be compared to an UML sequence diagram :\r\nA sequence diagram describes an Interaction by focusing on the sequence of Messages that are exchanged, along with\r\ntheir corresponding OccurrenceSpecifications on the Lifelines.\r\n[source:UML Superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Interaction' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface Scenario extends Namespace, AbstractBehavior {





	/**
   * Returns the value of the '<em><b>Pre Condition</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pre Condition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Pre Condition</em>' reference.
   * @see #setPreCondition(Constraint)
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getScenario_PreCondition()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the prerequisite conditions for the use of this Scenario' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints=''"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	Constraint getPreCondition();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.interaction.Scenario#getPreCondition <em>Pre Condition</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pre Condition</em>' reference.
   * @see #getPreCondition()
   * @generated
   */

	void setPreCondition(Constraint value);




	/**
   * Returns the value of the '<em><b>Post Condition</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Post Condition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Post Condition</em>' reference.
   * @see #setPostCondition(Constraint)
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getScenario_PostCondition()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the conditions applying after this Scenario has been exercized' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints=''"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	Constraint getPostCondition();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.interaction.Scenario#getPostCondition <em>Post Condition</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Post Condition</em>' reference.
   * @see #getPostCondition()
   * @generated
   */

	void setPostCondition(Constraint value);




	/**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * The default value is <code>"UNSET"</code>.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.interaction.ScenarioKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.interaction.ScenarioKind
   * @see #setKind(ScenarioKind)
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getScenario_Kind()
   * @model default="UNSET"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ScenarioKind getKind();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.interaction.Scenario#getKind <em>Kind</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.interaction.ScenarioKind
   * @see #getKind()
   * @generated
   */

	void setKind(ScenarioKind value);







	/**
   * Returns the value of the '<em><b>Merged</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Merged</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Merged</em>' attribute.
   * @see #setMerged(boolean)
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getScenario_Merged()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='isMerged' featureOwner='eng.Scenario' fromStereotype='true'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Whether the scenario underwent a merge operation for the transition from one level to the next\r\n[source: Capella study]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isMerged();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.interaction.Scenario#isMerged <em>Merged</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Merged</em>' attribute.
   * @see #isMerged()
   * @generated
   */

	void setMerged(boolean value);







	/**
   * Returns the value of the '<em><b>Owned Instance Roles</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.InstanceRole}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Instance Roles</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Instance Roles</em>' containment reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getScenario_OwnedInstanceRoles()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='lifeline' featureOwner='Interaction'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='instanceRoles'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to the set of instance roles (lifelines)\r\n[source:Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Interaction::lifeline' explanation='none' constraints='uml::Interaction::lifeline elements on which InstanceRole stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<InstanceRole> getOwnedInstanceRoles();







	/**
   * Returns the value of the '<em><b>Owned Messages</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.SequenceMessage}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Messages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Messages</em>' containment reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getScenario_OwnedMessages()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='message' featureOwner='Interaction'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='messages'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to the owned sequence messages\r\n[Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Interaction::message' explanation='none' constraints='Order must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<SequenceMessage> getOwnedMessages();







	/**
   * Returns the value of the '<em><b>Owned Interaction Fragments</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.InteractionFragment}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Interaction Fragments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Interaction Fragments</em>' containment reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getScenario_OwnedInteractionFragments()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='fragment' featureOwner='Interaction'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedAbstractEnds'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to the owned message and operation ends\r\n[source:Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Interaction::fragment' explanation='none' constraints='uml::Interaction::fragment elements on which AbstractEnd stereotype or any stereotype that inherits from it is applied'"
   * @generated
   */

	EList<InteractionFragment> getOwnedInteractionFragments();







	/**
   * Returns the value of the '<em><b>Owned Time Lapses</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.TimeLapse}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Time Lapses</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Time Lapses</em>' containment reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getScenario_OwnedTimeLapses()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='fragment' featureOwner='Interaction'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedExecutions'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to the set of owned executions\r\n[source:Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Interaction::fragment' explanation='none' constraints='uml::Interaction::fragment elements on which Execution stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<TimeLapse> getOwnedTimeLapses();







	/**
   * Returns the value of the '<em><b>Owned Events</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.Event}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Events</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Events</em>' containment reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getScenario_OwnedEvents()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedEvents'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Events associated to this Capability\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='specific rule : a package will be created in the nearest package, the events will be stored there, and the Capability will have a package import element.' constraints=''"
   * @generated
   */

	EList<Event> getOwnedEvents();







	/**
   * Returns the value of the '<em><b>Owned Formal Gates</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.Gate}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Formal Gates</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Formal Gates</em>' containment reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getScenario_OwnedFormalGates()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   * @generated
   */

	EList<Gate> getOwnedFormalGates();







	/**
   * Returns the value of the '<em><b>Owned Scenario Realization</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.ScenarioRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Scenario Realization</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Scenario Realization</em>' containment reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getScenario_OwnedScenarioRealization()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   * @generated
   */

	EList<ScenarioRealization> getOwnedScenarioRealization();







	/**
   * Returns the value of the '<em><b>Owned Constraint Durations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.ConstraintDuration}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Constraint Durations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Constraint Durations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getScenario_OwnedConstraintDurations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ConstraintDuration> getOwnedConstraintDurations();







	/**
   * Returns the value of the '<em><b>Contained Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.AbstractFunction}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getScenario_ContainedFunctions()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the SequenceMessage list, in sequence order' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedInstanceRoles.representedInstance'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<AbstractFunction> getContainedFunctions();







	/**
   * Returns the value of the '<em><b>Contained Parts</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Part}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Parts</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained Parts</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getScenario_ContainedParts()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the SequenceMessage list, in sequence order' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedInstanceRoles.representedInstance'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Part> getContainedParts();







	/**
   * Returns the value of the '<em><b>Referenced Scenarios</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.Scenario}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Scenarios</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Referenced Scenarios</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getScenario_ReferencedScenarios()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='Scenario.ownedTimeLapses(self, iu);\r\nInteractionUse.referencedScenario(iu, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Scenario> getReferencedScenarios();







	/**
   * Returns the value of the '<em><b>Realized Scenarios</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.Scenario}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.interaction.Scenario#getRealizingScenarios <em>Realizing Scenarios</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Scenarios</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Scenarios</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getScenario_RealizedScenarios()
   * @see org.polarsys.capella.core.data.interaction.Scenario#getRealizingScenarios
   * @model opposite="realizingScenarios" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='Scenario.outgoingTraces(self, sr);\r\nScenarioRealization.realizedScenario(sr, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Scenario> getRealizedScenarios();







	/**
   * Returns the value of the '<em><b>Realizing Scenarios</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.Scenario}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.interaction.Scenario#getRealizedScenarios <em>Realized Scenarios</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Scenarios</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Scenarios</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getScenario_RealizingScenarios()
   * @see org.polarsys.capella.core.data.interaction.Scenario#getRealizedScenarios
   * @model opposite="realizedScenarios" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='Scenario.incomingTraces(self, sr);\r\nScenarioRealization.realizingScenario(sr, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<Scenario> getRealizingScenarios();





} // Scenario

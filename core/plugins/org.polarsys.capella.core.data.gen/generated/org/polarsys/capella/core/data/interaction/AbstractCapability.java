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
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer;
import org.polarsys.capella.core.data.fa.FunctionalChain;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Capability</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getPreCondition <em>Pre Condition</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getPostCondition <em>Post Condition</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getOwnedScenarios <em>Owned Scenarios</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getIncomingCapabilityAllocation <em>Incoming Capability Allocation</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getOutgoingCapabilityAllocation <em>Outgoing Capability Allocation</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getExtends <em>Extends</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getExtending <em>Extending</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getAbstractCapabilityExtensionPoints <em>Abstract Capability Extension Points</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getSuperGeneralizations <em>Super Generalizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getSubGeneralizations <em>Sub Generalizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getIncludes <em>Includes</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getIncluding <em>Including</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getSuper <em>Super</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getSub <em>Sub</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getIncludedAbstractCapabilities <em>Included Abstract Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getIncludingAbstractCapabilities <em>Including Abstract Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getExtendedAbstractCapabilities <em>Extended Abstract Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getExtendingAbstractCapabilities <em>Extending Abstract Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getOwnedFunctionalChainAbstractCapabilityInvolvements <em>Owned Functional Chain Abstract Capability Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getOwnedAbstractFunctionAbstractCapabilityInvolvements <em>Owned Abstract Function Abstract Capability Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getAvailableInStates <em>Available In States</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getOwnedAbstractCapabilityRealizations <em>Owned Abstract Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getInvolvedAbstractFunctions <em>Involved Abstract Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getInvolvedFunctionalChains <em>Involved Functional Chains</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='AbstractCapability'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Package'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Base class for Capabilities (Capability and Capability Realization)\r\n[source:UML Superstructure v2.2]' usage\040guideline='n/a (Abstract)' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::UseCase' constraints='none'"
 * @generated
 */
public interface AbstractCapability extends Structure, InvolverElement, AbstractFunctionalChainContainer {





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
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_PreCondition()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the prerequisite conditions for the use of this Capability\r\n[source: Capella study]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints=''"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	Constraint getPreCondition();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getPreCondition <em>Pre Condition</em>}' reference.

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
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_PostCondition()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the conditions applying after this Capability has been exercized\r\n[source: Capella study]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints=''"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	Constraint getPostCondition();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getPostCondition <em>Post Condition</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Post Condition</em>' reference.
   * @see #getPostCondition()
   * @generated
   */

	void setPostCondition(Constraint value);




	/**
   * Returns the value of the '<em><b>Owned Scenarios</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.Scenario}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Scenarios</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Scenarios</em>' containment reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_OwnedScenarios()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='ownedBehavior' featureOwner='BehavioredClassifier'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='scenarios'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Scenarios describing the dynamic aspects of this Capability\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::BehavioredClassifier::ownedBehavior' explanation='none' constraints='uml::BehavioredClassifier::ownedBehavior elements on which Scenario stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Scenario> getOwnedScenarios();







	/**
   * Returns the value of the '<em><b>Incoming Capability Allocation</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization#getRealizedCapability <em>Realized Capability</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Capability Allocation</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Incoming Capability Allocation</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_IncomingCapabilityAllocation()
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization#getRealizedCapability
   * @model opposite="realizedCapability" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='incomingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the allocations links which destination is this Capability\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<AbstractCapabilityRealization> getIncomingCapabilityAllocation();







	/**
   * Returns the value of the '<em><b>Outgoing Capability Allocation</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization#getRealizingCapability <em>Realizing Capability</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Capability Allocation</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Outgoing Capability Allocation</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_OutgoingCapabilityAllocation()
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization#getRealizingCapability
   * @model opposite="realizingCapability" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='outgoingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the allocation links having this Capability as their start point\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<AbstractCapabilityRealization> getOutgoingCapabilityAllocation();







	/**
   * Returns the value of the '<em><b>Extends</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extends</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Extends</em>' containment reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_Extends()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='extend' featureOwner='UseCase'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='extends'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of reference elements to the Capabilities that this Capability extends\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::UseCase::extend' explanation='none' constraints='Order must be computed'"
   * @generated
   */

	EList<AbstractCapabilityExtend> getExtends();







	/**
   * Returns the value of the '<em><b>Extending</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extending</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Extending</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_Extending()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping umlOppositeReference='extendedCase' umlOppositeReferenceOwner='Extend'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='extending'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of reference elements to Capabilities that extend this Capability\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Opposite reference of uml::Extend::extendedCase' constraints='Order must be computed'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='extended'"
   * @generated
   */

	EList<AbstractCapabilityExtend> getExtending();







	/**
   * Returns the value of the '<em><b>Abstract Capability Extension Points</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abstract Capability Extension Points</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Abstract Capability Extension Points</em>' containment reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_AbstractCapabilityExtensionPoints()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='extensionPoint' featureOwner='UseCase'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='abstractCapabilityExtensionPoints'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the extension points that this Capability provides\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::UseCase::extensionPoint' explanation='none' constraints='Order must be computed'"
   * @generated
   */

	EList<AbstractCapabilityExtensionPoint> getAbstractCapabilityExtensionPoints();







	/**
   * Returns the value of the '<em><b>Super Generalizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Generalizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Super Generalizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_SuperGeneralizations()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='generalization' featureOwner='Classifier'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='generalizations'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of references to Capabilities from which this Capability inherits\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Classifier::generalization' explanation='none' constraints='Order must be computed'"
   * @generated
   */

	EList<AbstractCapabilityGeneralization> getSuperGeneralizations();







	/**
   * Returns the value of the '<em><b>Sub Generalizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Generalizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Sub Generalizations</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_SubGeneralizations()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='generalization' featureOwner='Classifier'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='generalizations'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of references to Capabilities that derive from this Capability\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Opposite reference of uml::Generalization::general' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='^super'"
   * @generated
   */

	EList<AbstractCapabilityGeneralization> getSubGeneralizations();







	/**
   * Returns the value of the '<em><b>Includes</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Includes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Includes</em>' containment reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_Includes()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='include' featureOwner='UseCase'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='includes'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of references to Capabilities used/included by this Capability\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::UseCase::include' explanation='none' constraints='Order must be computed'"
   * @generated
   */

	EList<AbstractCapabilityInclude> getIncludes();







	/**
   * Returns the value of the '<em><b>Including</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Including</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Including</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_Including()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping umlOppositeReference='addition' umlOppositeReferenceOwner='Include'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='including'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of references to Capabilities that use/include this Capability\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Opposite reference of uml::Include::addition' constraints='Order must be computed'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='included'"
   * @generated
   */

	EList<AbstractCapabilityInclude> getIncluding();







	/**
   * Returns the value of the '<em><b>Super</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.AbstractCapability}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Super</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_Super()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='superAbstractCapabilityUseCases'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='superGeneralizations.^super'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the direct references to Capabilities from which this Capability inherit' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<AbstractCapability> getSuper();







	/**
   * Returns the value of the '<em><b>Sub</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.AbstractCapability}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Sub</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_Sub()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='superAbstractCapabilityUseCases'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='subGeneralizations.sub'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the direct references to Capabilities that inherit from this Capability' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<AbstractCapability> getSub();







	/**
   * Returns the value of the '<em><b>Included Abstract Capabilities</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.AbstractCapability}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Included Abstract Capabilities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Included Abstract Capabilities</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_IncludedAbstractCapabilities()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='includedAbstractCapabilityUseCases'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='includes.included'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the direct references to the Capabilities that this Capability uses/includes' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<AbstractCapability> getIncludedAbstractCapabilities();







	/**
   * Returns the value of the '<em><b>Including Abstract Capabilities</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.AbstractCapability}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Including Abstract Capabilities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Including Abstract Capabilities</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_IncludingAbstractCapabilities()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='includedAbstractCapabilityUseCases'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='AbstractCapabilityInclude.included(aci, self);\r\nAbstractCapabilityInclude.inclusion(aci, target);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the direct references to the Capabilities that this Capability uses/includes' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<AbstractCapability> getIncludingAbstractCapabilities();







	/**
   * Returns the value of the '<em><b>Extended Abstract Capabilities</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.AbstractCapability}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extended Abstract Capabilities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Extended Abstract Capabilities</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_ExtendedAbstractCapabilities()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='extendedCapabilityUseCases'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='^extends.extended'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the direct references to the Capabilities that this Capability extends' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<AbstractCapability> getExtendedAbstractCapabilities();







	/**
   * Returns the value of the '<em><b>Extending Abstract Capabilities</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.AbstractCapability}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extending Abstract Capabilities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Extending Abstract Capabilities</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_ExtendingAbstractCapabilities()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='extendedCapabilityUseCases'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='AbstractCapabilityExtend.extended(ace, self);\r\nAbstractCapabilityExtend.^extension(ace, target);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the direct references to the Capabilities that this Capability extends' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<AbstractCapability> getExtendingAbstractCapabilities();







	/**
   * Returns the value of the '<em><b>Owned Functional Chain Abstract Capability Involvements</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Functional Chain Abstract Capability Involvements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Functional Chain Abstract Capability Involvements</em>' containment reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_OwnedFunctionalChainAbstractCapabilityInvolvements()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<FunctionalChainAbstractCapabilityInvolvement> getOwnedFunctionalChainAbstractCapabilityInvolvements();







	/**
   * Returns the value of the '<em><b>Owned Abstract Function Abstract Capability Involvements</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Abstract Function Abstract Capability Involvements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Abstract Function Abstract Capability Involvements</em>' containment reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_OwnedAbstractFunctionAbstractCapabilityInvolvements()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<AbstractFunctionAbstractCapabilityInvolvement> getOwnedAbstractFunctionAbstractCapabilityInvolvements();







	/**
   * Returns the value of the '<em><b>Available In States</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacommon.State}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Available In States</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Available In States</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_AvailableInStates()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of (system) states in which this abstract capability is actually available\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<State> getAvailableInStates();







	/**
   * Returns the value of the '<em><b>Owned Abstract Capability Realizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Abstract Capability Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Abstract Capability Realizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_OwnedAbstractCapabilityRealizations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<AbstractCapabilityRealization> getOwnedAbstractCapabilityRealizations();







	/**
   * Returns the value of the '<em><b>Involved Abstract Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.AbstractFunction}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involved Abstract Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involved Abstract Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_InvolvedAbstractFunctions()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='AbstractCapability.involvedInvolvements(self, afaci);\r\nAbstractFunctionAbstractCapabilityInvolvement.function(afaci, target);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<AbstractFunction> getInvolvedAbstractFunctions();







	/**
   * Returns the value of the '<em><b>Involved Functional Chains</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalChain}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involved Functional Chains</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involved Functional Chains</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapability_InvolvedFunctionalChains()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='AbstractCapability.involvedInvolvements(self, fcaci);\r\nFunctionalChainAbstractCapabilityInvolvement.functionalChain(fcaci, target);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<FunctionalChain> getInvolvedFunctionalChains();





} // AbstractCapability

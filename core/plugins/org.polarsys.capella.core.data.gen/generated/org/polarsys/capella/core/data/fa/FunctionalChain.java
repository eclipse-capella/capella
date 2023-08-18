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
package org.polarsys.capella.core.data.fa;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.la.CapabilityRealization;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Functional Chain</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChain#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChain#getOwnedFunctionalChainInvolvements <em>Owned Functional Chain Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChain#getOwnedFunctionalChainRealizations <em>Owned Functional Chain Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvedFunctionalChainInvolvements <em>Involved Functional Chain Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvedFunctions <em>Involved Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvedFunctionalExchanges <em>Involved Functional Exchanges</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvedElements <em>Involved Elements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChain#getEnactedFunctions <em>Enacted Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChain#getEnactedFunctionalBlocks <em>Enacted Functional Blocks</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChain#getAvailableInStates <em>Available In States</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChain#getFirstFunctionalChainInvolvements <em>First Functional Chain Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvingCapabilities <em>Involving Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvingCapabilityRealizations <em>Involving Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChain#getRealizedFunctionalChains <em>Realized Functional Chains</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChain#getRealizingFunctionalChains <em>Realizing Functional Chains</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChain#getPreCondition <em>Pre Condition</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChain#getPostCondition <em>Post Condition</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChain#getOwnedSequenceNodes <em>Owned Sequence Nodes</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChain#getOwnedSequenceLinks <em>Owned Sequence Links</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='FunctionalChain'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='StructuredActivityNode' stereotype='eng.FunctionalChain'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A functional chain is a set of Functions, activated through an activation graph (or path) and carrying non functional properties such as latency, criticity level ... \r\nIt provides a high-level description of a contribution of the system, users or external entities to an operational capability.' usage\040guideline='a functional chain is used highlight a specific path in the function flow, that is of particular interest in the context of the targeted application (performance constraint, safety path, ...)\r\n[source: Capella study]' used\040in\040levels='operational,system,logical,physical' usage\040examples='../img/usage_examples/example_functional_chain.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Package' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface FunctionalChain extends NamedElement, InvolverElement, InvolvedElement {





	/**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.fa.FunctionalChainKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.fa.FunctionalChainKind
   * @see #setKind(FunctionalChainKind)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain_Kind()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Defines the kind of this FunctionalChain' constraints='none' type='refer to FunctionalChainKind definition' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	FunctionalChainKind getKind();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getKind <em>Kind</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.fa.FunctionalChainKind
   * @see #getKind()
   * @generated
   */

	void setKind(FunctionalChainKind value);







	/**
   * Returns the value of the '<em><b>Owned Functional Chain Involvements</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Functional Chain Involvements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Functional Chain Involvements</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain_OwnedFunctionalChainInvolvements()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of involvement relationships owned by this functional chain' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::NamedElement::clientDependency::keyword::specific' explanation='Elements are contained in the nearest possible parent container.' constraints='uml::NamedElement::clientDependency elements on which FunctionalChain stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<FunctionalChainInvolvement> getOwnedFunctionalChainInvolvements();







	/**
   * Returns the value of the '<em><b>Owned Functional Chain Realizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalChainRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Functional Chain Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Functional Chain Realizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain_OwnedFunctionalChainRealizations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of realization relationships owned by this functional chain' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::NamedElement::clientDependency::keyword::specific' explanation='Elements are contained in the nearest possible parent container.' constraints='uml::NamedElement::clientDependency elements on which FunctionalChainInvolvement stereotype or any stereotype that inherits from it is applied'"
   * @generated
   */

	EList<FunctionalChainRealization> getOwnedFunctionalChainRealizations();







	/**
   * Returns the value of the '<em><b>Involved Functional Chain Involvements</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involved Functional Chain Involvements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involved Functional Chain Involvements</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain_InvolvedFunctionalChainInvolvements()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedFunctionalChainInvolvements'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the list of involvement relationships included in this functional chain\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<FunctionalChainInvolvement> getInvolvedFunctionalChainInvolvements();







	/**
   * Returns the value of the '<em><b>Involved Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.AbstractFunction}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getInvolvingFunctionalChains <em>Involving Functional Chains</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involved Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involved Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain_InvolvedFunctions()
   * @see org.polarsys.capella.core.data.fa.AbstractFunction#getInvolvingFunctionalChains
   * @model opposite="involvingFunctionalChains" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='involvedElements'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the functions involved in this functional chain\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<AbstractFunction> getInvolvedFunctions();







	/**
   * Returns the value of the '<em><b>Involved Functional Exchanges</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalExchange}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.FunctionalExchange#getInvolvingFunctionalChains <em>Involving Functional Chains</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involved Functional Exchanges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involved Functional Exchanges</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain_InvolvedFunctionalExchanges()
   * @see org.polarsys.capella.core.data.fa.FunctionalExchange#getInvolvingFunctionalChains
   * @model opposite="involvingFunctionalChains" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='involvedElements'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the functional exchanges involved in this functional chain\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<FunctionalExchange> getInvolvedFunctionalExchanges();







	/**
   * Returns the value of the '<em><b>Involved Elements</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.InvolvedElement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involved Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involved Elements</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain_InvolvedElements()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='involvedFunctionalChainInvolvements.involved'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the list of model elements involved in this functional chain\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<InvolvedElement> getInvolvedElements();







	/**
   * Returns the value of the '<em><b>Enacted Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.AbstractFunction}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enacted Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Enacted Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain_EnactedFunctions()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='involvedFunctions'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the functions involved in this functional chain\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<AbstractFunction> getEnactedFunctions();







	/**
   * Returns the value of the '<em><b>Enacted Functional Blocks</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enacted Functional Blocks</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Enacted Functional Blocks</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain_EnactedFunctionalBlocks()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='enactedFunctions.allocationBlocks'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the functional blocks involved in this functional chain\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<AbstractFunctionalBlock> getEnactedFunctionalBlocks();







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
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain_AvailableInStates()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of (system) states in which this functional chain is actually available\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<State> getAvailableInStates();







	/**
   * Returns the value of the '<em><b>First Functional Chain Involvements</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>First Functional Chain Involvements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>First Functional Chain Involvements</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain_FirstFunctionalChainInvolvements()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='freeform' viatra.expression='pattern FunctionalChain__firstFunctionalChainInvolvements(self : FunctionalChain, target : FunctionalChainInvolvement) {\r\n\tFunctionalChain.ownedFunctionalChainInvolvements(self, target);\r\n\tFunctionalChainInvolvement.involved(target, _);\r\n\tneg find _PreviousInvolvement(target, _);\r\n}\r\nprivate pattern _PreviousInvolvement(fci : FunctionalChainInvolvement, previous : FunctionalChainInvolvement) {\r\n\tFunctionalChainInvolvement.previousFunctionalChainInvolvements(fci, previous);\r\n}\r\n'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<FunctionalChainInvolvement> getFirstFunctionalChainInvolvements();







	/**
   * Returns the value of the '<em><b>Involving Capabilities</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.Capability}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involving Capabilities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involving Capabilities</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain_InvolvingCapabilities()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='FunctionalChain.involvingInvolvements(self, fcaci);\r\nFunctionalChainAbstractCapabilityInvolvement.capability(fcaci, target);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<Capability> getInvolvingCapabilities();







	/**
   * Returns the value of the '<em><b>Involving Capability Realizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.CapabilityRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involving Capability Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involving Capability Realizations</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain_InvolvingCapabilityRealizations()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='FunctionalChain.involvingInvolvements(self, fcaci);\r\nFunctionalChainAbstractCapabilityInvolvement.capability(fcaci, target);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<CapabilityRealization> getInvolvingCapabilityRealizations();







	/**
   * Returns the value of the '<em><b>Realized Functional Chains</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalChain}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Functional Chains</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Functional Chains</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain_RealizedFunctionalChains()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='FunctionalChainRealization.sourceElement(fcr, self);\r\nFunctionalChainRealization.targetElement(fcr, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<FunctionalChain> getRealizedFunctionalChains();







	/**
   * Returns the value of the '<em><b>Realizing Functional Chains</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalChain}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Functional Chains</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Functional Chains</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain_RealizingFunctionalChains()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='FunctionalChainRealization.targetElement(fcr, self);\r\nFunctionalChainRealization.sourceElement(fcr, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<FunctionalChain> getRealizingFunctionalChains();




	/**
   * Returns the value of the '<em><b>Pre Condition</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pre Condition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Pre Condition</em>' reference.
   * @see #setPreCondition(Constraint)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain_PreCondition()
   * @model annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	Constraint getPreCondition();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getPreCondition <em>Pre Condition</em>}' reference.

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
	 * If the meaning of the '<em>Post Condition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Post Condition</em>' reference.
   * @see #setPostCondition(Constraint)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain_PostCondition()
   * @model annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	Constraint getPostCondition();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getPostCondition <em>Post Condition</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Post Condition</em>' reference.
   * @see #getPostCondition()
   * @generated
   */

	void setPostCondition(Constraint value);




	/**
   * Returns the value of the '<em><b>Owned Sequence Nodes</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ControlNode}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Sequence Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Sequence Nodes</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain_OwnedSequenceNodes()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ControlNode> getOwnedSequenceNodes();




	/**
   * Returns the value of the '<em><b>Owned Sequence Links</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.SequenceLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Sequence Links</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Sequence Links</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChain_OwnedSequenceLinks()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<SequenceLink> getOwnedSequenceLinks();





} // FunctionalChain

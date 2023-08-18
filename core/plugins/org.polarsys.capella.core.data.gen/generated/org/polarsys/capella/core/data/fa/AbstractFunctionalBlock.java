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
import org.polarsys.capella.core.data.capellacore.ModellingBlock;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Functional Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getOwnedFunctionalAllocation <em>Owned Functional Allocation</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getOwnedComponentExchanges <em>Owned Component Exchanges</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getOwnedComponentExchangeCategories <em>Owned Component Exchange Categories</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getFunctionalAllocations <em>Functional Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getAllocatedFunctions <em>Allocated Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getInExchangeLinks <em>In Exchange Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getOutExchangeLinks <em>Out Exchange Links</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunctionalBlock()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a specialization of a generic modelling block, with added ability to hold allocation links to functions\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 * @generated
 */
public interface AbstractFunctionalBlock extends ModellingBlock {





	/**
   * Returns the value of the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Functional Allocation</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Functional Allocation</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunctionalBlock_OwnedFunctionalAllocation()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='allocation relationships between Functions and Blocks, that are owned by this Block\r\n[source: Capella study]\r\n' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='none' constraints='Some elements on which ComponentFunctionalAllocation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<ComponentFunctionalAllocation> getOwnedFunctionalAllocation();







	/**
   * Returns the value of the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentExchange}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Component Exchanges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Component Exchanges</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunctionalBlock_OwnedComponentExchanges()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='ownedConnector' featureOwner='StructuredClassifier'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the connections associated with this block\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='none' constraints='In uml::Element::nearestPackage, exchanges between two elements contained by this block. Thoses exchanges are packaged elements on which ComponentExchange stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ComponentExchange> getOwnedComponentExchanges();







	/**
   * Returns the value of the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentExchangeCategory}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Component Exchange Categories</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Component Exchange Categories</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunctionalBlock_OwnedComponentExchangeCategories()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ComponentExchangeCategory> getOwnedComponentExchangeCategories();







	/**
   * Returns the value of the '<em><b>Functional Allocations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation#getBlock <em>Block</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Functional Allocations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Functional Allocations</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunctionalBlock_FunctionalAllocations()
   * @see org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation#getBlock
   * @model opposite="block" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='outgoingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the allocation links between this block, and the functions that are allocated to it.\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ComponentFunctionalAllocation> getFunctionalAllocations();







	/**
   * Returns the value of the '<em><b>Allocated Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.AbstractFunction}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getAllocationBlocks <em>Allocation Blocks</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunctionalBlock_AllocatedFunctions()
   * @see org.polarsys.capella.core.data.fa.AbstractFunction#getAllocationBlocks
   * @model opposite="allocationBlocks" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='functionalAllocations.function'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of functions allocated to this block\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<AbstractFunction> getAllocatedFunctions();







	/**
   * Returns the value of the '<em><b>In Exchange Links</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ExchangeLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Exchange Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>In Exchange Links</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunctionalBlock_InExchangeLinks()
   * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='inFunctionalLinks'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the (functional) exchanges that have this block as their target/destination\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<ExchangeLink> getInExchangeLinks();







	/**
   * Returns the value of the '<em><b>Out Exchange Links</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ExchangeLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Exchange Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Out Exchange Links</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunctionalBlock_OutExchangeLinks()
   * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='outFunctionalLinks'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the (functional) exchanges that have this block as their source\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<ExchangeLink> getOutExchangeLinks();





} // AbstractFunctionalBlock

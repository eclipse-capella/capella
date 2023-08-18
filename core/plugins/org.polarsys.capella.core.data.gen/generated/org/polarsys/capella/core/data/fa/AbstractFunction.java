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
import org.polarsys.capella.common.data.activity.CallBehaviorAction;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.la.CapabilityRealization;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunction#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunction#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunction#getOwnedFunctions <em>Owned Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunction#getOwnedFunctionRealizations <em>Owned Function Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunction#getOwnedFunctionalExchanges <em>Owned Functional Exchanges</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunction#getSubFunctions <em>Sub Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunction#getOutFunctionRealizations <em>Out Function Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunction#getInFunctionRealizations <em>In Function Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunction#getComponentFunctionalAllocations <em>Component Functional Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunction#getAllocationBlocks <em>Allocation Blocks</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunction#getAvailableInStates <em>Available In States</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunction#getInvolvingCapabilities <em>Involving Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunction#getInvolvingCapabilityRealizations <em>Involving Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunction#getInvolvingFunctionalChains <em>Involving Functional Chains</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunction#getLinkedStateMachine <em>Linked State Machine</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunction#getLinkedFunctionSpecification <em>Linked Function Specification</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunction()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Action'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='OpaqueAction' stereotype='eng.Action'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies an operation or an action that is performed by an entity.\r\n\r\nA transformation of inputs to outputs that may include the creation, monitoring, modification or destruction of elements, or a null transformation.\r\n[source: SysML glossary for SysML v1.0]\r\n\r\nThis is an abstract base class for the derivation of specific function types at each design level\r\n[source: Capella study]' usage\040guideline='n/a (Abstract)' used\040in\040levels='operational,system,logical,physical' arcadia_description='A function is an action, an operation or a service fulfilled by the system or by an actor when interacting with the system. Example: tune radio frequency, display radio name...' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Activity' constraints='none'"
 * @generated
 */
public interface AbstractFunction extends Namespace, InvolvedElement, AbstractInstance, AbstractFunctionalChainContainer, CallBehaviorAction, AbstractEvent {





	/**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.fa.FunctionKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.fa.FunctionKind
   * @see #setKind(FunctionKind)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunction_Kind()
   * @model annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	FunctionKind getKind();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getKind <em>Kind</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.fa.FunctionKind
   * @see #getKind()
   * @generated
   */

	void setKind(FunctionKind value);







	/**
   * Returns the value of the '<em><b>Condition</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Condition</em>' attribute.
   * @see #setCondition(String)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunction_Condition()
   * @model annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getCondition();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getCondition <em>Condition</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' attribute.
   * @see #getCondition()
   * @generated
   */

	void setCondition(String value);







	/**
   * Returns the value of the '<em><b>Owned Functions</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.AbstractFunction}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Functions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Functions</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunction_OwnedFunctions()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the functions that are owned (in terms of model structure) by this function\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='the nesting relation is not representing the hierarchy of functions, but helps storing the functions in a structured way' constraints='none'"
   * @generated
   */

	EList<AbstractFunction> getOwnedFunctions();







	/**
   * Returns the value of the '<em><b>Owned Function Realizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Function Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Function Realizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunction_OwnedFunctionRealizations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the function realisation links that are associated to this function\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='Elements are contained in the nearest possible parent container.' constraints='Some elements on which FunctionRealization stereotype or any stereotype that inherits from it is applied'"
   * @generated
   */

	EList<FunctionRealization> getOwnedFunctionRealizations();







	/**
   * Returns the value of the '<em><b>Owned Functional Exchanges</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalExchange}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Functional Exchanges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Functional Exchanges</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunction_OwnedFunctionalExchanges()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the functional exchanges that are owned by this function, e.g. that have their source and destination on sub-functions of this function.\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Activity::edge' explanation='Elements are contained in the nearest possible parent container.' constraints='uml::Activity::edge elements on which FunctionalExchange stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<FunctionalExchange> getOwnedFunctionalExchanges();







	/**
   * Returns the value of the '<em><b>Sub Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.AbstractFunction}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Sub Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunction_SubFunctions()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='freeform' viatra.expression='pattern AbstractFunction__subFunctions(self : AbstractFunction, target : AbstractFunction) {\r\n\t// sub function directly in function\r\n\tAbstractFunction.ownedFunctions(self, target);\r\n} or { // sub function in function first level package\r\n\tfind _AbstractFunction__ownedFunctionPkgs(self, pkg);\r\n\tfind _FunctionPkg__ownedFunctions(pkg, target);\r\n}\r\nor { // sub function in function first level package sub packages\r\n\tfind _AbstractFunction__ownedFunctionPkgs(self, pkg);\r\n\tfind _FunctionPkg__ownedFunctionPkgs+(pkg, subpkg);\r\n\tfind _FunctionPkg__ownedFunctions(subpkg, target);\r\n}\r\n\r\nprivate pattern _AbstractFunction__ownedFunctionPkgs(af : AbstractFunction, ownedpkg : FunctionPkg) {\r\n\tOperationalActivity.ownedOperationalActivityPkgs(af, ownedpkg);\r\n} or {\r\n\tSystemFunction.ownedSystemFunctionPkgs(af, ownedpkg);\r\n} or {\r\n\tLogicalFunction.ownedLogicalFunctionPkgs(af, ownedpkg);\r\n} or {\r\n\tPhysicalFunction.ownedPhysicalFunctionPkgs(af, ownedpkg);\r\n}\r\n\r\nprivate pattern _FunctionPkg__ownedFunctionPkgs(pkg : FunctionPkg, ownedpkg : FunctionPkg) {\r\n\tOperationalActivityPkg.ownedOperationalActivityPkgs(pkg, ownedpkg);\r\n} or {\r\n\tSystemFunctionPkg.ownedSystemFunctionPkgs(pkg, ownedpkg);\r\n} or {\r\n\tLogicalFunctionPkg.ownedLogicalFunctionPkgs(pkg, ownedpkg);\r\n} or {\r\n\tPhysicalFunctionPkg.ownedPhysicalFunctionPkgs(pkg, ownedpkg);\r\n}\r\n\r\nprivate pattern _FunctionPkg__ownedFunctions(pkg : FunctionPkg, af : AbstractFunction) {\r\n\tOperationalActivityPkg.ownedOperationalActivities(pkg, af);\r\n} or {\r\n\tSystemFunctionPkg.ownedSystemFunctions(pkg, af);\r\n} or {\r\n\tLogicalFunctionPkg.ownedLogicalFunctions(pkg, af);\r\n} or {\r\n\tPhysicalFunctionPkg.ownedPhysicalFunctions(pkg, af);\r\n}\r\n'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the children functions of this function\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<AbstractFunction> getSubFunctions();







	/**
   * Returns the value of the '<em><b>Out Function Realizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionRealization}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.FunctionRealization#getAllocatingFunction <em>Allocating Function</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Function Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Out Function Realizations</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunction_OutFunctionRealizations()
   * @see org.polarsys.capella.core.data.fa.FunctionRealization#getAllocatingFunction
   * @model opposite="allocatingFunction" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='outgoingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='function realization links that have this function as their origin\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<FunctionRealization> getOutFunctionRealizations();







	/**
   * Returns the value of the '<em><b>In Function Realizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionRealization}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.FunctionRealization#getAllocatedFunction <em>Allocated Function</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Function Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>In Function Realizations</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunction_InFunctionRealizations()
   * @see org.polarsys.capella.core.data.fa.FunctionRealization#getAllocatedFunction
   * @model opposite="allocatedFunction" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='incomingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the function realisation links that have this function as their destination\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<FunctionRealization> getInFunctionRealizations();







	/**
   * Returns the value of the '<em><b>Component Functional Allocations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation#getFunction <em>Function</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Functional Allocations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Component Functional Allocations</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunction_ComponentFunctionalAllocations()
   * @see org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation#getFunction
   * @model opposite="function" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='incomingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the mediator classes that implement the allocation of this function to/from components (blocks)\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ComponentFunctionalAllocation> getComponentFunctionalAllocations();







	/**
   * Returns the value of the '<em><b>Allocation Blocks</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getAllocatedFunctions <em>Allocated Functions</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocation Blocks</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocation Blocks</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunction_AllocationBlocks()
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getAllocatedFunctions
   * @model opposite="allocatedFunctions" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='componentFunctionalAllocations.block'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the blocks to/from which this function is allocated\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<AbstractFunctionalBlock> getAllocationBlocks();







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
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunction_AvailableInStates()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of (system) states in which this function is actually available\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<State> getAvailableInStates();







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
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunction_InvolvingCapabilities()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='AbstractFunction.involvingInvolvements(self, afaci);\r\nAbstractFunctionAbstractCapabilityInvolvement.capability(afaci, target);'"
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
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunction_InvolvingCapabilityRealizations()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='AbstractFunction.involvingInvolvements(self, afaci);\r\nAbstractFunctionAbstractCapabilityInvolvement.capability(afaci, target);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<CapabilityRealization> getInvolvingCapabilityRealizations();







	/**
   * Returns the value of the '<em><b>Involving Functional Chains</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalChain}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvedFunctions <em>Involved Functions</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involving Functional Chains</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involving Functional Chains</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunction_InvolvingFunctionalChains()
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvedFunctions
   * @model opposite="involvedFunctions" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='AbstractFunction.involvingInvolvements(self, fci);\r\nFunctionalChainInvolvement.involver(fci, target);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the functional chains that involve this function\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<FunctionalChain> getInvolvingFunctionalChains();







	/**
   * Returns the value of the '<em><b>Linked State Machine</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Linked State Machine</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Linked State Machine</em>' reference.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunction_LinkedStateMachine()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='linkedFunction'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='behavior'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the state machine associated to this function\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	StateMachine getLinkedStateMachine();







	/**
   * Returns the value of the '<em><b>Linked Function Specification</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Linked Function Specification</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Linked Function Specification</em>' reference.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunction_LinkedFunctionSpecification()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='linkedFunctionSpecification'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='behavior'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the function specification with which this function complies\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	FunctionSpecification getLinkedFunctionSpecification();





} // AbstractFunction

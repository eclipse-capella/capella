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
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.Port;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Exchange</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchange#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchange#isOriented <em>Oriented</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchange#getAllocatedFunctionalExchanges <em>Allocated Functional Exchanges</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchange#getIncomingComponentExchangeRealizations <em>Incoming Component Exchange Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchange#getOutgoingComponentExchangeRealizations <em>Outgoing Component Exchange Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchange#getOutgoingComponentExchangeFunctionalExchangeAllocations <em>Outgoing Component Exchange Functional Exchange Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchange#getOwnedComponentExchangeFunctionalExchangeAllocations <em>Owned Component Exchange Functional Exchange Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchange#getOwnedComponentExchangeRealizations <em>Owned Component Exchange Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchange#getOwnedComponentExchangeEnds <em>Owned Component Exchange Ends</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchange#getSourcePort <em>Source Port</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchange#getSourcePart <em>Source Part</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchange#getTargetPort <em>Target Port</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchange#getTargetPart <em>Target Part</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchange#getCategories <em>Categories</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchange#getAllocatorPhysicalLinks <em>Allocator Physical Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchange#getRealizedComponentExchanges <em>Realized Component Exchanges</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchange#getRealizingComponentExchanges <em>Realizing Component Exchanges</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchange()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a specialized version of an exchange specification, dedicated to characterize exchanges between components\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none' arcadia_description='An Exchange is an interaction between some entities such as actors, the system, functions or components, which is likely to influence their behaviour. Example: tuning frequency, radio selection command...'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::InformationFlow' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface ComponentExchange extends AbstractEvent, AbstractEventOperation, NamedElement, ExchangeSpecification {





	/**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.fa.ComponentExchangeKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeKind
   * @see #setKind(ComponentExchangeKind)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchange_Kind()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Kind of the connection' constraints='none' type='refer to ConnectionKind definition' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ComponentExchangeKind getKind();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getKind <em>Kind</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeKind
   * @see #getKind()
   * @generated
   */

	void setKind(ComponentExchangeKind value);







	/**
   * Returns the value of the '<em><b>Oriented</b></em>' attribute.
   * The default value is <code>"false"</code>.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Oriented</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Oriented</em>' attribute.
   * @see #setOriented(boolean)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchange_Oriented()
   * @model default="false"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='describes the orientation of the connection. The connection can be oriented or not' constraints='none' type='refer to OrientationConnectionKind definition' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isOriented();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.ComponentExchange#isOriented <em>Oriented</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Oriented</em>' attribute.
   * @see #isOriented()
   * @generated
   */

	void setOriented(boolean value);







	/**
   * Returns the value of the '<em><b>Allocated Functional Exchanges</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalExchange}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.FunctionalExchange#getAllocatingComponentExchanges <em>Allocating Component Exchanges</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Functional Exchanges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated Functional Exchanges</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchange_AllocatedFunctionalExchanges()
   * @see org.polarsys.capella.core.data.fa.FunctionalExchange#getAllocatingComponentExchanges
   * @model opposite="allocatingComponentExchanges" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='outgoingComponentExchangeFunctionalExchangeAllocations.allocatedFunctionalExchange'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the functional exchanges associated with this component exchange (e.g. the functional exchanges that happen between functions allocated to the two components involved in this component exchange)\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<FunctionalExchange> getAllocatedFunctionalExchanges();







	/**
   * Returns the value of the '<em><b>Incoming Component Exchange Realizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentExchangeRealization}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ComponentExchangeRealization#getAllocatedComponentExchange <em>Allocated Component Exchange</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Component Exchange Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Incoming Component Exchange Realizations</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchange_IncomingComponentExchangeRealizations()
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeRealization#getAllocatedComponentExchange
   * @model opposite="allocatedComponentExchange" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='incomingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the component exchange realization links that have this component exchange as their destination\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ComponentExchangeRealization> getIncomingComponentExchangeRealizations();







	/**
   * Returns the value of the '<em><b>Outgoing Component Exchange Realizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentExchangeRealization}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ComponentExchangeRealization#getAllocatingComponentExchange <em>Allocating Component Exchange</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Component Exchange Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Outgoing Component Exchange Realizations</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchange_OutgoingComponentExchangeRealizations()
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeRealization#getAllocatingComponentExchange
   * @model opposite="allocatingComponentExchange" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='outgoingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the component exchange realization links that have this component exchange as their source\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ComponentExchangeRealization> getOutgoingComponentExchangeRealizations();







	/**
   * Returns the value of the '<em><b>Outgoing Component Exchange Functional Exchange Allocations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation#getAllocatingComponentExchange <em>Allocating Component Exchange</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Component Exchange Functional Exchange Allocations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Outgoing Component Exchange Functional Exchange Allocations</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchange_OutgoingComponentExchangeFunctionalExchangeAllocations()
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation#getAllocatingComponentExchange
   * @model opposite="allocatingComponentExchange" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='outgoingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the allocation links between functional exchanges and component exchanges, for which this component exchange is the source\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ComponentExchangeFunctionalExchangeAllocation> getOutgoingComponentExchangeFunctionalExchangeAllocations();







	/**
   * Returns the value of the '<em><b>Owned Component Exchange Functional Exchange Allocations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Component Exchange Functional Exchange Allocations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Component Exchange Functional Exchange Allocations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchange_OwnedComponentExchangeFunctionalExchangeAllocations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the allocation links between functional exchanges and component exchanges, owned by this component exchange\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='Elements are contained in the nearest possible parent container.' constraints='Some elements on which ComponentFunctionalExchangeAllocation stereotype or any stereotype that inherits from it is applied'"
   * @generated
   */

	EList<ComponentExchangeFunctionalExchangeAllocation> getOwnedComponentExchangeFunctionalExchangeAllocations();







	/**
   * Returns the value of the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentExchangeRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Component Exchange Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Component Exchange Realizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchange_OwnedComponentExchangeRealizations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the component exchange realization links that are owned by this component exchange\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='Elements are contained in the nearest possible parent container.' constraints='Some elements on which ComponentExchangeRealization stereotype or any stereotype that inherits from it is applied'"
   * @generated
   */

	EList<ComponentExchangeRealization> getOwnedComponentExchangeRealizations();







	/**
   * Returns the value of the '<em><b>Owned Component Exchange Ends</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentExchangeEnd}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Component Exchange Ends</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Component Exchange Ends</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchange_OwnedComponentExchangeEnds()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='end' featureOwner='Connector'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedConnectionEnds'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the connection endpoints involved in this link (potentially, an arbitrary number of them can be present)\r\n[source: Capella study]\r\n\r\nA connector consists of at least two connector ends, each representing the participation of instances of the classifiers\r\ntyping the connectable elements attached to this end. The set of connector ends is ordered.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Connector::end' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ComponentExchangeEnd> getOwnedComponentExchangeEnds();







	/**
   * Returns the value of the '<em><b>Source Port</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Port</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Source Port</em>' reference.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchange_SourcePort()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='ComponentExchange.source(self, target);\r\n} or {\r\n\tComponentExchange.source(self, cee);\r\n\tComponentExchangeEnd.port(cee, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	Port getSourcePort();







	/**
   * Returns the value of the '<em><b>Source Part</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Part</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Source Part</em>' reference.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchange_SourcePart()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='ComponentExchange.source(self, target);\r\n} or {\r\n\tComponentExchange.source(self, cee);\r\n\tComponentExchangeEnd.part(cee, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	Part getSourcePart();







	/**
   * Returns the value of the '<em><b>Target Port</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Port</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Target Port</em>' reference.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchange_TargetPort()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='ComponentExchange.target(self, target);\r\n} or {\r\n\tComponentExchange.target(self, cee);\r\n\tComponentExchangeEnd.port(cee, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	Port getTargetPort();







	/**
   * Returns the value of the '<em><b>Target Part</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Part</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Target Part</em>' reference.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchange_TargetPart()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='ComponentExchange.target(self, target);\r\n} or {\r\n\tComponentExchange.target(self, cee);\r\n\tComponentExchangeEnd.part(cee, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	Part getTargetPart();







	/**
   * Returns the value of the '<em><b>Categories</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentExchangeCategory}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Categories</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Categories</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchange_Categories()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='exchanges'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the exchange categories (families) to which this functional exchange belongs\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ComponentExchangeCategory> getCategories();







	/**
   * Returns the value of the '<em><b>Allocator Physical Links</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.PhysicalLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocator Physical Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocator Physical Links</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchange_AllocatorPhysicalLinks()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='ComponentExchange.incomingTraces(self, cea);\r\nComponentExchangeAllocation.componentExchangeAllocator(cea, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<PhysicalLink> getAllocatorPhysicalLinks();







	/**
   * Returns the value of the '<em><b>Realized Component Exchanges</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentExchange}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getRealizingComponentExchanges <em>Realizing Component Exchanges</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Component Exchanges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Component Exchanges</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchange_RealizedComponentExchanges()
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#getRealizingComponentExchanges
   * @model opposite="realizingComponentExchanges" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='ComponentExchange.outgoingTraces(self, cer);\r\nComponentExchangeRealization.allocatedComponentExchange(cer, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ComponentExchange> getRealizedComponentExchanges();







	/**
   * Returns the value of the '<em><b>Realizing Component Exchanges</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentExchange}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getRealizedComponentExchanges <em>Realized Component Exchanges</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Component Exchanges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Component Exchanges</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchange_RealizingComponentExchanges()
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#getRealizedComponentExchanges
   * @model opposite="realizedComponentExchanges" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='ComponentExchange.incomingTraces(self, cer);\r\nComponentExchangeRealization.allocatingComponentExchange(cer, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ComponentExchange> getRealizingComponentExchanges();





} // ComponentExchange

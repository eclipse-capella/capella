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
package org.polarsys.capella.common.data.modellingcore;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Traceable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.TraceableElement#getIncomingTraces <em>Incoming Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.TraceableElement#getOutgoingTraces <em>Outgoing Traces</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getTraceableElement()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='TraceableElement'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Element' stereotype='eng.TraceableElement'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Base abstract class supporting the ability to trace a model element to/from other elements\r\n[source: Capella study]' usage\040guideline='n/a (Abstract)' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::NamedElement' constraints=''"
 * @generated
 */
public interface TraceableElement extends ModelElement {





	/**
   * Returns the value of the '<em><b>Incoming Traces</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.modellingcore.AbstractTrace}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Traces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Incoming Traces</em>' reference list.
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getTraceableElement_IncomingTraces()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='incomingTraces'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='targetElement'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of trace relationships pointing towards this element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and Transient' constraints='none'"
   * @generated
   */

	EList<AbstractTrace> getIncomingTraces();







	/**
   * Returns the value of the '<em><b>Outgoing Traces</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.modellingcore.AbstractTrace}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Traces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Outgoing Traces</em>' reference list.
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getTraceableElement_OutgoingTraces()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping umlOppositeReference='supplier' umlOppositeReferenceOwner='Dependency'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='outgoingTraces'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='sourceElement'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of trace relationships starting from this element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and Transient' constraints='none'"
   * @generated
   */

	EList<AbstractTrace> getOutgoingTraces();





} // TraceableElement

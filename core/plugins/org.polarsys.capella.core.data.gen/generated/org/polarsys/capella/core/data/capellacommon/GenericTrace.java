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
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacore.KeyValue;
import org.polarsys.capella.core.data.capellacore.Trace;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generic Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.GenericTrace#getKeyValuePairs <em>Key Value Pairs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.GenericTrace#getSource <em>Source</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.GenericTrace#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getGenericTrace()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='GenericTrace'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Dependency' stereotype='eng.GenericTrace'"
 *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a Trace relationship (in the UML sense) to which can be associated a set of key/value pairs characterizing the trace.\r\n[source: Capella study]' usage\040guideline='n/a (Abstract)' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='coud be uml::Dependency, but left empty so that this element is not actually available/transformed for the end user.\r\nThis is a feature of Capella that is not available in Capella/MAX anyway.' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface GenericTrace extends Trace {





	/**
   * Returns the value of the '<em><b>Key Value Pairs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.KeyValue}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key Value Pairs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Key Value Pairs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getGenericTrace_KeyValuePairs()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='ownedComment' featureOwner='Element'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='keyValuePairs'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of key/value pairs that characterize this trace relationship\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Element::ownedComment' explanation='none' constraints='uml::Element::ownedComment elements on which KeyValue stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<KeyValue> getKeyValuePairs();







	/**
   * Returns the value of the '<em><b>Source</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Source</em>' reference.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getGenericTrace_Source()
   * @model resolveProxies="false" required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='sourceElement'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	TraceableElement getSource();







	/**
   * Returns the value of the '<em><b>Target</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' reference.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getGenericTrace_Target()
   * @model resolveProxies="false" required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='targetElement'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	TraceableElement getTarget();





} // GenericTrace

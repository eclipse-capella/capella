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
package org.polarsys.capella.core.data.oa;

import org.polarsys.capella.core.data.capellacore.NamedRelationship;
import org.polarsys.capella.core.data.fa.ComponentExchange;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Communication Mean</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.CommunicationMean#getSourceEntity <em>Source Entity</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.CommunicationMean#getTargetEntity <em>Target Entity</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.oa.OaPackage#getCommunicationMean()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the mean by which two specific operational entities are able to exchange information\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='not explicitly mapped to uml::InformationFlow, since its parent (ComponentExchange) is concrete and already mapped to uml::InformationFlow\r\n' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface CommunicationMean extends NamedRelationship, ComponentExchange {





	/**
   * Returns the value of the '<em><b>Source Entity</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Entity</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Source Entity</em>' reference.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getCommunicationMean_SourceEntity()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='source'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	Entity getSourceEntity();







	/**
   * Returns the value of the '<em><b>Target Entity</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Entity</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Target Entity</em>' reference.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getCommunicationMean_TargetEntity()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='target'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	Entity getTargetEntity();





} // CommunicationMean

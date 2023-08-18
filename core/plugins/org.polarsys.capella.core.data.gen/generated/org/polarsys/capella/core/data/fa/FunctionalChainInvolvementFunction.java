/**
 *
 *  Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     Thales - initial API and implementation
 */

package org.polarsys.capella.core.data.fa;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Functional Chain Involvement Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction#getOutgoingInvolvementLinks <em>Outgoing Involvement Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction#getIncomingInvolvementLinks <em>Incoming Involvement Links</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChainInvolvementFunction()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies the involvement of a model element in form of function in a functional chain\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */

public interface FunctionalChainInvolvementFunction extends FunctionalChainInvolvement, SequenceLinkEnd {





	/**
   * Returns the value of the '<em><b>Outgoing Involvement Links</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Involvement Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Outgoing Involvement Links</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChainInvolvementFunction_OutgoingInvolvementLinks()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='source'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<FunctionalChainInvolvementLink> getOutgoingInvolvementLinks();







	/**
   * Returns the value of the '<em><b>Incoming Involvement Links</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Involvement Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Incoming Involvement Links</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChainInvolvementFunction_IncomingInvolvementLinks()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='target'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<FunctionalChainInvolvementLink> getIncomingInvolvementLinks();





} // FunctionalChainInvolvementFunction

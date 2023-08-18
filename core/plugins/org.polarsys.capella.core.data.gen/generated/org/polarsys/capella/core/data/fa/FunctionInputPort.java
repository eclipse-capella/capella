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
import org.polarsys.capella.common.data.activity.InputPin;
import org.polarsys.capella.core.data.information.ExchangeItem;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Input Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionInputPort#getIncomingExchangeItems <em>Incoming Exchange Items</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionInputPort#getIncomingFunctionalExchanges <em>Incoming Functional Exchanges</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionInputPort()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='an input interface of its owning function, to receive functional exchanges from other functions\r\n[source: Capella study]' usage\040guideline='It is necessary to create a function input port on a function, to be able to set this function as the receiving end of a functional exchange. Note however that the Capella tool automatically creates a function input port on the destination function, when a functional exchange is created.\r\n[source: Capella study]' used\040in\040levels='system,logical,physical' usage\040examples='../img/usage_examples/ports_exchanges.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::ActivityParameterNode' explanation='use ActivityParameterNodes, delegation will add uml::InputPin on callBeahviorAction\r\n' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface FunctionInputPort extends FunctionPort, InputPin {





	/**
   * Returns the value of the '<em><b>Incoming Exchange Items</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.ExchangeItem}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Exchange Items</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Incoming Exchange Items</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionInputPort_IncomingExchangeItems()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the exchange items that are declared as potential flowing into this port\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ExchangeItem> getIncomingExchangeItems();







	/**
   * Returns the value of the '<em><b>Incoming Functional Exchanges</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalExchange}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Functional Exchanges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Incoming Functional Exchanges</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionInputPort_IncomingFunctionalExchanges()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='incoming'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<FunctionalExchange> getIncomingFunctionalExchanges();





} // FunctionInputPort

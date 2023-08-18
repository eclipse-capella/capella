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
package org.polarsys.capella.core.data.information;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Event Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.AbstractEventOperation#getInvokingSequenceMessages <em>Invoking Sequence Messages</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.InformationPackage#getAbstractEventOperation()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the element triggered by the reception of the event' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ReceiveOperationEvent::operation\r\numl::SentOperationEvent::operation' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 * @generated
 */
public interface AbstractEventOperation extends NamedElement {





	/**
   * Returns the value of the '<em><b>Invoking Sequence Messages</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.SequenceMessage}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Invoking Sequence Messages</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Invoking Sequence Messages</em>' reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getAbstractEventOperation_InvokingSequenceMessages()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='SequenceMessage.receivingEnd.event(target, ero);\r\n\tEventReceiptOperation.operation(ero, self);\r\n} or {\r\n\tSequenceMessage.sendingEnd.event(target, eso);\r\n\tEventSentOperation.operation(eso, self);'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<SequenceMessage> getInvokingSequenceMessages();





} // AbstractEventOperation

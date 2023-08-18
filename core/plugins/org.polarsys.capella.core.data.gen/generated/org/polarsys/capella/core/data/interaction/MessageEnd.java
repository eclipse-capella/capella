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
package org.polarsys.capella.core.data.interaction;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Message End</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.MessageEnd#getMessage <em>Message</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getMessageEnd()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='MessageEnd'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='MessageOccurrenceSpecification' stereotype='eng.MessageEnd'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies the occurrence of events, such as sending and receiving of signals or invoking or receiving of operation calls. A\r\nmessage occurrence specification is a kind of message end. Messages are generated either by synchronous operation calls\r\nor asynchronous signal sends. They are received by the execution of corresponding accept event actions.\r\n\r\nThis concept can be compared to UML MessageOccurrenceSpecification.\r\n[source:UML Superstructure v2.2] ' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='Should be renamed MessageOccurrenceSpecification to map UML concept' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::MessageOccurrenceSpecification' explanation='none' constraints='none'"
 * @generated
 */
public interface MessageEnd extends AbstractEnd {





	/**
   * Returns the value of the '<em><b>Message</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Message</em>' reference.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getMessageEnd_Message()
   * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='message' featureOwner='MessageEnd'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='message'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Message to which this MessageEnd is attached\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::MessageEnd::message' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='SequenceMessage.sendingEnd(target, self);\r\n} or {\r\n\tSequenceMessage.receivingEnd(target, self);'"
   * @generated
   */

	SequenceMessage getMessage();





} // MessageEnd

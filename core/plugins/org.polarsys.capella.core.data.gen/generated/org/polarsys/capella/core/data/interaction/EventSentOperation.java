/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.information.AbstractEventOperation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Sent Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.EventSentOperation#getOperation <em>Operation</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getEventSentOperation()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='EventSentOperation'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='SendOperationEvent' stereotype='eng.EventSentOperation'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='This concept is similar to UML SendOperationEvent : A SendOperationEvent models the invocation of an operation call.\r\n[source:UML Superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='Should be renamed SendOperationEvent to map UML concept' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::SendOperationEvent' explanation='none' constraints='none'"
 * @generated
 */
public interface EventSentOperation extends Event {





	/**
	 * Returns the value of the '<em><b>Operation</b></em>' reference.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' reference.
	 * @see #setOperation(AbstractEventOperation)
	 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getEventSentOperation_Operation()
	 * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='operation' featureOwner='SendOperationEvent'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='operation'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Operation triggering associated to the sending of this Event\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::SendOperationEvent::operation' explanation='none' constraints='none'"
	 * @generated
	 */

	AbstractEventOperation getOperation();




	/**
	 * Sets the value of the '{@link org.polarsys.capella.core.data.interaction.EventSentOperation#getOperation <em>Operation</em>}' reference.

	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' reference.
	 * @see #getOperation()
	 * @generated
	 */

	void setOperation(AbstractEventOperation value);





} // EventSentOperation

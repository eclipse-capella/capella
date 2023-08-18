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

package org.polarsys.capella.core.data.capellacommon;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Change Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.ChangeEvent#getKind <em>Kind</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getChangeEvent()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A change event occurs when a Boolean-valued expression becomes true. For example, as a result of a change in the value\r\nheld in a slot corresponding to an attribute, or a change in the value referenced by a link corresponding to an association.\r\nA change event is raised implicitly and is not the result of an explicit action\r\n[source: UML superstructure v2.4]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::ChangeEvent' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */

public interface ChangeEvent extends StateEvent {





	/**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.capellacommon.ChangeEventKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.capellacommon.ChangeEventKind
   * @see #setKind(ChangeEventKind)
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getChangeEvent_Kind()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies the type of the state ChangeEvent (see ChangeEventKind)\r\n[source: Capella study]' constraints='none' type='refer to ChangeEventKind definition' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ChangeEventKind getKind();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacommon.ChangeEvent#getKind <em>Kind</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.capellacommon.ChangeEventKind
   * @see #getKind()
   * @generated
   */

	void setKind(ChangeEventKind value);





} // ChangeEvent

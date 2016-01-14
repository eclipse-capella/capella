/**
 *
 *  Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     Thales - initial API and implementation
 */

package org.polarsys.capella.core.data.capellacommon;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Time Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.TimeEvent#getKind <em>Kind</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getTimeEvent()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A time event specifies a point in time by an expression. The expression might be absolute or might be relative to some\r\nother point in time.\r\n[source: UML superstructure v2.4]\r\n' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::TimeEvent' explanation='none' constraints='none'"
 * @generated
 */

public interface TimeEvent extends StateEvent {





	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link org.polarsys.capella.core.data.capellacommon.TimeEventKind}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see org.polarsys.capella.core.data.capellacommon.TimeEventKind
	 * @see #setKind(TimeEventKind)
	 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getTimeEvent_Kind()
	 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies the type of the state TimeEvent (see TimeEventKind)\r\n[source: Capella study]' constraints='none' type='refer to TimeEventKind definition' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::TimeEvent::isRelative' explanation='A relative time trigger is specified with the keyword \u2018after\u2019 followed by an expression that evaluates to a time value, such\r\nas \u201cafter (5 seconds).\u201d An absolute time trigger is specified with the keyword \u2018at\u2019 followed by an expression that\r\nevaluates to a time value, such as \u201cJan. 1, 2000, Noon.\"' constraints='none'"
	 * @generated
	 */

	TimeEventKind getKind();




	/**
	 * Sets the value of the '{@link org.polarsys.capella.core.data.capellacommon.TimeEvent#getKind <em>Kind</em>}' attribute.

	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see org.polarsys.capella.core.data.capellacommon.TimeEventKind
	 * @see #getKind()
	 * @generated
	 */

	void setKind(TimeEventKind value);





} // TimeEvent

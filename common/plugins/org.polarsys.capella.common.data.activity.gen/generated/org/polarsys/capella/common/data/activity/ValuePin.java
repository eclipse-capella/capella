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
package org.polarsys.capella.common.data.activity;

import org.polarsys.capella.common.data.modellingcore.ValueSpecification;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Value Pin</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.ValuePin#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.activity.ActivityPackage#getValuePin()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A value pin is an input pin that provides a value by evaluating a value specification\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='not used/implemented, as of Capella 1.0.3' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::ValuePin' constraints='none'"
 * @generated
 */
public interface ValuePin extends InputPin {





	/**
   * Returns the value of the '<em><b>Value</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' containment reference.
   * @see #setValue(ValueSpecification)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getValuePin_Value()
   * @model containment="true" resolveProxies="true" required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Value that the pin will provide\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ValuePin::value' explanation='' constraints='none'"
   * @generated
   */

	ValueSpecification getValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ValuePin#getValue <em>Value</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' containment reference.
   * @see #getValue()
   * @generated
   */

	void setValue(ValueSpecification value);





} // ValuePin

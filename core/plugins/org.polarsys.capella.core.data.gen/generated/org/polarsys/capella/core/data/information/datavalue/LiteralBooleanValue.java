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
package org.polarsys.capella.core.data.information.datavalue;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Literal Boolean Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue#isValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getLiteralBooleanValue()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='LiteralBooleanValue'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='LiteralBoolean' stereotype='eng.LiteralBooleanValue'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A literal Boolean is a specification of a Boolean value\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::LiteralBoolean' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface LiteralBooleanValue extends AbstractBooleanValue {





	/**
   * Returns the value of the '<em><b>Value</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(boolean)
   * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getLiteralBooleanValue_Value()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='value' featureOwner='LiteralBoolean'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the value \"true\" or \"false\"\r\n[source: Capella study]' constraints='none' type='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::LiteralBoolean::value' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue#isValue <em>Value</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #isValue()
   * @generated
   */

	void setValue(boolean value);





} // LiteralBooleanValue

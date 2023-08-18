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

import org.polarsys.capella.core.data.information.Unit;
import org.polarsys.capella.core.data.information.datatype.NumericType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Numeric Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.NumericValue#getUnit <em>Unit</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.NumericValue#getNumericType <em>Numeric Type</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getNumericValue()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='NumericValue'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='ValueSpecification'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A value expressed as a number\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::LiteralSpecification' constraints='none'"
 * @generated
 */
public interface NumericValue extends DataValue {





	/**
   * Returns the value of the '<em><b>Unit</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unit</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Unit</em>' reference.
   * @see #setUnit(Unit)
   * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getNumericValue_Unit()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	Unit getUnit();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datavalue.NumericValue#getUnit <em>Unit</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unit</em>' reference.
   * @see #getUnit()
   * @generated
   */

	void setUnit(Unit value);







	/**
   * Returns the value of the '<em><b>Numeric Type</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Numeric Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Numeric Type</em>' reference.
   * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getNumericValue_NumericType()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='type' featureOwner='TypedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='type'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='abstractType'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the type of the data being valued\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic feature='abstractType' excludefrom='xmlpivot'"
   * @generated
   */

	NumericType getNumericType();





} // NumericValue

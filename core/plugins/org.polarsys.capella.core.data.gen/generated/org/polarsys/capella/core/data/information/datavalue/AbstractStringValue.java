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

import org.polarsys.capella.core.data.information.datatype.StringType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract String Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.AbstractStringValue#getStringType <em>String Type</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getAbstractStringValue()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='StringValue'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='ValueSpecification'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A value defined by an ordered set of characters\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::ValueSpecification' constraints='none'"
 * @generated
 */
public interface AbstractStringValue extends DataValue {





	/**
   * Returns the value of the '<em><b>String Type</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>String Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>String Type</em>' reference.
   * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getAbstractStringValue_StringType()
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

	StringType getStringType();





} // AbstractStringValue

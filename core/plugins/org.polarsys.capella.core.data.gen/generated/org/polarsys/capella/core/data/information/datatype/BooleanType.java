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
package org.polarsys.capella.core.data.information.datatype;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.BooleanType#getOwnedLiterals <em>Owned Literals</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.BooleanType#getOwnedDefaultValue <em>Owned Default Value</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getBooleanType()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='BooleanType'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='DataType' stereotype='eng.BooleanType'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A boolean is an instance of PrimitiveType. In the metamodel, Boolean defines an enumeration that denotes a logical\r\ncondition. Its enumeration literals are:\r\n- true - The Boolean condition is satisfied.\r\n- false - The Boolean condition is not satisfied\r\n[source: UML superstructure v2.2]\r\n' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::PrimitiveType' explanation='none' constraints='none'"
 * @generated
 */
public interface BooleanType extends DataType {





	/**
	 * Returns the value of the '<em><b>Owned Literals</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Literals</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Literals</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getBooleanType_OwnedLiterals()
	 * @model containment="true" upper="2"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='ownedLiteral' featureOwner='Enumeration'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='literals'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the literals that are contained in this enumeration\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='none'"
	 * @generated
	 */

	EList<LiteralBooleanValue> getOwnedLiterals();







	/**
	 * Returns the value of the '<em><b>Owned Default Value</b></em>' containment reference.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Default Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Default Value</em>' containment reference.
	 * @see #setOwnedDefaultValue(AbstractBooleanValue)
	 * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getBooleanType_OwnedDefaultValue()
	 * @model containment="true" resolveProxies="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='defaultValue'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='default value for this data type\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='Elements on which BooleanValue stereotype or any stereotype that inherits from it is applied'"
	 * @generated
	 */

	AbstractBooleanValue getOwnedDefaultValue();




	/**
	 * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.BooleanType#getOwnedDefaultValue <em>Owned Default Value</em>}' containment reference.

	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Default Value</em>' containment reference.
	 * @see #getOwnedDefaultValue()
	 * @generated
	 */

	void setOwnedDefaultValue(AbstractBooleanValue value);





} // BooleanType

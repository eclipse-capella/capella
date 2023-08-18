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

import org.polarsys.capella.core.data.information.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enumeration Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.EnumerationReference#getReferencedValue <em>Referenced Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.EnumerationReference#getReferencedProperty <em>Referenced Property</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getEnumerationReference()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A reference to an abstract enumeration value, allowing the reuse of enumeration values across data value structures' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Expression' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface EnumerationReference extends AbstractEnumerationValue {





	/**
   * Returns the value of the '<em><b>Referenced Value</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Referenced Value</em>' reference.
   * @see #setReferencedValue(AbstractEnumerationValue)
   * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getEnumerationReference_ReferencedValue()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the abstract enumeration value that this reference points to' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	AbstractEnumerationValue getReferencedValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datavalue.EnumerationReference#getReferencedValue <em>Referenced Value</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Referenced Value</em>' reference.
   * @see #getReferencedValue()
   * @generated
   */

	void setReferencedValue(AbstractEnumerationValue value);







	/**
   * Returns the value of the '<em><b>Referenced Property</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Property</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Referenced Property</em>' reference.
   * @see #setReferencedProperty(Property)
   * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getEnumerationReference_ReferencedProperty()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='reference'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the property that is using this reference\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	Property getReferencedProperty();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datavalue.EnumerationReference#getReferencedProperty <em>Referenced Property</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Referenced Property</em>' reference.
   * @see #getReferencedProperty()
   * @generated
   */

	void setReferencedProperty(Property value);





} // EnumerationReference

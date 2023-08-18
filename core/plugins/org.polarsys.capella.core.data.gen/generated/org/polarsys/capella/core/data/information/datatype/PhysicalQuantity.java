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
package org.polarsys.capella.core.data.information.datatype;

import org.polarsys.capella.core.data.information.Unit;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Physical Quantity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.PhysicalQuantity#getUnit <em>Unit</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getPhysicalQuantity()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='PhysicalDimension'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='DataType' stereotype='eng.PhysicalDimension'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A Physical Quantity is a measurable value of a physical property of a thing or a movement. It referes to a Unit.\r\n\r\nA Dimension (SysML notion of Physical Quantity) is a kind of quantity that may be stated by means of defined units. For example, the dimension of length may be measured by units of meters, kilometers, or feet\r\n[source: SysML specification v1.1]' usage\040guideline='n/a' used\040in\040levels='system/logical/physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='should be SysML::Blocks::ValueType, but its parent is concrete and already mapped (to uml::DataType), \r\nso do not map this one too to prevent Papyrus errors.' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface PhysicalQuantity extends NumericType {





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
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getPhysicalQuantity_Unit()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='unit'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the unit of this physical dimension\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	Unit getUnit();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.PhysicalQuantity#getUnit <em>Unit</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unit</em>' reference.
   * @see #getUnit()
   * @generated
   */

	void setUnit(Unit value);





} // PhysicalQuantity

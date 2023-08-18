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
package org.polarsys.capella.core.data.fa;

import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.Port;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Exchange End</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchangeEnd#getPort <em>Port</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchangeEnd#getPart <em>Part</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchangeEnd()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ConnectionEnd'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='an endpoint of a connection link\r\n\r\nA connector end is an endpoint of a connector, which attaches the connector to a connectable element. Each connector\r\nend is part of one connector.\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::ConnectorEnd' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface ComponentExchangeEnd extends InformationsExchanger, CapellaElement {





	/**
   * Returns the value of the '<em><b>Port</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Port</em>' reference.
   * @see #setPort(Port)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchangeEnd_Port()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='role' featureOwner='ConnectorEnd'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='port'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the port to which this communication endpoint is attached\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ConnectorEnd::role' explanation='none' constraints='uml::ConnectorEnd::role elements on which StandardPort stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	Port getPort();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.ComponentExchangeEnd#getPort <em>Port</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Port</em>' reference.
   * @see #getPort()
   * @generated
   */

	void setPort(Port value);







	/**
   * Returns the value of the '<em><b>Part</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Part</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Part</em>' reference.
   * @see #setPart(Part)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchangeEnd_Part()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='partWithPort' featureOwner='ConnectorEnd'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='part'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the part to which this connect endpoint is attached\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ConnectorEnd::partWithPort' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	Part getPart();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.ComponentExchangeEnd#getPart <em>Part</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Part</em>' reference.
   * @see #getPart()
   * @generated
   */

	void setPart(Part value);





} // ComponentExchangeEnd

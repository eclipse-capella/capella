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
package org.polarsys.capella.core.data.pa.deployment;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.fa.ComponentExchange;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.deployment.ConnectionInstance#getConnectionEnds <em>Connection Ends</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.deployment.ConnectionInstance#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.pa.deployment.DeploymentPackage#getConnectionInstance()
 * @model annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface ConnectionInstance extends AbstractPhysicalInstance {





	/**
   * Returns the value of the '<em><b>Connection Ends</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.deployment.PortInstance}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.pa.deployment.PortInstance#getConnections <em>Connections</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection Ends</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Connection Ends</em>' reference list.
   * @see org.polarsys.capella.core.data.pa.deployment.DeploymentPackage#getConnectionInstance_ConnectionEnds()
   * @see org.polarsys.capella.core.data.pa.deployment.PortInstance#getConnections
   * @model opposite="connections"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<PortInstance> getConnectionEnds();







	/**
   * Returns the value of the '<em><b>Type</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' reference.
   * @see #setType(ComponentExchange)
   * @see org.polarsys.capella.core.data.pa.deployment.DeploymentPackage#getConnectionInstance_Type()
   * @model annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ComponentExchange getType();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.pa.deployment.ConnectionInstance#getType <em>Type</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' reference.
   * @see #getType()
   * @generated
   */

	void setType(ComponentExchange value);





} // ConnectionInstance

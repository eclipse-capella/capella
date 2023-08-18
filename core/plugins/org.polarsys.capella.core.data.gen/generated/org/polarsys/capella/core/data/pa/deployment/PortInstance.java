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
import org.polarsys.capella.core.data.fa.ComponentPort;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.deployment.PortInstance#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.deployment.PortInstance#getComponent <em>Component</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.deployment.PortInstance#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.pa.deployment.DeploymentPackage#getPortInstance()
 * @model annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface PortInstance extends AbstractPhysicalInstance {





	/**
   * Returns the value of the '<em><b>Connections</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.deployment.ConnectionInstance}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.pa.deployment.ConnectionInstance#getConnectionEnds <em>Connection Ends</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connections</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Connections</em>' reference list.
   * @see org.polarsys.capella.core.data.pa.deployment.DeploymentPackage#getPortInstance_Connections()
   * @see org.polarsys.capella.core.data.pa.deployment.ConnectionInstance#getConnectionEnds
   * @model opposite="connectionEnds"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ConnectionInstance> getConnections();







	/**
   * Returns the value of the '<em><b>Component</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Component</em>' reference.
   * @see org.polarsys.capella.core.data.pa.deployment.DeploymentPackage#getPortInstance_Component()
   * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='portInstances'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ComponentInstance getComponent();







	/**
   * Returns the value of the '<em><b>Type</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' reference.
   * @see #setType(ComponentPort)
   * @see org.polarsys.capella.core.data.pa.deployment.DeploymentPackage#getPortInstance_Type()
   * @model annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ComponentPort getType();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.pa.deployment.PortInstance#getType <em>Type</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' reference.
   * @see #getType()
   * @generated
   */

	void setType(ComponentPort value);





} // PortInstance

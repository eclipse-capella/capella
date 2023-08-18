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
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.pa.PhysicalComponent;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.deployment.ComponentInstance#getPortInstances <em>Port Instances</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.deployment.ComponentInstance#getOwnedAbstractPhysicalInstances <em>Owned Abstract Physical Instances</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.deployment.ComponentInstance#getOwnedInstanceDeploymentLinks <em>Owned Instance Deployment Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.deployment.ComponentInstance#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.pa.deployment.DeploymentPackage#getComponentInstance()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An instance of a component for deployment purposes.' usage\040guideline='none' used\040in\040levels='physical' usage\040examples='none' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface ComponentInstance extends AbstractPhysicalInstance, DeployableElement, DeploymentTarget {





	/**
   * Returns the value of the '<em><b>Port Instances</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.deployment.PortInstance}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port Instances</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Port Instances</em>' reference list.
   * @see org.polarsys.capella.core.data.pa.deployment.DeploymentPackage#getComponentInstance_PortInstances()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedAbstractPhysicalInstances'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<PortInstance> getPortInstances();







	/**
   * Returns the value of the '<em><b>Owned Abstract Physical Instances</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.deployment.AbstractPhysicalInstance}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Abstract Physical Instances</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Abstract Physical Instances</em>' containment reference list.
   * @see org.polarsys.capella.core.data.pa.deployment.DeploymentPackage#getComponentInstance_OwnedAbstractPhysicalInstances()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<AbstractPhysicalInstance> getOwnedAbstractPhysicalInstances();







	/**
   * Returns the value of the '<em><b>Owned Instance Deployment Links</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.deployment.InstanceDeploymentLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Instance Deployment Links</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Instance Deployment Links</em>' containment reference list.
   * @see org.polarsys.capella.core.data.pa.deployment.DeploymentPackage#getComponentInstance_OwnedInstanceDeploymentLinks()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<InstanceDeploymentLink> getOwnedInstanceDeploymentLinks();







	/**
   * Returns the value of the '<em><b>Type</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' reference.
   * @see #setType(PhysicalComponent)
   * @see org.polarsys.capella.core.data.pa.deployment.DeploymentPackage#getComponentInstance_Type()
   * @model annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	PhysicalComponent getType();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.pa.deployment.ComponentInstance#getType <em>Type</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' reference.
   * @see #getType()
   * @generated
   */

	void setType(PhysicalComponent value);





} // ComponentInstance

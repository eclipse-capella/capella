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
package org.polarsys.capella.core.data.cs;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deployment Target</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.DeploymentTarget#getDeploymentLinks <em>Deployment Links</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getDeploymentTarget()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='DeploymentTarget'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Namespace'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the physical target that will host a deployable element\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::DeploymentTarget' constraints='none'"
 * @generated
 */
public interface DeploymentTarget extends NamedElement {





	/**
   * Returns the value of the '<em><b>Deployment Links</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.AbstractDeploymentLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deployment Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Deployment Links</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getDeploymentTarget_DeploymentLinks()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='deployments'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of deployment specifications involving this physical target as the destination of the deployment\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::NamedElement::clientDependency' explanation='none' constraints='uml::DeploymentTarget::deployment elements on which AbstractDeployment stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='location'"
   * @generated
   */

	EList<AbstractDeploymentLink> getDeploymentLinks();





} // DeploymentTarget

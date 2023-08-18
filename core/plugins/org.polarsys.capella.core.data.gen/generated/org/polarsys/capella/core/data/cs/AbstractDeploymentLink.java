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

import org.polarsys.capella.core.data.capellacore.Relationship;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Deployment Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.AbstractDeploymentLink#getDeployedElement <em>Deployed Element</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.AbstractDeploymentLink#getLocation <em>Location</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getAbstractDeploymentLink()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='AbstractDeployement'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Dependency'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the link between a physical element, and the physical target onto which it is deployed\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Dependency,could be mapped on uml::Deployment, but dependencies diagram allows to \"deploy\" more capella element types.' constraints='none'"
 * @generated
 */
public interface AbstractDeploymentLink extends Relationship {





	/**
   * Returns the value of the '<em><b>Deployed Element</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deployed Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Deployed Element</em>' reference.
   * @see #setDeployedElement(DeployableElement)
   * @see org.polarsys.capella.core.data.cs.CsPackage#getAbstractDeploymentLink_DeployedElement()
   * @model required="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='supplier' featureOwner='Dependency'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='deployedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the physical element involved in this relationship, that is to be deployed on the target\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Dependency::supplier' explanation='none' constraints='Multiplicity must be [1..1]'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	DeployableElement getDeployedElement();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.cs.AbstractDeploymentLink#getDeployedElement <em>Deployed Element</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Deployed Element</em>' reference.
   * @see #getDeployedElement()
   * @generated
   */

	void setDeployedElement(DeployableElement value);







	/**
   * Returns the value of the '<em><b>Location</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Location</em>' reference.
   * @see #setLocation(DeploymentTarget)
   * @see org.polarsys.capella.core.data.cs.CsPackage#getAbstractDeploymentLink_Location()
   * @model required="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='client' featureOwner='Dependency'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='location'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the host where the source element involved in this relationship will be deployed\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Dependency::client' explanation='none' constraints='uml::Dependency::client elements on which DeploymentTarget stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	DeploymentTarget getLocation();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.cs.AbstractDeploymentLink#getLocation <em>Location</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Location</em>' reference.
   * @see #getLocation()
   * @generated
   */

	void setLocation(DeploymentTarget value);





} // AbstractDeploymentLink

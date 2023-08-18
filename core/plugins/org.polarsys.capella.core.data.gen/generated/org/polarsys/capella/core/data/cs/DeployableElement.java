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
 * A representation of the model object '<em><b>Deployable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.DeployableElement#getDeployingLinks <em>Deploying Links</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getDeployableElement()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='DeployableElement'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='NamedElement'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='characterizes a physical model element that is intended to be deployed on a given (physical) target\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::NamedElement' constraints='none'"
 * @generated
 */
public interface DeployableElement extends NamedElement {





	/**
   * Returns the value of the '<em><b>Deploying Links</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.AbstractDeploymentLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deploying Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Deploying Links</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getDeployableElement_DeployingLinks()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping umlOppositeReference='supplier' umlOppositeReferenceOwner='Dependency'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='deployingLinks'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of deployment specifications associated to this element, e.g. associations between this element and a physical location to which it is to be deployed\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Opposite reference of uml::Dependency::supplier' constraints='Order must be computed'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='deployedElement'"
   * @generated
   */

	EList<AbstractDeploymentLink> getDeployingLinks();





} // DeployableElement

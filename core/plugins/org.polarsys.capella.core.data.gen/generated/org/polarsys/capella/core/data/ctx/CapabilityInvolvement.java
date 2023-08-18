/**
 *
 *  Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     Thales - initial API and implementation
 */

package org.polarsys.capella.core.data.ctx;

import org.polarsys.capella.core.data.capellacore.Involvement;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Capability Involvement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.CapabilityInvolvement#getSystemComponent <em>System Component</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.CapabilityInvolvement#getCapability <em>Capability</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapabilityInvolvement()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='CapabilityInvolvement'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Dependency' stereotype='eng.CapabilityInvolvement'"
 *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link between a system component and a system capability that means the system component is involved in the capability\r\n[source:Capella study]' usage\040guideline='n/a' used\040in\040levels='system' usage\040examples='../img/usage_examples/example_actor_capability.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Dependency' explanation='none' constraints='none'"
 * @generated
 */

public interface CapabilityInvolvement extends Involvement {





	/**
   * Returns the value of the '<em><b>System Component</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>System Component</em>' reference.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapabilityInvolvement_SystemComponent()
   * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to a system component that is involved in the system capability.' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='involved'"
   * @generated
   */

	SystemComponent getSystemComponent();







	/**
   * Returns the value of the '<em><b>Capability</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Capability</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Capability</em>' reference.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapabilityInvolvement_Capability()
   * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='client' featureOwner='Dependency'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='capability'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='involver'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to the system capability involving the actor\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	Capability getCapability();





} // CapabilityInvolvement

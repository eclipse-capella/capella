/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.ctx;

import org.polarsys.capella.core.data.capellacore.Involvement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System Capability Involvement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement#getCapability <em>Capability</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement#getSystem <em>System</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemCapabilityInvolvement()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='CapabilitySupplierLink'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Dependency' stereotype='eng.CapabilitySupplierLink'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link between a system and a system capability that means the system is involved in the capability\r\n[source:Capella study]' usage\040guideline='n/a' used\040in\040levels='system' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Dependency' explanation='none' constraints='none'"
 * @generated
 */
public interface SystemCapabilityInvolvement extends Involvement {





	/**
	 * Returns the value of the '<em><b>Capability</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.Capability#getInvolvedSystem <em>Involved System</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Capability</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Capability</em>' reference.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemCapabilityInvolvement_Capability()
	 * @see org.polarsys.capella.core.data.ctx.Capability#getInvolvedSystem
	 * @model opposite="involvedSystem" required="true" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='client' featureOwner='Dependency'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='capability'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Capability involved in this relationship\r\n[source:Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	Capability getCapability();







	/**
	 * Returns the value of the '<em><b>System</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.System#getParticipationsInCapabilities <em>Participations In Capabilities</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System</em>' reference.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemCapabilityInvolvement_System()
	 * @see org.polarsys.capella.core.data.ctx.System#getParticipationsInCapabilities
	 * @model opposite="participationsInCapabilities" required="true" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='supplier' featureOwner='Dependency'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='system'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The System involved in this relationship\r\n[source:Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	org.polarsys.capella.core.data.ctx.System getSystem();





} // SystemCapabilityInvolvement

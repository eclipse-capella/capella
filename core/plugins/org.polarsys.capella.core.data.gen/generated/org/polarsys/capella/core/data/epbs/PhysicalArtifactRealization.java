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
package org.polarsys.capella.core.data.epbs;

import org.polarsys.capella.core.data.capellacore.Allocation;
import org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Physical Artifact Realization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization#getRealizedPhysicalArtifact <em>Realized Physical Artifact</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization#getRealizingConfigurationItem <em>Realizing Configuration Item</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.epbs.EpbsPackage#getPhysicalArtifactRealization()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Realization link betwen a Configuration Item and a physical artifact' constraints='none' comment/notes='none'"
 * @generated
 */
public interface PhysicalArtifactRealization extends Allocation {

	/**
   * Returns the value of the '<em><b>Realized Physical Artifact</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Physical Artifact</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Physical Artifact</em>' reference.
   * @see org.polarsys.capella.core.data.epbs.EpbsPackage#getPhysicalArtifactRealization_RealizedPhysicalArtifact()
   * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='targetElement'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies the allocated architecture\r\n[source: Capella study]\r\n\r\nSpecifies the targets of the DirectedRelationship.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   * @generated
   */

	AbstractPhysicalArtifact getRealizedPhysicalArtifact();

	/**
   * Returns the value of the '<em><b>Realizing Configuration Item</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Configuration Item</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Configuration Item</em>' reference.
   * @see org.polarsys.capella.core.data.epbs.EpbsPackage#getPhysicalArtifactRealization_RealizingConfigurationItem()
   * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='sourceElement'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies the allocating architecture\r\n[source: Capella study]\r\n\r\nSpecifies the sources of the DirectedRelationship.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	ConfigurationItem getRealizingConfigurationItem();



} // PhysicalArtifactRealization

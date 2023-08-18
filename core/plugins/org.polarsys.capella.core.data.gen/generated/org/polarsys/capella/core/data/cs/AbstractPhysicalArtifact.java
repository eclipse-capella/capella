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
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Physical Artifact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact#getAllocatorConfigurationItems <em>Allocator Configuration Items</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getAbstractPhysicalArtifact()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A physical artifact is any physical element in the physical architecture (component, port, link,...).\r\nThese artifacts will be part allocated to configuration items in the EPBS.' usage\040guideline='n/a' used\040in\040levels='physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 * @generated
 */
public interface AbstractPhysicalArtifact extends CapellaElement {





	/**
   * Returns the value of the '<em><b>Allocator Configuration Items</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.epbs.ConfigurationItem}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.epbs.ConfigurationItem#getAllocatedPhysicalArtifacts <em>Allocated Physical Artifacts</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocator Configuration Items</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocator Configuration Items</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getAbstractPhysicalArtifact_AllocatorConfigurationItems()
   * @see org.polarsys.capella.core.data.epbs.ConfigurationItem#getAllocatedPhysicalArtifacts
   * @model opposite="allocatedPhysicalArtifacts" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='PhysicalArtifactRealization.targetElement(par, self);\r\nPhysicalArtifactRealization.allocatingComponent(par, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ConfigurationItem> getAllocatorConfigurationItems();





} // AbstractPhysicalArtifact

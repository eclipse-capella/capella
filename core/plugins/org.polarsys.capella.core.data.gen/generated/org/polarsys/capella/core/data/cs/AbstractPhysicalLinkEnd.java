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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Physical Link End</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd#getInvolvedLinks <em>Involved Links</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getAbstractPhysicalLinkEnd()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='End of a physical link' usage\040guideline='n/a' used\040in\040levels='physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 * @generated
 */
public interface AbstractPhysicalLinkEnd extends CapellaElement {





	/**
   * Returns the value of the '<em><b>Involved Links</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.PhysicalLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involved Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involved Links</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getAbstractPhysicalLinkEnd_InvolvedLinks()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Physical links that come in or out of this physical port\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='PhysicalLink.linkEnds(target, self);\r\n} or {\r\n\tPhysicalLinkEnd.port(ple, self);\t\r\n\tPhysicalLink.linkEnds(target, ple);'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<PhysicalLink> getInvolvedLinks();





} // AbstractPhysicalLinkEnd

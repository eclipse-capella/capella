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
package org.polarsys.capella.core.data.pa;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Physical Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalNode#getSubPhysicalNodes <em>Sub Physical Nodes</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalNode()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='PhysicalNode'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Component' stereotype='eng.PhysicalNode'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a physical resource hosting behavioral components, and required for their execution or expected behavior\r\n[source: Arcadia encyclopedia]' usage\040guideline='n/a' used\040in\040levels='physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Node' explanation='none' constraints='none'"
 * @generated
 */
public interface PhysicalNode extends PhysicalComponent {





	/**
	 * Returns the value of the '<em><b>Sub Physical Nodes</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalNode}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Physical Nodes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Physical Nodes</em>' reference list.
	 * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalNode_SubPhysicalNodes()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='subActors'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='all derived children of this physical node\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
	 * @generated
	 */

	EList<PhysicalNode> getSubPhysicalNodes();





} // PhysicalNode

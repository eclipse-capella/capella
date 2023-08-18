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
package org.polarsys.capella.common.re;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Compliancy Definition Pkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.re.CompliancyDefinitionPkg#getOwnedDefinitions <em>Owned Definitions</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.re.RePackage#getCompliancyDefinitionPkg()
 * @model
 * @generated
 */

public interface CompliancyDefinitionPkg extends ReNamedElement {





	/**
   * Returns the value of the '<em><b>Owned Definitions</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.common.re.CompliancyDefinition}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Definitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Definitions</em>' containment reference list.
   * @see org.polarsys.capella.common.re.RePackage#getCompliancyDefinitionPkg_OwnedDefinitions()
   * @model containment="true" resolveProxies="true"
   * @generated
   */

	EList<CompliancyDefinition> getOwnedDefinitions();





} // CompliancyDefinitionPkg

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

import org.polarsys.kitalpha.emde.model.ElementExtension;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rec Catalog</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.re.RecCatalog#getOwnedCompliancyDefinitionPkg <em>Owned Compliancy Definition Pkg</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.re.RePackage#getRecCatalog()
 * @model annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/constraint ExtendedElement='http://www.polarsys.org/capella/core/modeller/7.0.0#//SystemEngineering '"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/constraintMapping Mapping='platform:/plugin/org.polarsys.capella.core.data.gen/model/CapellaModeller.ecore#//SystemEngineering '"
 * @generated
 */

public interface RecCatalog extends CatalogElementPkg, ElementExtension {





	/**
   * Returns the value of the '<em><b>Owned Compliancy Definition Pkg</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Compliancy Definition Pkg</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Compliancy Definition Pkg</em>' containment reference.
   * @see #setOwnedCompliancyDefinitionPkg(CompliancyDefinitionPkg)
   * @see org.polarsys.capella.common.re.RePackage#getRecCatalog_OwnedCompliancyDefinitionPkg()
   * @model containment="true" resolveProxies="true"
   * @generated
   */

	CompliancyDefinitionPkg getOwnedCompliancyDefinitionPkg();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.re.RecCatalog#getOwnedCompliancyDefinitionPkg <em>Owned Compliancy Definition Pkg</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Compliancy Definition Pkg</em>' containment reference.
   * @see #getOwnedCompliancyDefinitionPkg()
   * @generated
   */

	void setOwnedCompliancyDefinitionPkg(CompliancyDefinitionPkg value);





} // RecCatalog

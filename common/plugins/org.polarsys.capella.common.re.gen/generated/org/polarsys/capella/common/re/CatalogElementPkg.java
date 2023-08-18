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
 * A representation of the model object '<em><b>Catalog Element Pkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElementPkg#getOwnedElementPkgs <em>Owned Element Pkgs</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.re.RePackage#getCatalogElementPkg()
 * @model annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */

public interface CatalogElementPkg extends ReNamedElement, ReElementContainer {





	/**
   * Returns the value of the '<em><b>Owned Element Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.common.re.CatalogElementPkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Element Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Element Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElementPkg_OwnedElementPkgs()
   * @model containment="true" resolveProxies="true"
   * @generated
   */

	EList<CatalogElementPkg> getOwnedElementPkgs();





} // CatalogElementPkg

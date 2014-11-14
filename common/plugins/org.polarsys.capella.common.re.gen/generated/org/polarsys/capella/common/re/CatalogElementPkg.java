/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
 * <ul>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElementPkg#getOwnedElementPkgs <em>Owned Element Pkgs</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.polarsys.capella.common.re.RePackage#getCatalogElementPkg()
 * @model
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

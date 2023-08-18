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
package org.polarsys.capella.common.libraries.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource Factory</b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.common.libraries.util.LibrariesResourceImpl
 * @generated
 */
public class LibrariesResourceFactoryImpl extends ResourceFactoryImpl {
	/**
   * Creates an instance of the resource factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public LibrariesResourceFactoryImpl() {
    super();
  }

	/**
   * Creates an instance of the resource.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Resource createResource(URI uri) {

    Resource result = new LibrariesResourceImpl(uri);
    return result;

  }

} //LibrariesResourceFactoryImpl

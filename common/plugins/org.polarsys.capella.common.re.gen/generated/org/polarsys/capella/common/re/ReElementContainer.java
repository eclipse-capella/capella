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
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.re.ReElementContainer#getOwnedElements <em>Owned Elements</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.re.RePackage#getReElementContainer()
 * @model interface="true" abstract="true"
 * @generated
 */

public interface ReElementContainer extends EObject {





	/**
   * Returns the value of the '<em><b>Owned Elements</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.common.re.CatalogElement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Elements</em>' containment reference list.
   * @see org.polarsys.capella.common.re.RePackage#getReElementContainer_OwnedElements()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<CatalogElement> getOwnedElements();





} // ReElementContainer

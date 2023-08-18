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
package org.polarsys.capella.core.data.interaction.impl;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.capellacore.impl.TraceImpl;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.RefinementLink;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Refinement Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class RefinementLinkImpl extends TraceImpl implements RefinementLink {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected RefinementLinkImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InteractionPackage.Literals.REFINEMENT_LINK;
  }




} //RefinementLinkImpl
/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.vp.ms;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Boolean Operation</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.polarsys.capella.vp.ms.BooleanOperation#getChildren <em>Children</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.vp.ms.MsPackage#getBooleanOperation()
 * @model abstract="true"
 * @generated
 */

public interface BooleanOperation extends BooleanExpression {

  /**
   * Returns the value of the '<em><b>Children</b></em>' containment reference list. The list contents are of type
   * {@link org.polarsys.capella.vp.ms.BooleanExpression}.
   * 
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Children</em>' containment reference list isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Children</em>' containment reference list.
   * @see org.polarsys.capella.vp.ms.MsPackage#getBooleanOperation_Children()
   * @model containment="true"
   * @generated
   */

  EList<BooleanExpression> getChildren();

} // BooleanOperation

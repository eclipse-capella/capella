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

import org.polarsys.capella.core.data.capellacommon.AbstractState;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>In State Expression</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.polarsys.capella.vp.ms.InStateExpression#getState <em>State</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.vp.ms.MsPackage#getInStateExpression()
 * @model
 * @generated
 */

public interface InStateExpression extends BooleanExpression {

  /**
   * Returns the value of the '<em><b>State</b></em>' reference.
   * 
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>State</em>' reference isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>State</em>' reference.
   * @see #setState(AbstractState)
   * @see org.polarsys.capella.vp.ms.MsPackage#getInStateExpression_State()
   * @model
   * @generated
   */

  AbstractState getState();

  /**
   * Sets the value of the '{@link org.polarsys.capella.vp.ms.InStateExpression#getState <em>State</em>}' reference.
   * 
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value
   *          the new value of the '<em>State</em>' reference.
   * @see #getState()
   * @generated
   */

  void setState(AbstractState value);

} // InStateExpression

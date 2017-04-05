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

import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.kitalpha.emde.model.ElementExtension;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>FSM Type</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.polarsys.capella.vp.ms.FSMType#getMs <em>Ms</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.vp.ms.MsPackage#getFSMType()
 * @model annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/constraint ExtendedElement='
 *        http://www.polarsys.org/capella/core/common/1.1.0#//StateMachine'"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/constraintMapping Mapping='
 *        platform:/plugin/org.polarsys.capella.core.data.gen/model/CapellaCommon.ecore#//StateMachine'"
 * @generated
 */

public interface FSMType extends NamedElement, ElementExtension {

  /**
   * Returns the value of the '<em><b>Ms</b></em>' attribute. The literals are from the enumeration
   * {@link org.polarsys.capella.vp.ms.ms_Type}.
   * 
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ms</em>' attribute isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Ms</em>' attribute.
   * @see org.polarsys.capella.vp.ms.ms_Type
   * @see #setMs(ms_Type)
   * @see org.polarsys.capella.vp.ms.MsPackage#getFSMType_Ms()
   * @model
   * @generated
   */

  ms_Type getMs();

  /**
   * Sets the value of the '{@link org.polarsys.capella.vp.ms.FSMType#getMs <em>Ms</em>}' attribute.
   * 
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value
   *          the new value of the '<em>Ms</em>' attribute.
   * @see org.polarsys.capella.vp.ms.ms_Type
   * @see #getMs()
   * @generated
   */

  void setMs(ms_Type value);

} // FSMType

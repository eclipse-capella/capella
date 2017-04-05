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

package org.polarsys.capella.vp.ms.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.vp.ms.FSMType;
import org.polarsys.capella.vp.ms.MsPackage;
import org.polarsys.capella.vp.ms.ms_Type;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>FSM Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.polarsys.capella.vp.ms.impl.FSMTypeImpl#getMs <em>Ms</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FSMTypeImpl extends NamedElementImpl implements FSMType {

  /**
   * The default value of the '{@link #getMs() <em>Ms</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getMs()
   * @generated
   * @ordered
   */
  protected static final ms_Type MS_EDEFAULT = ms_Type.MIXED;

  /**
   * The cached value of the '{@link #getMs() <em>Ms</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getMs()
   * @generated
   * @ordered
   */
  protected ms_Type ms = MS_EDEFAULT;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected FSMTypeImpl() {

    super();

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return MsPackage.Literals.FSM_TYPE;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public ms_Type getMs() {

    return ms;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public void setMs(ms_Type newMs) {

    ms_Type oldMs = ms;
    ms = newMs == null ? MS_EDEFAULT : newMs;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MsPackage.FSM_TYPE__MS, oldMs, ms));

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case MsPackage.FSM_TYPE__MS:
      return getMs();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case MsPackage.FSM_TYPE__MS:
      setMs((ms_Type) newValue);
      return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
    case MsPackage.FSM_TYPE__MS:
      setMs(MS_EDEFAULT);
      return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
    case MsPackage.FSM_TYPE__MS:
      return ms != MS_EDEFAULT;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy())
      return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (ms: "); //$NON-NLS-1$
    result.append(ms);
    result.append(')');
    return result.toString();
  }

} // FSMTypeImpl
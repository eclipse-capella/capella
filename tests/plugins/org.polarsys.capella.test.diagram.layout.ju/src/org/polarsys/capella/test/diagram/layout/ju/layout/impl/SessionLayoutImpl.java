/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.layout.ju.layout.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage;
import org.polarsys.capella.test.diagram.layout.ju.layout.SessionLayout;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Session Layout</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.SessionLayoutImpl#getOwnedLayouts <em>Owned Layouts</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SessionLayoutImpl extends MinimalEObjectImpl.Container implements SessionLayout {
  /**
   * The cached value of the '{@link #getOwnedLayouts() <em>Owned Layouts</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOwnedLayouts()
   * @generated
   * @ordered
   */
  protected EList<DiagramLayout> ownedLayouts;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SessionLayoutImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return LayoutPackage.Literals.SESSION_LAYOUT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<DiagramLayout> getOwnedLayouts() {
    if (ownedLayouts == null) {
      ownedLayouts = new EObjectContainmentEList<DiagramLayout>(DiagramLayout.class, this, LayoutPackage.SESSION_LAYOUT__OWNED_LAYOUTS);
    }
    return ownedLayouts;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case LayoutPackage.SESSION_LAYOUT__OWNED_LAYOUTS:
        return ((InternalEList<?>)getOwnedLayouts()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case LayoutPackage.SESSION_LAYOUT__OWNED_LAYOUTS:
        return getOwnedLayouts();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case LayoutPackage.SESSION_LAYOUT__OWNED_LAYOUTS:
        getOwnedLayouts().clear();
        getOwnedLayouts().addAll((Collection<? extends DiagramLayout>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
      case LayoutPackage.SESSION_LAYOUT__OWNED_LAYOUTS:
        getOwnedLayouts().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
      case LayoutPackage.SESSION_LAYOUT__OWNED_LAYOUTS:
        return ownedLayouts != null && !ownedLayouts.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //SessionLayoutImpl

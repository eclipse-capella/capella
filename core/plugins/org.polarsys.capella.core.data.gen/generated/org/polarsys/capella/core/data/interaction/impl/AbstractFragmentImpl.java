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

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.interaction.AbstractFragment;
import org.polarsys.capella.core.data.interaction.Gate;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Fragment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractFragmentImpl#getOwnedGates <em>Owned Gates</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractFragmentImpl extends TimeLapseImpl implements AbstractFragment {

	/**
   * The cached value of the '{@link #getOwnedGates() <em>Owned Gates</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedGates()
   * @generated
   * @ordered
   */
	protected EList<Gate> ownedGates;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected AbstractFragmentImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InteractionPackage.Literals.ABSTRACT_FRAGMENT;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Gate> getOwnedGates() {

    if (ownedGates == null) {
      ownedGates = new EObjectContainmentEList.Resolving<Gate>(Gate.class, this, InteractionPackage.ABSTRACT_FRAGMENT__OWNED_GATES);
    }
    return ownedGates;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case InteractionPackage.ABSTRACT_FRAGMENT__OWNED_GATES:
        return ((InternalEList<?>)getOwnedGates()).basicRemove(otherEnd, msgs);
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
      case InteractionPackage.ABSTRACT_FRAGMENT__OWNED_GATES:
        return getOwnedGates();
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
      case InteractionPackage.ABSTRACT_FRAGMENT__OWNED_GATES:
        getOwnedGates().clear();
        getOwnedGates().addAll((Collection<? extends Gate>)newValue);
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
      case InteractionPackage.ABSTRACT_FRAGMENT__OWNED_GATES:
        getOwnedGates().clear();
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
      case InteractionPackage.ABSTRACT_FRAGMENT__OWNED_GATES:
        return ownedGates != null && !ownedGates.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //AbstractFragmentImpl
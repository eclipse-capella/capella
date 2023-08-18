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
package org.polarsys.capella.core.data.capellacommon.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Region</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.RegionImpl#getOwnedStates <em>Owned States</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.RegionImpl#getOwnedTransitions <em>Owned Transitions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.RegionImpl#getInvolvedStates <em>Involved States</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RegionImpl extends NamedElementImpl implements Region {

	/**
   * The cached value of the '{@link #getOwnedStates() <em>Owned States</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedStates()
   * @generated
   * @ordered
   */
	protected EList<AbstractState> ownedStates;





	/**
   * The cached value of the '{@link #getOwnedTransitions() <em>Owned Transitions</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedTransitions()
   * @generated
   * @ordered
   */
	protected EList<StateTransition> ownedTransitions;





	/**
   * The cached value of the '{@link #getInvolvedStates() <em>Involved States</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getInvolvedStates()
   * @generated
   * @ordered
   */
	protected EList<AbstractState> involvedStates;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected RegionImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CapellacommonPackage.Literals.REGION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractState> getOwnedStates() {

    if (ownedStates == null) {
      ownedStates = new EObjectContainmentEList.Resolving<AbstractState>(AbstractState.class, this, CapellacommonPackage.REGION__OWNED_STATES);
    }
    return ownedStates;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<StateTransition> getOwnedTransitions() {

    if (ownedTransitions == null) {
      ownedTransitions = new EObjectContainmentEList.Resolving<StateTransition>(StateTransition.class, this, CapellacommonPackage.REGION__OWNED_TRANSITIONS);
    }
    return ownedTransitions;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractState> getInvolvedStates() {

    if (involvedStates == null) {
      involvedStates = new EObjectResolvingEList<AbstractState>(AbstractState.class, this, CapellacommonPackage.REGION__INVOLVED_STATES);
    }
    return involvedStates;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case CapellacommonPackage.REGION__OWNED_STATES:
        return ((InternalEList<?>)getOwnedStates()).basicRemove(otherEnd, msgs);
      case CapellacommonPackage.REGION__OWNED_TRANSITIONS:
        return ((InternalEList<?>)getOwnedTransitions()).basicRemove(otherEnd, msgs);
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
      case CapellacommonPackage.REGION__OWNED_STATES:
        return getOwnedStates();
      case CapellacommonPackage.REGION__OWNED_TRANSITIONS:
        return getOwnedTransitions();
      case CapellacommonPackage.REGION__INVOLVED_STATES:
        return getInvolvedStates();
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
      case CapellacommonPackage.REGION__OWNED_STATES:
        getOwnedStates().clear();
        getOwnedStates().addAll((Collection<? extends AbstractState>)newValue);
        return;
      case CapellacommonPackage.REGION__OWNED_TRANSITIONS:
        getOwnedTransitions().clear();
        getOwnedTransitions().addAll((Collection<? extends StateTransition>)newValue);
        return;
      case CapellacommonPackage.REGION__INVOLVED_STATES:
        getInvolvedStates().clear();
        getInvolvedStates().addAll((Collection<? extends AbstractState>)newValue);
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
      case CapellacommonPackage.REGION__OWNED_STATES:
        getOwnedStates().clear();
        return;
      case CapellacommonPackage.REGION__OWNED_TRANSITIONS:
        getOwnedTransitions().clear();
        return;
      case CapellacommonPackage.REGION__INVOLVED_STATES:
        getInvolvedStates().clear();
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
      case CapellacommonPackage.REGION__OWNED_STATES:
        return ownedStates != null && !ownedStates.isEmpty();
      case CapellacommonPackage.REGION__OWNED_TRANSITIONS:
        return ownedTransitions != null && !ownedTransitions.isEmpty();
      case CapellacommonPackage.REGION__INVOLVED_STATES:
        return involvedStates != null && !involvedStates.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //RegionImpl
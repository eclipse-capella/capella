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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operand</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.InteractionOperandImpl#getReferencedInteractionFragments <em>Referenced Interaction Fragments</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.InteractionOperandImpl#getGuard <em>Guard</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InteractionOperandImpl extends InteractionFragmentImpl implements InteractionOperand {

	/**
   * The cached value of the '{@link #getReferencedInteractionFragments() <em>Referenced Interaction Fragments</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getReferencedInteractionFragments()
   * @generated
   * @ordered
   */
	protected EList<InteractionFragment> referencedInteractionFragments;

	/**
   * The cached value of the '{@link #getGuard() <em>Guard</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getGuard()
   * @generated
   * @ordered
   */
	protected Constraint guard;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected InteractionOperandImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InteractionPackage.Literals.INTERACTION_OPERAND;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Constraint getGuard() {

    if (guard != null && guard.eIsProxy()) {
      InternalEObject oldGuard = (InternalEObject)guard;
      guard = (Constraint)eResolveProxy(oldGuard);
      if (guard != oldGuard) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.INTERACTION_OPERAND__GUARD, oldGuard, guard));
      }
    }
    return guard;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Constraint basicGetGuard() {

    return guard;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setGuard(Constraint newGuard) {

    Constraint oldGuard = guard;
    guard = newGuard;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.INTERACTION_OPERAND__GUARD, oldGuard, guard));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<InteractionFragment> getReferencedInteractionFragments() {

    if (referencedInteractionFragments == null) {
      referencedInteractionFragments = new EObjectResolvingEList<InteractionFragment>(InteractionFragment.class, this, InteractionPackage.INTERACTION_OPERAND__REFERENCED_INTERACTION_FRAGMENTS);
    }
    return referencedInteractionFragments;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case InteractionPackage.INTERACTION_OPERAND__REFERENCED_INTERACTION_FRAGMENTS:
        return getReferencedInteractionFragments();
      case InteractionPackage.INTERACTION_OPERAND__GUARD:
        if (resolve) return getGuard();
        return basicGetGuard();
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
      case InteractionPackage.INTERACTION_OPERAND__REFERENCED_INTERACTION_FRAGMENTS:
        getReferencedInteractionFragments().clear();
        getReferencedInteractionFragments().addAll((Collection<? extends InteractionFragment>)newValue);
        return;
      case InteractionPackage.INTERACTION_OPERAND__GUARD:
          setGuard((Constraint)newValue);
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
      case InteractionPackage.INTERACTION_OPERAND__REFERENCED_INTERACTION_FRAGMENTS:
        getReferencedInteractionFragments().clear();
        return;
      case InteractionPackage.INTERACTION_OPERAND__GUARD:
        setGuard((Constraint)null);
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
      case InteractionPackage.INTERACTION_OPERAND__REFERENCED_INTERACTION_FRAGMENTS:
        return referencedInteractionFragments != null && !referencedInteractionFragments.isEmpty();
      case InteractionPackage.INTERACTION_OPERAND__GUARD:
        return guard != null;
    }
    return super.eIsSet(featureID);
  }


} //InteractionOperandImpl

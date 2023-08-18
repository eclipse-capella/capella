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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fragment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.InteractionFragmentImpl#getCoveredInstanceRoles <em>Covered Instance Roles</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class InteractionFragmentImpl extends NamedElementImpl implements InteractionFragment {

	/**
   * The cached value of the '{@link #getCoveredInstanceRoles() <em>Covered Instance Roles</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getCoveredInstanceRoles()
   * @generated
   * @ordered
   */
	protected EList<InstanceRole> coveredInstanceRoles;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected InteractionFragmentImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InteractionPackage.Literals.INTERACTION_FRAGMENT;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<InstanceRole> getCoveredInstanceRoles() {

    if (coveredInstanceRoles == null) {
      coveredInstanceRoles = new EObjectResolvingEList<InstanceRole>(InstanceRole.class, this, InteractionPackage.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES);
    }
    return coveredInstanceRoles;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case InteractionPackage.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES:
        return getCoveredInstanceRoles();
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
      case InteractionPackage.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES:
        getCoveredInstanceRoles().clear();
        getCoveredInstanceRoles().addAll((Collection<? extends InstanceRole>)newValue);
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
      case InteractionPackage.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES:
        getCoveredInstanceRoles().clear();
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
      case InteractionPackage.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES:
        return coveredInstanceRoles != null && !coveredInstanceRoles.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //InteractionFragmentImpl
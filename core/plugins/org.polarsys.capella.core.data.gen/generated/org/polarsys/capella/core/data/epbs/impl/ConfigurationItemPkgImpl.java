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

package org.polarsys.capella.core.data.epbs.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemPkg;
import org.polarsys.capella.core.data.epbs.EpbsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Configuration Item Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.epbs.impl.ConfigurationItemPkgImpl#getOwnedConfigurationItems <em>Owned Configuration Items</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.epbs.impl.ConfigurationItemPkgImpl#getOwnedConfigurationItemPkgs <em>Owned Configuration Item Pkgs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConfigurationItemPkgImpl extends ComponentPkgImpl implements ConfigurationItemPkg {

	/**
   * The cached value of the '{@link #getOwnedConfigurationItems() <em>Owned Configuration Items</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedConfigurationItems()
   * @generated
   * @ordered
   */
	protected EList<ConfigurationItem> ownedConfigurationItems;





	/**
   * The cached value of the '{@link #getOwnedConfigurationItemPkgs() <em>Owned Configuration Item Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedConfigurationItemPkgs()
   * @generated
   * @ordered
   */
	protected EList<ConfigurationItemPkg> ownedConfigurationItemPkgs;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ConfigurationItemPkgImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return EpbsPackage.Literals.CONFIGURATION_ITEM_PKG;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ConfigurationItem> getOwnedConfigurationItems() {

    if (ownedConfigurationItems == null) {
      ownedConfigurationItems = new EObjectContainmentEList.Resolving<ConfigurationItem>(ConfigurationItem.class, this, EpbsPackage.CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEMS);
    }
    return ownedConfigurationItems;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ConfigurationItemPkg> getOwnedConfigurationItemPkgs() {

    if (ownedConfigurationItemPkgs == null) {
      ownedConfigurationItemPkgs = new EObjectContainmentEList.Resolving<ConfigurationItemPkg>(ConfigurationItemPkg.class, this, EpbsPackage.CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEM_PKGS);
    }
    return ownedConfigurationItemPkgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case EpbsPackage.CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEMS:
        return ((InternalEList<?>)getOwnedConfigurationItems()).basicRemove(otherEnd, msgs);
      case EpbsPackage.CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEM_PKGS:
        return ((InternalEList<?>)getOwnedConfigurationItemPkgs()).basicRemove(otherEnd, msgs);
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
      case EpbsPackage.CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEMS:
        return getOwnedConfigurationItems();
      case EpbsPackage.CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEM_PKGS:
        return getOwnedConfigurationItemPkgs();
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
      case EpbsPackage.CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEMS:
        getOwnedConfigurationItems().clear();
        getOwnedConfigurationItems().addAll((Collection<? extends ConfigurationItem>)newValue);
        return;
      case EpbsPackage.CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEM_PKGS:
        getOwnedConfigurationItemPkgs().clear();
        getOwnedConfigurationItemPkgs().addAll((Collection<? extends ConfigurationItemPkg>)newValue);
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
      case EpbsPackage.CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEMS:
        getOwnedConfigurationItems().clear();
        return;
      case EpbsPackage.CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEM_PKGS:
        getOwnedConfigurationItemPkgs().clear();
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
      case EpbsPackage.CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEMS:
        return ownedConfigurationItems != null && !ownedConfigurationItems.isEmpty();
      case EpbsPackage.CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEM_PKGS:
        return ownedConfigurationItemPkgs != null && !ownedConfigurationItemPkgs.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //ConfigurationItemPkgImpl
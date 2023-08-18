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
package org.polarsys.capella.core.data.cs.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.AbstractExchangeItemPkg;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.impl.MessageReferencePkgImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interface Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfacePkgImpl#getOwnedExchangeItems <em>Owned Exchange Items</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfacePkgImpl#getOwnedInterfaces <em>Owned Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfacePkgImpl#getOwnedInterfacePkgs <em>Owned Interface Pkgs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InterfacePkgImpl extends MessageReferencePkgImpl implements InterfacePkg {

	/**
   * The cached value of the '{@link #getOwnedExchangeItems() <em>Owned Exchange Items</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedExchangeItems()
   * @generated
   * @ordered
   */
	protected EList<ExchangeItem> ownedExchangeItems;





	/**
   * The cached value of the '{@link #getOwnedInterfaces() <em>Owned Interfaces</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedInterfaces()
   * @generated
   * @ordered
   */
	protected EList<Interface> ownedInterfaces;





	/**
   * The cached value of the '{@link #getOwnedInterfacePkgs() <em>Owned Interface Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedInterfacePkgs()
   * @generated
   * @ordered
   */
	protected EList<InterfacePkg> ownedInterfacePkgs;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected InterfacePkgImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CsPackage.Literals.INTERFACE_PKG;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ExchangeItem> getOwnedExchangeItems() {

    if (ownedExchangeItems == null) {
      ownedExchangeItems = new EObjectContainmentEList.Resolving<ExchangeItem>(ExchangeItem.class, this, CsPackage.INTERFACE_PKG__OWNED_EXCHANGE_ITEMS);
    }
    return ownedExchangeItems;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Interface> getOwnedInterfaces() {

    if (ownedInterfaces == null) {
      ownedInterfaces = new EObjectContainmentEList.Resolving<Interface>(Interface.class, this, CsPackage.INTERFACE_PKG__OWNED_INTERFACES);
    }
    return ownedInterfaces;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<InterfacePkg> getOwnedInterfacePkgs() {

    if (ownedInterfacePkgs == null) {
      ownedInterfacePkgs = new EObjectContainmentEList.Resolving<InterfacePkg>(InterfacePkg.class, this, CsPackage.INTERFACE_PKG__OWNED_INTERFACE_PKGS);
    }
    return ownedInterfacePkgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case CsPackage.INTERFACE_PKG__OWNED_EXCHANGE_ITEMS:
        return ((InternalEList<?>)getOwnedExchangeItems()).basicRemove(otherEnd, msgs);
      case CsPackage.INTERFACE_PKG__OWNED_INTERFACES:
        return ((InternalEList<?>)getOwnedInterfaces()).basicRemove(otherEnd, msgs);
      case CsPackage.INTERFACE_PKG__OWNED_INTERFACE_PKGS:
        return ((InternalEList<?>)getOwnedInterfacePkgs()).basicRemove(otherEnd, msgs);
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
      case CsPackage.INTERFACE_PKG__OWNED_EXCHANGE_ITEMS:
        return getOwnedExchangeItems();
      case CsPackage.INTERFACE_PKG__OWNED_INTERFACES:
        return getOwnedInterfaces();
      case CsPackage.INTERFACE_PKG__OWNED_INTERFACE_PKGS:
        return getOwnedInterfacePkgs();
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
      case CsPackage.INTERFACE_PKG__OWNED_EXCHANGE_ITEMS:
        getOwnedExchangeItems().clear();
        getOwnedExchangeItems().addAll((Collection<? extends ExchangeItem>)newValue);
        return;
      case CsPackage.INTERFACE_PKG__OWNED_INTERFACES:
        getOwnedInterfaces().clear();
        getOwnedInterfaces().addAll((Collection<? extends Interface>)newValue);
        return;
      case CsPackage.INTERFACE_PKG__OWNED_INTERFACE_PKGS:
        getOwnedInterfacePkgs().clear();
        getOwnedInterfacePkgs().addAll((Collection<? extends InterfacePkg>)newValue);
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
      case CsPackage.INTERFACE_PKG__OWNED_EXCHANGE_ITEMS:
        getOwnedExchangeItems().clear();
        return;
      case CsPackage.INTERFACE_PKG__OWNED_INTERFACES:
        getOwnedInterfaces().clear();
        return;
      case CsPackage.INTERFACE_PKG__OWNED_INTERFACE_PKGS:
        getOwnedInterfacePkgs().clear();
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
      case CsPackage.INTERFACE_PKG__OWNED_EXCHANGE_ITEMS:
        return ownedExchangeItems != null && !ownedExchangeItems.isEmpty();
      case CsPackage.INTERFACE_PKG__OWNED_INTERFACES:
        return ownedInterfaces != null && !ownedInterfaces.isEmpty();
      case CsPackage.INTERFACE_PKG__OWNED_INTERFACE_PKGS:
        return ownedInterfacePkgs != null && !ownedInterfacePkgs.isEmpty();
    }
    return super.eIsSet(featureID);
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
    if (baseClass == AbstractDependenciesPkg.class) {
      switch (derivedFeatureID) {
        default: return -1;
      }
    }
    if (baseClass == AbstractExchangeItemPkg.class) {
      switch (derivedFeatureID) {
        case CsPackage.INTERFACE_PKG__OWNED_EXCHANGE_ITEMS: return CapellacorePackage.ABSTRACT_EXCHANGE_ITEM_PKG__OWNED_EXCHANGE_ITEMS;
        default: return -1;
      }
    }
    return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
    if (baseClass == AbstractDependenciesPkg.class) {
      switch (baseFeatureID) {
        default: return -1;
      }
    }
    if (baseClass == AbstractExchangeItemPkg.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.ABSTRACT_EXCHANGE_ITEM_PKG__OWNED_EXCHANGE_ITEMS: return CsPackage.INTERFACE_PKG__OWNED_EXCHANGE_ITEMS;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }


} //InterfacePkgImpl
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
package org.polarsys.capella.common.re.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.common.re.ReElementContainer;
import org.polarsys.capella.common.re.RePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Catalog Element Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementPkgImpl#getOwnedElements <em>Owned Elements</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementPkgImpl#getOwnedElementPkgs <em>Owned Element Pkgs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CatalogElementPkgImpl extends ReNamedElementImpl implements CatalogElementPkg {

	/**
   * The cached value of the '{@link #getOwnedElements() <em>Owned Elements</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedElements()
   * @generated
   * @ordered
   */
	protected EList<CatalogElement> ownedElements;





	/**
   * The cached value of the '{@link #getOwnedElementPkgs() <em>Owned Element Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedElementPkgs()
   * @generated
   * @ordered
   */
	protected EList<CatalogElementPkg> ownedElementPkgs;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CatalogElementPkgImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return RePackage.Literals.CATALOG_ELEMENT_PKG;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CatalogElement> getOwnedElements() {

    if (ownedElements == null) {
      ownedElements = new EObjectContainmentEList.Resolving<CatalogElement>(CatalogElement.class, this, RePackage.CATALOG_ELEMENT_PKG__OWNED_ELEMENTS);
    }
    return ownedElements;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CatalogElementPkg> getOwnedElementPkgs() {

    if (ownedElementPkgs == null) {
      ownedElementPkgs = new EObjectContainmentEList.Resolving<CatalogElementPkg>(CatalogElementPkg.class, this, RePackage.CATALOG_ELEMENT_PKG__OWNED_ELEMENT_PKGS);
    }
    return ownedElementPkgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case RePackage.CATALOG_ELEMENT_PKG__OWNED_ELEMENTS:
        return ((InternalEList<?>)getOwnedElements()).basicRemove(otherEnd, msgs);
      case RePackage.CATALOG_ELEMENT_PKG__OWNED_ELEMENT_PKGS:
        return ((InternalEList<?>)getOwnedElementPkgs()).basicRemove(otherEnd, msgs);
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
      case RePackage.CATALOG_ELEMENT_PKG__OWNED_ELEMENTS:
        return getOwnedElements();
      case RePackage.CATALOG_ELEMENT_PKG__OWNED_ELEMENT_PKGS:
        return getOwnedElementPkgs();
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
      case RePackage.CATALOG_ELEMENT_PKG__OWNED_ELEMENTS:
        getOwnedElements().clear();
        getOwnedElements().addAll((Collection<? extends CatalogElement>)newValue);
        return;
      case RePackage.CATALOG_ELEMENT_PKG__OWNED_ELEMENT_PKGS:
        getOwnedElementPkgs().clear();
        getOwnedElementPkgs().addAll((Collection<? extends CatalogElementPkg>)newValue);
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
      case RePackage.CATALOG_ELEMENT_PKG__OWNED_ELEMENTS:
        getOwnedElements().clear();
        return;
      case RePackage.CATALOG_ELEMENT_PKG__OWNED_ELEMENT_PKGS:
        getOwnedElementPkgs().clear();
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
      case RePackage.CATALOG_ELEMENT_PKG__OWNED_ELEMENTS:
        return ownedElements != null && !ownedElements.isEmpty();
      case RePackage.CATALOG_ELEMENT_PKG__OWNED_ELEMENT_PKGS:
        return ownedElementPkgs != null && !ownedElementPkgs.isEmpty();
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
    if (baseClass == ReElementContainer.class) {
      switch (derivedFeatureID) {
        case RePackage.CATALOG_ELEMENT_PKG__OWNED_ELEMENTS: return RePackage.RE_ELEMENT_CONTAINER__OWNED_ELEMENTS;
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
    if (baseClass == ReElementContainer.class) {
      switch (baseFeatureID) {
        case RePackage.RE_ELEMENT_CONTAINER__OWNED_ELEMENTS: return RePackage.CATALOG_ELEMENT_PKG__OWNED_ELEMENTS;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }


} //CatalogElementPkgImpl
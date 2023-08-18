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
package org.polarsys.capella.core.data.capellacore.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enumeration Property Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.EnumerationPropertyTypeImpl#getOwnedLiterals <em>Owned Literals</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EnumerationPropertyTypeImpl extends NamedElementImpl implements EnumerationPropertyType {

	/**
   * The cached value of the '{@link #getOwnedLiterals() <em>Owned Literals</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedLiterals()
   * @generated
   * @ordered
   */
	protected EList<EnumerationPropertyLiteral> ownedLiterals;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected EnumerationPropertyTypeImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CapellacorePackage.Literals.ENUMERATION_PROPERTY_TYPE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<EnumerationPropertyLiteral> getOwnedLiterals() {

    if (ownedLiterals == null) {
      ownedLiterals = new EObjectContainmentEList.Resolving<EnumerationPropertyLiteral>(EnumerationPropertyLiteral.class, this, CapellacorePackage.ENUMERATION_PROPERTY_TYPE__OWNED_LITERALS);
    }
    return ownedLiterals;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case CapellacorePackage.ENUMERATION_PROPERTY_TYPE__OWNED_LITERALS:
        return ((InternalEList<?>)getOwnedLiterals()).basicRemove(otherEnd, msgs);
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
      case CapellacorePackage.ENUMERATION_PROPERTY_TYPE__OWNED_LITERALS:
        return getOwnedLiterals();
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
      case CapellacorePackage.ENUMERATION_PROPERTY_TYPE__OWNED_LITERALS:
        getOwnedLiterals().clear();
        getOwnedLiterals().addAll((Collection<? extends EnumerationPropertyLiteral>)newValue);
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
      case CapellacorePackage.ENUMERATION_PROPERTY_TYPE__OWNED_LITERALS:
        getOwnedLiterals().clear();
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
      case CapellacorePackage.ENUMERATION_PROPERTY_TYPE__OWNED_LITERALS:
        return ownedLiterals != null && !ownedLiterals.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //EnumerationPropertyTypeImpl
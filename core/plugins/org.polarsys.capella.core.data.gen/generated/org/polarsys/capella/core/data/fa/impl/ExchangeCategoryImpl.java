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
package org.polarsys.capella.core.data.fa.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Exchange Category</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.ExchangeCategoryImpl#getExchanges <em>Exchanges</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExchangeCategoryImpl extends NamedElementImpl implements ExchangeCategory {

	/**
   * The cached value of the '{@link #getExchanges() <em>Exchanges</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getExchanges()
   * @generated
   * @ordered
   */
	protected EList<FunctionalExchange> exchanges;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ExchangeCategoryImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return FaPackage.Literals.EXCHANGE_CATEGORY;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<FunctionalExchange> getExchanges() {

    if (exchanges == null) {
      exchanges = new EObjectResolvingEList<FunctionalExchange>(FunctionalExchange.class, this, FaPackage.EXCHANGE_CATEGORY__EXCHANGES);
    }
    return exchanges;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case FaPackage.EXCHANGE_CATEGORY__EXCHANGES:
        return getExchanges();
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
      case FaPackage.EXCHANGE_CATEGORY__EXCHANGES:
        getExchanges().clear();
        getExchanges().addAll((Collection<? extends FunctionalExchange>)newValue);
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
      case FaPackage.EXCHANGE_CATEGORY__EXCHANGES:
        getExchanges().clear();
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
      case FaPackage.EXCHANGE_CATEGORY__EXCHANGES:
        return exchanges != null && !exchanges.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //ExchangeCategoryImpl
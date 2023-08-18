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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.impl.AllocationImpl;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeRealization;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Functional Exchange Realization</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalExchangeRealizationImpl#getRealizedFunctionalExchange <em>Realized Functional Exchange</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionalExchangeRealizationImpl#getRealizingFunctionalExchange <em>Realizing Functional Exchange</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FunctionalExchangeRealizationImpl extends AllocationImpl implements FunctionalExchangeRealization {








	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected FunctionalExchangeRealizationImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return FaPackage.Literals.FUNCTIONAL_EXCHANGE_REALIZATION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public FunctionalExchange getRealizedFunctionalExchange() {

    FunctionalExchange realizedFunctionalExchange = basicGetRealizedFunctionalExchange();
    return realizedFunctionalExchange != null && realizedFunctionalExchange.eIsProxy() ? (FunctionalExchange)eResolveProxy((InternalEObject)realizedFunctionalExchange) : realizedFunctionalExchange;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public FunctionalExchange basicGetRealizedFunctionalExchange() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = FaPackage.Literals.FUNCTIONAL_EXCHANGE_REALIZATION__REALIZED_FUNCTIONAL_EXCHANGE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.FUNCTIONAL_EXCHANGE_REALIZATION__REALIZED_FUNCTIONAL_EXCHANGE, annotation);
    
    try {
      return (FunctionalExchange) result;
    } catch (ClassCastException exception) {
       exception.printStackTrace();
      return null;
    }
    
  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public FunctionalExchange getRealizingFunctionalExchange() {

    FunctionalExchange realizingFunctionalExchange = basicGetRealizingFunctionalExchange();
    return realizingFunctionalExchange != null && realizingFunctionalExchange.eIsProxy() ? (FunctionalExchange)eResolveProxy((InternalEObject)realizingFunctionalExchange) : realizingFunctionalExchange;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public FunctionalExchange basicGetRealizingFunctionalExchange() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = FaPackage.Literals.FUNCTIONAL_EXCHANGE_REALIZATION__REALIZING_FUNCTIONAL_EXCHANGE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.FUNCTIONAL_EXCHANGE_REALIZATION__REALIZING_FUNCTIONAL_EXCHANGE, annotation);
    
    try {
      return (FunctionalExchange) result;
    } catch (ClassCastException exception) {
       exception.printStackTrace();
      return null;
    }
    
  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case FaPackage.FUNCTIONAL_EXCHANGE_REALIZATION__REALIZED_FUNCTIONAL_EXCHANGE:
        if (resolve) return getRealizedFunctionalExchange();
        return basicGetRealizedFunctionalExchange();
      case FaPackage.FUNCTIONAL_EXCHANGE_REALIZATION__REALIZING_FUNCTIONAL_EXCHANGE:
        if (resolve) return getRealizingFunctionalExchange();
        return basicGetRealizingFunctionalExchange();
    }
    return super.eGet(featureID, resolve, coreType);
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public boolean eIsSet(int featureID) {
    switch (featureID) {
      case FaPackage.FUNCTIONAL_EXCHANGE_REALIZATION__REALIZED_FUNCTIONAL_EXCHANGE:
        return basicGetRealizedFunctionalExchange() != null;
      case FaPackage.FUNCTIONAL_EXCHANGE_REALIZATION__REALIZING_FUNCTIONAL_EXCHANGE:
        return basicGetRealizingFunctionalExchange() != null;
    }
    return super.eIsSet(featureID);
  }



} //FunctionalExchangeRealizationImpl
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
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocator;
import org.polarsys.capella.core.data.fa.FaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Exchange Allocation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.ComponentExchangeAllocationImpl#getComponentExchangeAllocated <em>Component Exchange Allocated</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.ComponentExchangeAllocationImpl#getComponentExchangeAllocator <em>Component Exchange Allocator</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ComponentExchangeAllocationImpl extends AllocationImpl implements ComponentExchangeAllocation {








	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ComponentExchangeAllocationImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ComponentExchange getComponentExchangeAllocated() {

    ComponentExchange componentExchangeAllocated = basicGetComponentExchangeAllocated();
    return componentExchangeAllocated != null && componentExchangeAllocated.eIsProxy() ? (ComponentExchange)eResolveProxy((InternalEObject)componentExchangeAllocated) : componentExchangeAllocated;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ComponentExchange basicGetComponentExchangeAllocated() {


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
    EAnnotation annotation = FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATION__COMPONENT_EXCHANGE_ALLOCATED.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATION__COMPONENT_EXCHANGE_ALLOCATED, annotation);
    
    try {
      return (ComponentExchange) result;
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

	public ComponentExchangeAllocator getComponentExchangeAllocator() {

    ComponentExchangeAllocator componentExchangeAllocator = basicGetComponentExchangeAllocator();
    return componentExchangeAllocator != null && componentExchangeAllocator.eIsProxy() ? (ComponentExchangeAllocator)eResolveProxy((InternalEObject)componentExchangeAllocator) : componentExchangeAllocator;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ComponentExchangeAllocator basicGetComponentExchangeAllocator() {


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
    EAnnotation annotation = FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATION__COMPONENT_EXCHANGE_ALLOCATOR.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATION__COMPONENT_EXCHANGE_ALLOCATOR, annotation);
    
    try {
      return (ComponentExchangeAllocator) result;
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
      case FaPackage.COMPONENT_EXCHANGE_ALLOCATION__COMPONENT_EXCHANGE_ALLOCATED:
        if (resolve) return getComponentExchangeAllocated();
        return basicGetComponentExchangeAllocated();
      case FaPackage.COMPONENT_EXCHANGE_ALLOCATION__COMPONENT_EXCHANGE_ALLOCATOR:
        if (resolve) return getComponentExchangeAllocator();
        return basicGetComponentExchangeAllocator();
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
      case FaPackage.COMPONENT_EXCHANGE_ALLOCATION__COMPONENT_EXCHANGE_ALLOCATED:
        return basicGetComponentExchangeAllocated() != null;
      case FaPackage.COMPONENT_EXCHANGE_ALLOCATION__COMPONENT_EXCHANGE_ALLOCATOR:
        return basicGetComponentExchangeAllocator() != null;
    }
    return super.eIsSet(featureID);
  }



} //ComponentExchangeAllocationImpl
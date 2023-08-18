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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.impl.AllocationImpl;
import org.polarsys.capella.core.data.cs.ArchitectureAllocation;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Architecture Allocation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ArchitectureAllocationImpl#getAllocatedArchitecture <em>Allocated Architecture</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.ArchitectureAllocationImpl#getAllocatingArchitecture <em>Allocating Architecture</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ArchitectureAllocationImpl extends AllocationImpl implements ArchitectureAllocation {








	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ArchitectureAllocationImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CsPackage.Literals.ARCHITECTURE_ALLOCATION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public BlockArchitecture getAllocatedArchitecture() {

    BlockArchitecture allocatedArchitecture = basicGetAllocatedArchitecture();
    return allocatedArchitecture != null && allocatedArchitecture.eIsProxy() ? (BlockArchitecture)eResolveProxy((InternalEObject)allocatedArchitecture) : allocatedArchitecture;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public BlockArchitecture basicGetAllocatedArchitecture() {


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
    EAnnotation annotation = CsPackage.Literals.ARCHITECTURE_ALLOCATION__ALLOCATED_ARCHITECTURE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.ARCHITECTURE_ALLOCATION__ALLOCATED_ARCHITECTURE, annotation);
    
    try {
      return (BlockArchitecture) result;
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

	public BlockArchitecture getAllocatingArchitecture() {

    BlockArchitecture allocatingArchitecture = basicGetAllocatingArchitecture();
    return allocatingArchitecture != null && allocatingArchitecture.eIsProxy() ? (BlockArchitecture)eResolveProxy((InternalEObject)allocatingArchitecture) : allocatingArchitecture;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public BlockArchitecture basicGetAllocatingArchitecture() {


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
    EAnnotation annotation = CsPackage.Literals.ARCHITECTURE_ALLOCATION__ALLOCATING_ARCHITECTURE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.ARCHITECTURE_ALLOCATION__ALLOCATING_ARCHITECTURE, annotation);
    
    try {
      return (BlockArchitecture) result;
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
      case CsPackage.ARCHITECTURE_ALLOCATION__ALLOCATED_ARCHITECTURE:
        if (resolve) return getAllocatedArchitecture();
        return basicGetAllocatedArchitecture();
      case CsPackage.ARCHITECTURE_ALLOCATION__ALLOCATING_ARCHITECTURE:
        if (resolve) return getAllocatingArchitecture();
        return basicGetAllocatingArchitecture();
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
      case CsPackage.ARCHITECTURE_ALLOCATION__ALLOCATED_ARCHITECTURE:
        return basicGetAllocatedArchitecture() != null;
      case CsPackage.ARCHITECTURE_ALLOCATION__ALLOCATING_ARCHITECTURE:
        return basicGetAllocatingArchitecture() != null;
    }
    return super.eIsSet(featureID);
  }



} //ArchitectureAllocationImpl
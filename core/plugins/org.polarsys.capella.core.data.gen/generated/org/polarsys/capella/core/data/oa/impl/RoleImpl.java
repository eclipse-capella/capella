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

package org.polarsys.capella.core.data.oa.impl;

import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.information.impl.AbstractInstanceImpl;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.data.oa.RoleAssemblyUsage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.RoleImpl#getOwnedRoleAssemblyUsages <em>Owned Role Assembly Usages</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.RoleImpl#getOwnedActivityAllocations <em>Owned Activity Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.RoleImpl#getRoleAllocations <em>Role Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.RoleImpl#getActivityAllocations <em>Activity Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.RoleImpl#getAllocatingEntities <em>Allocating Entities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.RoleImpl#getAllocatedOperationalActivities <em>Allocated Operational Activities</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RoleImpl extends AbstractInstanceImpl implements Role {

	/**
   * The cached value of the '{@link #getOwnedRoleAssemblyUsages() <em>Owned Role Assembly Usages</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedRoleAssemblyUsages()
   * @generated
   * @ordered
   */
	protected EList<RoleAssemblyUsage> ownedRoleAssemblyUsages;





	/**
   * The cached value of the '{@link #getOwnedActivityAllocations() <em>Owned Activity Allocations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedActivityAllocations()
   * @generated
   * @ordered
   */
	protected EList<ActivityAllocation> ownedActivityAllocations;




















	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected RoleImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OaPackage.Literals.ROLE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<RoleAssemblyUsage> getOwnedRoleAssemblyUsages() {

    if (ownedRoleAssemblyUsages == null) {
      ownedRoleAssemblyUsages = new EObjectContainmentEList.Resolving<RoleAssemblyUsage>(RoleAssemblyUsage.class, this, OaPackage.ROLE__OWNED_ROLE_ASSEMBLY_USAGES);
    }
    return ownedRoleAssemblyUsages;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ActivityAllocation> getOwnedActivityAllocations() {

    if (ownedActivityAllocations == null) {
      ownedActivityAllocations = new EObjectContainmentEList.Resolving<ActivityAllocation>(ActivityAllocation.class, this, OaPackage.ROLE__OWNED_ACTIVITY_ALLOCATIONS);
    }
    return ownedActivityAllocations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<RoleAllocation> getRoleAllocations() {


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
    EAnnotation annotation = OaPackage.Literals.ROLE__ROLE_ALLOCATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.ROLE__ROLE_ALLOCATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<RoleAllocation> resultAsList = (Collection<RoleAllocation>) result;
    return new EcoreEList.UnmodifiableEList<RoleAllocation>(this, OaPackage.Literals.ROLE__ROLE_ALLOCATIONS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ActivityAllocation> getActivityAllocations() {


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
    EAnnotation annotation = OaPackage.Literals.ROLE__ACTIVITY_ALLOCATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.ROLE__ACTIVITY_ALLOCATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<ActivityAllocation> resultAsList = (Collection<ActivityAllocation>) result;
    return new EcoreEList.UnmodifiableEList<ActivityAllocation>(this, OaPackage.Literals.ROLE__ACTIVITY_ALLOCATIONS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Entity> getAllocatingEntities() {


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
    EAnnotation annotation = OaPackage.Literals.ROLE__ALLOCATING_ENTITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.ROLE__ALLOCATING_ENTITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Entity> resultAsList = (Collection<Entity>) result;
    return new EcoreEList.UnmodifiableEList<Entity>(this, OaPackage.Literals.ROLE__ALLOCATING_ENTITIES, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<OperationalActivity> getAllocatedOperationalActivities() {


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
    EAnnotation annotation = OaPackage.Literals.ROLE__ALLOCATED_OPERATIONAL_ACTIVITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.ROLE__ALLOCATED_OPERATIONAL_ACTIVITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<OperationalActivity> resultAsList = (Collection<OperationalActivity>) result;
    return new EcoreEList.UnmodifiableEList<OperationalActivity>(this, OaPackage.Literals.ROLE__ALLOCATED_OPERATIONAL_ACTIVITIES, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case OaPackage.ROLE__OWNED_ROLE_ASSEMBLY_USAGES:
        return ((InternalEList<?>)getOwnedRoleAssemblyUsages()).basicRemove(otherEnd, msgs);
      case OaPackage.ROLE__OWNED_ACTIVITY_ALLOCATIONS:
        return ((InternalEList<?>)getOwnedActivityAllocations()).basicRemove(otherEnd, msgs);
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
      case OaPackage.ROLE__OWNED_ROLE_ASSEMBLY_USAGES:
        return getOwnedRoleAssemblyUsages();
      case OaPackage.ROLE__OWNED_ACTIVITY_ALLOCATIONS:
        return getOwnedActivityAllocations();
      case OaPackage.ROLE__ROLE_ALLOCATIONS:
        return getRoleAllocations();
      case OaPackage.ROLE__ACTIVITY_ALLOCATIONS:
        return getActivityAllocations();
      case OaPackage.ROLE__ALLOCATING_ENTITIES:
        return getAllocatingEntities();
      case OaPackage.ROLE__ALLOCATED_OPERATIONAL_ACTIVITIES:
        return getAllocatedOperationalActivities();
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
      case OaPackage.ROLE__OWNED_ROLE_ASSEMBLY_USAGES:
        getOwnedRoleAssemblyUsages().clear();
        getOwnedRoleAssemblyUsages().addAll((Collection<? extends RoleAssemblyUsage>)newValue);
        return;
      case OaPackage.ROLE__OWNED_ACTIVITY_ALLOCATIONS:
        getOwnedActivityAllocations().clear();
        getOwnedActivityAllocations().addAll((Collection<? extends ActivityAllocation>)newValue);
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
      case OaPackage.ROLE__OWNED_ROLE_ASSEMBLY_USAGES:
        getOwnedRoleAssemblyUsages().clear();
        return;
      case OaPackage.ROLE__OWNED_ACTIVITY_ALLOCATIONS:
        getOwnedActivityAllocations().clear();
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
      case OaPackage.ROLE__OWNED_ROLE_ASSEMBLY_USAGES:
        return ownedRoleAssemblyUsages != null && !ownedRoleAssemblyUsages.isEmpty();
      case OaPackage.ROLE__OWNED_ACTIVITY_ALLOCATIONS:
        return ownedActivityAllocations != null && !ownedActivityAllocations.isEmpty();
      case OaPackage.ROLE__ROLE_ALLOCATIONS:
        return !getRoleAllocations().isEmpty();
      case OaPackage.ROLE__ACTIVITY_ALLOCATIONS:
        return !getActivityAllocations().isEmpty();
      case OaPackage.ROLE__ALLOCATING_ENTITIES:
        return !getAllocatingEntities().isEmpty();
      case OaPackage.ROLE__ALLOCATED_OPERATIONAL_ACTIVITIES:
        return !getAllocatedOperationalActivities().isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //RoleImpl
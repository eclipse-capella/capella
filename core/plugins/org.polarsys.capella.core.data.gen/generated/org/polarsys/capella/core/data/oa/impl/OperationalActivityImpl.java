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
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.impl.AbstractFunctionImpl;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.Swimlane;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operational Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalActivityImpl#getOwnedOperationalActivityPkgs <em>Owned Operational Activity Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalActivityImpl#getActivityAllocations <em>Activity Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalActivityImpl#getOwnedSwimlanes <em>Owned Swimlanes</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalActivityImpl#getOwnedProcess <em>Owned Process</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalActivityImpl#getAllocatorEntities <em>Allocator Entities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalActivityImpl#getRealizingSystemFunctions <em>Realizing System Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalActivityImpl#getAllocatingRoles <em>Allocating Roles</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalActivityImpl#getContainedOperationalActivities <em>Contained Operational Activities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalActivityImpl#getChildrenOperationalActivities <em>Children Operational Activities</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationalActivityImpl extends AbstractFunctionImpl implements OperationalActivity {

	/**
   * The cached value of the '{@link #getOwnedOperationalActivityPkgs() <em>Owned Operational Activity Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedOperationalActivityPkgs()
   * @generated
   * @ordered
   */
	protected EList<OperationalActivityPkg> ownedOperationalActivityPkgs;




































	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected OperationalActivityImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OaPackage.Literals.OPERATIONAL_ACTIVITY;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<OperationalActivityPkg> getOwnedOperationalActivityPkgs() {

    if (ownedOperationalActivityPkgs == null) {
      ownedOperationalActivityPkgs = new EObjectContainmentEList.Resolving<OperationalActivityPkg>(OperationalActivityPkg.class, this, OaPackage.OPERATIONAL_ACTIVITY__OWNED_OPERATIONAL_ACTIVITY_PKGS);
    }
    return ownedOperationalActivityPkgs;
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
    EAnnotation annotation = OaPackage.Literals.OPERATIONAL_ACTIVITY__ACTIVITY_ALLOCATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.OPERATIONAL_ACTIVITY__ACTIVITY_ALLOCATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<ActivityAllocation> resultAsList = (Collection<ActivityAllocation>) result;
    return new EcoreEList.UnmodifiableEList<ActivityAllocation>(this, OaPackage.Literals.OPERATIONAL_ACTIVITY__ACTIVITY_ALLOCATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Swimlane> getOwnedSwimlanes() {


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
    EAnnotation annotation = OaPackage.Literals.OPERATIONAL_ACTIVITY__OWNED_SWIMLANES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.OPERATIONAL_ACTIVITY__OWNED_SWIMLANES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Swimlane> resultAsList = (Collection<Swimlane>) result;
    return new EcoreEList.UnmodifiableEList<Swimlane>(this, OaPackage.Literals.OPERATIONAL_ACTIVITY__OWNED_SWIMLANES, resultAsList.size(), resultAsList.toArray());
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

	public EList<OperationalProcess> getOwnedProcess() {


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
    EAnnotation annotation = OaPackage.Literals.OPERATIONAL_ACTIVITY__OWNED_PROCESS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.OPERATIONAL_ACTIVITY__OWNED_PROCESS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<OperationalProcess> resultAsList = (Collection<OperationalProcess>) result;
    return new EcoreEList.UnmodifiableEList<OperationalProcess>(this, OaPackage.Literals.OPERATIONAL_ACTIVITY__OWNED_PROCESS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Entity> getAllocatorEntities() {


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
    EAnnotation annotation = OaPackage.Literals.OPERATIONAL_ACTIVITY__ALLOCATOR_ENTITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.OPERATIONAL_ACTIVITY__ALLOCATOR_ENTITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Entity> resultAsList = (Collection<Entity>) result;
    return new EcoreEList.UnmodifiableEList<Entity>(this, OaPackage.Literals.OPERATIONAL_ACTIVITY__ALLOCATOR_ENTITIES, resultAsList.size(), resultAsList.toArray());
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

	public EList<SystemFunction> getRealizingSystemFunctions() {


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
    EAnnotation annotation = OaPackage.Literals.OPERATIONAL_ACTIVITY__REALIZING_SYSTEM_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.OPERATIONAL_ACTIVITY__REALIZING_SYSTEM_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<SystemFunction> resultAsList = (Collection<SystemFunction>) result;
    return new EcoreEList.UnmodifiableEList<SystemFunction>(this, OaPackage.Literals.OPERATIONAL_ACTIVITY__REALIZING_SYSTEM_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Role> getAllocatingRoles() {


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
    EAnnotation annotation = OaPackage.Literals.OPERATIONAL_ACTIVITY__ALLOCATING_ROLES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.OPERATIONAL_ACTIVITY__ALLOCATING_ROLES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Role> resultAsList = (Collection<Role>) result;
    return new EcoreEList.UnmodifiableEList<Role>(this, OaPackage.Literals.OPERATIONAL_ACTIVITY__ALLOCATING_ROLES, resultAsList.size(), resultAsList.toArray());
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

	public EList<OperationalActivity> getContainedOperationalActivities() {


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
    EAnnotation annotation = OaPackage.Literals.OPERATIONAL_ACTIVITY__CONTAINED_OPERATIONAL_ACTIVITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.OPERATIONAL_ACTIVITY__CONTAINED_OPERATIONAL_ACTIVITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<OperationalActivity> resultAsList = (Collection<OperationalActivity>) result;
    return new EcoreEList.UnmodifiableEList<OperationalActivity>(this, OaPackage.Literals.OPERATIONAL_ACTIVITY__CONTAINED_OPERATIONAL_ACTIVITIES, resultAsList.size(), resultAsList.toArray());
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

	public EList<OperationalActivity> getChildrenOperationalActivities() {


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
    EAnnotation annotation = OaPackage.Literals.OPERATIONAL_ACTIVITY__CHILDREN_OPERATIONAL_ACTIVITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.OPERATIONAL_ACTIVITY__CHILDREN_OPERATIONAL_ACTIVITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<OperationalActivity> resultAsList = (Collection<OperationalActivity>) result;
    return new EcoreEList.UnmodifiableEList<OperationalActivity>(this, OaPackage.Literals.OPERATIONAL_ACTIVITY__CHILDREN_OPERATIONAL_ACTIVITIES, resultAsList.size(), resultAsList.toArray());
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
      case OaPackage.OPERATIONAL_ACTIVITY__OWNED_OPERATIONAL_ACTIVITY_PKGS:
        return ((InternalEList<?>)getOwnedOperationalActivityPkgs()).basicRemove(otherEnd, msgs);
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
      case OaPackage.OPERATIONAL_ACTIVITY__OWNED_OPERATIONAL_ACTIVITY_PKGS:
        return getOwnedOperationalActivityPkgs();
      case OaPackage.OPERATIONAL_ACTIVITY__ACTIVITY_ALLOCATIONS:
        return getActivityAllocations();
      case OaPackage.OPERATIONAL_ACTIVITY__OWNED_SWIMLANES:
        return getOwnedSwimlanes();
      case OaPackage.OPERATIONAL_ACTIVITY__OWNED_PROCESS:
        return getOwnedProcess();
      case OaPackage.OPERATIONAL_ACTIVITY__ALLOCATOR_ENTITIES:
        return getAllocatorEntities();
      case OaPackage.OPERATIONAL_ACTIVITY__REALIZING_SYSTEM_FUNCTIONS:
        return getRealizingSystemFunctions();
      case OaPackage.OPERATIONAL_ACTIVITY__ALLOCATING_ROLES:
        return getAllocatingRoles();
      case OaPackage.OPERATIONAL_ACTIVITY__CONTAINED_OPERATIONAL_ACTIVITIES:
        return getContainedOperationalActivities();
      case OaPackage.OPERATIONAL_ACTIVITY__CHILDREN_OPERATIONAL_ACTIVITIES:
        return getChildrenOperationalActivities();
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
      case OaPackage.OPERATIONAL_ACTIVITY__OWNED_OPERATIONAL_ACTIVITY_PKGS:
        getOwnedOperationalActivityPkgs().clear();
        getOwnedOperationalActivityPkgs().addAll((Collection<? extends OperationalActivityPkg>)newValue);
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
      case OaPackage.OPERATIONAL_ACTIVITY__OWNED_OPERATIONAL_ACTIVITY_PKGS:
        getOwnedOperationalActivityPkgs().clear();
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
      case OaPackage.OPERATIONAL_ACTIVITY__OWNED_OPERATIONAL_ACTIVITY_PKGS:
        return ownedOperationalActivityPkgs != null && !ownedOperationalActivityPkgs.isEmpty();
      case OaPackage.OPERATIONAL_ACTIVITY__ACTIVITY_ALLOCATIONS:
        return !getActivityAllocations().isEmpty();
      case OaPackage.OPERATIONAL_ACTIVITY__OWNED_SWIMLANES:
        return !getOwnedSwimlanes().isEmpty();
      case OaPackage.OPERATIONAL_ACTIVITY__OWNED_PROCESS:
        return !getOwnedProcess().isEmpty();
      case OaPackage.OPERATIONAL_ACTIVITY__ALLOCATOR_ENTITIES:
        return !getAllocatorEntities().isEmpty();
      case OaPackage.OPERATIONAL_ACTIVITY__REALIZING_SYSTEM_FUNCTIONS:
        return !getRealizingSystemFunctions().isEmpty();
      case OaPackage.OPERATIONAL_ACTIVITY__ALLOCATING_ROLES:
        return !getAllocatingRoles().isEmpty();
      case OaPackage.OPERATIONAL_ACTIVITY__CONTAINED_OPERATIONAL_ACTIVITIES:
        return !getContainedOperationalActivities().isEmpty();
      case OaPackage.OPERATIONAL_ACTIVITY__CHILDREN_OPERATIONAL_ACTIVITIES:
        return !getChildrenOperationalActivities().isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //OperationalActivityImpl
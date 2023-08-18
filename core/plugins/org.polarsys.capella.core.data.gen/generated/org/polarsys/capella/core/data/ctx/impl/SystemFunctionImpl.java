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

package org.polarsys.capella.core.data.ctx.impl;

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
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.fa.impl.AbstractFunctionImpl;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.OperationalActivity;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemFunctionImpl#getOwnedSystemFunctionPkgs <em>Owned System Function Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemFunctionImpl#getAllocatingSystemComponents <em>Allocating System Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemFunctionImpl#getRealizedOperationalActivities <em>Realized Operational Activities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemFunctionImpl#getRealizingLogicalFunctions <em>Realizing Logical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemFunctionImpl#getContainedSystemFunctions <em>Contained System Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemFunctionImpl#getChildrenSystemFunctions <em>Children System Functions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SystemFunctionImpl extends AbstractFunctionImpl implements SystemFunction {

	/**
   * The cached value of the '{@link #getOwnedSystemFunctionPkgs() <em>Owned System Function Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedSystemFunctionPkgs()
   * @generated
   * @ordered
   */
	protected EList<SystemFunctionPkg> ownedSystemFunctionPkgs;




























	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected SystemFunctionImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CtxPackage.Literals.SYSTEM_FUNCTION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<SystemFunctionPkg> getOwnedSystemFunctionPkgs() {

    if (ownedSystemFunctionPkgs == null) {
      ownedSystemFunctionPkgs = new EObjectContainmentEList.Resolving<SystemFunctionPkg>(SystemFunctionPkg.class, this, CtxPackage.SYSTEM_FUNCTION__OWNED_SYSTEM_FUNCTION_PKGS);
    }
    return ownedSystemFunctionPkgs;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<SystemComponent> getAllocatingSystemComponents() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM_FUNCTION__ALLOCATING_SYSTEM_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM_FUNCTION__ALLOCATING_SYSTEM_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<SystemComponent> resultAsList = (Collection<SystemComponent>) result;
    return new EcoreEList.UnmodifiableEList<SystemComponent>(this, CtxPackage.Literals.SYSTEM_FUNCTION__ALLOCATING_SYSTEM_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<OperationalActivity> getRealizedOperationalActivities() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM_FUNCTION__REALIZED_OPERATIONAL_ACTIVITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM_FUNCTION__REALIZED_OPERATIONAL_ACTIVITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<OperationalActivity> resultAsList = (Collection<OperationalActivity>) result;
    return new EcoreEList.UnmodifiableEList<OperationalActivity>(this, CtxPackage.Literals.SYSTEM_FUNCTION__REALIZED_OPERATIONAL_ACTIVITIES, resultAsList.size(), resultAsList.toArray());
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

	public EList<LogicalFunction> getRealizingLogicalFunctions() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM_FUNCTION__REALIZING_LOGICAL_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM_FUNCTION__REALIZING_LOGICAL_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<LogicalFunction> resultAsList = (Collection<LogicalFunction>) result;
    return new EcoreEList.UnmodifiableEList<LogicalFunction>(this, CtxPackage.Literals.SYSTEM_FUNCTION__REALIZING_LOGICAL_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<SystemFunction> getContainedSystemFunctions() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM_FUNCTION__CONTAINED_SYSTEM_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM_FUNCTION__CONTAINED_SYSTEM_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<SystemFunction> resultAsList = (Collection<SystemFunction>) result;
    return new EcoreEList.UnmodifiableEList<SystemFunction>(this, CtxPackage.Literals.SYSTEM_FUNCTION__CONTAINED_SYSTEM_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<SystemFunction> getChildrenSystemFunctions() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM_FUNCTION__CHILDREN_SYSTEM_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM_FUNCTION__CHILDREN_SYSTEM_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<SystemFunction> resultAsList = (Collection<SystemFunction>) result;
    return new EcoreEList.UnmodifiableEList<SystemFunction>(this, CtxPackage.Literals.SYSTEM_FUNCTION__CHILDREN_SYSTEM_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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
      case CtxPackage.SYSTEM_FUNCTION__OWNED_SYSTEM_FUNCTION_PKGS:
        return ((InternalEList<?>)getOwnedSystemFunctionPkgs()).basicRemove(otherEnd, msgs);
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
      case CtxPackage.SYSTEM_FUNCTION__OWNED_SYSTEM_FUNCTION_PKGS:
        return getOwnedSystemFunctionPkgs();
      case CtxPackage.SYSTEM_FUNCTION__ALLOCATING_SYSTEM_COMPONENTS:
        return getAllocatingSystemComponents();
      case CtxPackage.SYSTEM_FUNCTION__REALIZED_OPERATIONAL_ACTIVITIES:
        return getRealizedOperationalActivities();
      case CtxPackage.SYSTEM_FUNCTION__REALIZING_LOGICAL_FUNCTIONS:
        return getRealizingLogicalFunctions();
      case CtxPackage.SYSTEM_FUNCTION__CONTAINED_SYSTEM_FUNCTIONS:
        return getContainedSystemFunctions();
      case CtxPackage.SYSTEM_FUNCTION__CHILDREN_SYSTEM_FUNCTIONS:
        return getChildrenSystemFunctions();
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
      case CtxPackage.SYSTEM_FUNCTION__OWNED_SYSTEM_FUNCTION_PKGS:
        getOwnedSystemFunctionPkgs().clear();
        getOwnedSystemFunctionPkgs().addAll((Collection<? extends SystemFunctionPkg>)newValue);
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
      case CtxPackage.SYSTEM_FUNCTION__OWNED_SYSTEM_FUNCTION_PKGS:
        getOwnedSystemFunctionPkgs().clear();
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
      case CtxPackage.SYSTEM_FUNCTION__OWNED_SYSTEM_FUNCTION_PKGS:
        return ownedSystemFunctionPkgs != null && !ownedSystemFunctionPkgs.isEmpty();
      case CtxPackage.SYSTEM_FUNCTION__ALLOCATING_SYSTEM_COMPONENTS:
        return !getAllocatingSystemComponents().isEmpty();
      case CtxPackage.SYSTEM_FUNCTION__REALIZED_OPERATIONAL_ACTIVITIES:
        return !getRealizedOperationalActivities().isEmpty();
      case CtxPackage.SYSTEM_FUNCTION__REALIZING_LOGICAL_FUNCTIONS:
        return !getRealizingLogicalFunctions().isEmpty();
      case CtxPackage.SYSTEM_FUNCTION__CONTAINED_SYSTEM_FUNCTIONS:
        return !getContainedSystemFunctions().isEmpty();
      case CtxPackage.SYSTEM_FUNCTION__CHILDREN_SYSTEM_FUNCTIONS:
        return !getChildrenSystemFunctions().isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //SystemFunctionImpl
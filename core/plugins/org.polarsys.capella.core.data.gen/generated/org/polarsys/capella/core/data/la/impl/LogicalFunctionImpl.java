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

package org.polarsys.capella.core.data.la.impl;

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
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.pa.PhysicalFunction;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Logical Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalFunctionImpl#getOwnedLogicalFunctionPkgs <em>Owned Logical Function Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalFunctionImpl#getAllocatingLogicalComponents <em>Allocating Logical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalFunctionImpl#getRealizedSystemFunctions <em>Realized System Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalFunctionImpl#getRealizingPhysicalFunctions <em>Realizing Physical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalFunctionImpl#getContainedLogicalFunctions <em>Contained Logical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalFunctionImpl#getChildrenLogicalFunctions <em>Children Logical Functions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LogicalFunctionImpl extends AbstractFunctionImpl implements LogicalFunction {

	/**
   * The cached value of the '{@link #getOwnedLogicalFunctionPkgs() <em>Owned Logical Function Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedLogicalFunctionPkgs()
   * @generated
   * @ordered
   */
	protected EList<LogicalFunctionPkg> ownedLogicalFunctionPkgs;




























	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected LogicalFunctionImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return LaPackage.Literals.LOGICAL_FUNCTION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<LogicalFunctionPkg> getOwnedLogicalFunctionPkgs() {

    if (ownedLogicalFunctionPkgs == null) {
      ownedLogicalFunctionPkgs = new EObjectContainmentEList.Resolving<LogicalFunctionPkg>(LogicalFunctionPkg.class, this, LaPackage.LOGICAL_FUNCTION__OWNED_LOGICAL_FUNCTION_PKGS);
    }
    return ownedLogicalFunctionPkgs;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<LogicalComponent> getAllocatingLogicalComponents() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_FUNCTION__ALLOCATING_LOGICAL_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_FUNCTION__ALLOCATING_LOGICAL_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<LogicalComponent> resultAsList = (Collection<LogicalComponent>) result;
    return new EcoreEList.UnmodifiableEList<LogicalComponent>(this, LaPackage.Literals.LOGICAL_FUNCTION__ALLOCATING_LOGICAL_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<SystemFunction> getRealizedSystemFunctions() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_FUNCTION__REALIZED_SYSTEM_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_FUNCTION__REALIZED_SYSTEM_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<SystemFunction> resultAsList = (Collection<SystemFunction>) result;
    return new EcoreEList.UnmodifiableEList<SystemFunction>(this, LaPackage.Literals.LOGICAL_FUNCTION__REALIZED_SYSTEM_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalFunction> getRealizingPhysicalFunctions() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_FUNCTION__REALIZING_PHYSICAL_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_FUNCTION__REALIZING_PHYSICAL_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PhysicalFunction> resultAsList = (Collection<PhysicalFunction>) result;
    return new EcoreEList.UnmodifiableEList<PhysicalFunction>(this, LaPackage.Literals.LOGICAL_FUNCTION__REALIZING_PHYSICAL_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<LogicalFunction> getContainedLogicalFunctions() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_FUNCTION__CONTAINED_LOGICAL_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_FUNCTION__CONTAINED_LOGICAL_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<LogicalFunction> resultAsList = (Collection<LogicalFunction>) result;
    return new EcoreEList.UnmodifiableEList<LogicalFunction>(this, LaPackage.Literals.LOGICAL_FUNCTION__CONTAINED_LOGICAL_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<LogicalFunction> getChildrenLogicalFunctions() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_FUNCTION__CHILDREN_LOGICAL_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_FUNCTION__CHILDREN_LOGICAL_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<LogicalFunction> resultAsList = (Collection<LogicalFunction>) result;
    return new EcoreEList.UnmodifiableEList<LogicalFunction>(this, LaPackage.Literals.LOGICAL_FUNCTION__CHILDREN_LOGICAL_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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
      case LaPackage.LOGICAL_FUNCTION__OWNED_LOGICAL_FUNCTION_PKGS:
        return ((InternalEList<?>)getOwnedLogicalFunctionPkgs()).basicRemove(otherEnd, msgs);
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
      case LaPackage.LOGICAL_FUNCTION__OWNED_LOGICAL_FUNCTION_PKGS:
        return getOwnedLogicalFunctionPkgs();
      case LaPackage.LOGICAL_FUNCTION__ALLOCATING_LOGICAL_COMPONENTS:
        return getAllocatingLogicalComponents();
      case LaPackage.LOGICAL_FUNCTION__REALIZED_SYSTEM_FUNCTIONS:
        return getRealizedSystemFunctions();
      case LaPackage.LOGICAL_FUNCTION__REALIZING_PHYSICAL_FUNCTIONS:
        return getRealizingPhysicalFunctions();
      case LaPackage.LOGICAL_FUNCTION__CONTAINED_LOGICAL_FUNCTIONS:
        return getContainedLogicalFunctions();
      case LaPackage.LOGICAL_FUNCTION__CHILDREN_LOGICAL_FUNCTIONS:
        return getChildrenLogicalFunctions();
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
      case LaPackage.LOGICAL_FUNCTION__OWNED_LOGICAL_FUNCTION_PKGS:
        getOwnedLogicalFunctionPkgs().clear();
        getOwnedLogicalFunctionPkgs().addAll((Collection<? extends LogicalFunctionPkg>)newValue);
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
      case LaPackage.LOGICAL_FUNCTION__OWNED_LOGICAL_FUNCTION_PKGS:
        getOwnedLogicalFunctionPkgs().clear();
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
      case LaPackage.LOGICAL_FUNCTION__OWNED_LOGICAL_FUNCTION_PKGS:
        return ownedLogicalFunctionPkgs != null && !ownedLogicalFunctionPkgs.isEmpty();
      case LaPackage.LOGICAL_FUNCTION__ALLOCATING_LOGICAL_COMPONENTS:
        return !getAllocatingLogicalComponents().isEmpty();
      case LaPackage.LOGICAL_FUNCTION__REALIZED_SYSTEM_FUNCTIONS:
        return !getRealizedSystemFunctions().isEmpty();
      case LaPackage.LOGICAL_FUNCTION__REALIZING_PHYSICAL_FUNCTIONS:
        return !getRealizingPhysicalFunctions().isEmpty();
      case LaPackage.LOGICAL_FUNCTION__CONTAINED_LOGICAL_FUNCTIONS:
        return !getContainedLogicalFunctions().isEmpty();
      case LaPackage.LOGICAL_FUNCTION__CHILDREN_LOGICAL_FUNCTIONS:
        return !getChildrenLogicalFunctions().isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //LogicalFunctionImpl